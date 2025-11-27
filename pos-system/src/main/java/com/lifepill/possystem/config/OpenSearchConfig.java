package com.lifepill.possystem.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestClientBuilder;
import org.opensearch.client.json.jackson.JacksonJsonpMapper;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.transport.rest_client.RestClientTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * Configuration class for OpenSearch client setup.
 * Provides beans for connecting to OpenSearch cluster.
 */
@Configuration
public class OpenSearchConfig {

    @Value("${opensearch.host:localhost}")
    private String host;

    @Value("${opensearch.port:9200}")
    private int port;

    @Value("${opensearch.scheme:http}")
    private String scheme;

    @Value("${opensearch.username:admin}")
    private String username;

    @Value("${opensearch.password:admin}")
    private String password;

    @Value("${opensearch.enabled:false}")
    private boolean enabled;

    private RestClient restClient;

    /**
     * Creates and configures the low-level REST client for OpenSearch.
     *
     * @return RestClient configured with host, port, and authentication
     */
    @Bean
    public RestClient restClient() {
        if (!enabled) {
            return null;
        }

        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                AuthScope.ANY,
                new UsernamePasswordCredentials(username, password)
        );

        RestClientBuilder builder = RestClient.builder(
                new HttpHost(host, port, scheme)
        ).setHttpClientConfigCallback(httpClientBuilder ->
                httpClientBuilder
                        .setDefaultCredentialsProvider(credentialsProvider)
                        // Disable SSL verification for development (remove in production with proper certs)
                        .setSSLContext(createInsecureSSLContext())
        ).setRequestConfigCallback(requestConfigBuilder ->
                requestConfigBuilder
                        .setConnectTimeout(5000)
                        .setSocketTimeout(60000)
        );

        this.restClient = builder.build();
        return this.restClient;
    }

    /**
     * Creates the OpenSearch Java client using the REST client.
     *
     * @param restClient The low-level REST client
     * @return OpenSearchClient for high-level operations
     */
    @Bean
    public OpenSearchClient openSearchClient(RestClient restClient) {
        if (!enabled || restClient == null) {
            return null;
        }

        RestClientTransport transport = new RestClientTransport(
                restClient,
                new JacksonJsonpMapper()
        );

        return new OpenSearchClient(transport);
    }

    /**
     * Creates an insecure SSL context for development.
     * WARNING: Only use in development. In production, use proper SSL certificates.
     */
    private javax.net.ssl.SSLContext createInsecureSSLContext() {
        try {
            javax.net.ssl.SSLContext sslContext = javax.net.ssl.SSLContext.getInstance("TLS");
            sslContext.init(null, new javax.net.ssl.TrustManager[]{
                    new javax.net.ssl.X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return null;
                        }
                        public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        }
                        public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType) {
                        }
                    }
            }, new java.security.SecureRandom());
            return sslContext;
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SSL context", e);
        }
    }

    /**
     * Cleanup method to close the REST client when the application shuts down.
     */
    @PreDestroy
    public void closeClient() {
        if (restClient != null) {
            try {
                restClient.close();
            } catch (IOException e) {
                // Log error but don't throw
                System.err.println("Error closing OpenSearch REST client: " + e.getMessage());
            }
        }
    }

    public boolean isEnabled() {
        return enabled;
    }
}
