package com.lifepill.possystem.config;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Supplier;

public class CsrfTokenRequestAttributeHandler implements CsrfTokenRequestHandler {
    private String csrfRequestAttributeName = "_csrf";

    public CsrfTokenRequestAttributeHandler() {
    }

    public final void setCsrfRequestAttributeName(String csrfRequestAttributeName) {
        this.csrfRequestAttributeName = csrfRequestAttributeName;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, Supplier<CsrfToken> deferredCsrfToken) {
        Assert.notNull(request, "request cannot be null");
        Assert.notNull(response, "response cannot be null");
        Assert.notNull(deferredCsrfToken, "deferredCsrfToken cannot be null");
        request.setAttribute(HttpServletResponse.class.getName(), response);
        CsrfToken csrfToken = new SupplierCsrfToken(deferredCsrfToken);
        request.setAttribute(CsrfToken.class.getName(), csrfToken);
        String csrfAttrName = this.csrfRequestAttributeName != null ? this.csrfRequestAttributeName : csrfToken.getParameterName();
        request.setAttribute(csrfAttrName, csrfToken);
    }

    private static final class SupplierCsrfToken implements CsrfToken {
        private final Supplier<CsrfToken> csrfTokenSupplier;

        private SupplierCsrfToken(Supplier<CsrfToken> csrfTokenSupplier) {
            this.csrfTokenSupplier = csrfTokenSupplier;
        }

        public String getHeaderName() {
            return this.getDelegate().getHeaderName();
        }

        public String getParameterName() {
            return this.getDelegate().getParameterName();
        }

        public String getToken() {
            return this.getDelegate().getToken();
        }

        private CsrfToken getDelegate() {
            CsrfToken delegate = (CsrfToken)this.csrfTokenSupplier.get();
            if (delegate == null) {
                throw new IllegalStateException("csrfTokenSupplier returned null delegate");
            } else {
                return delegate;
            }
        }
    }
}