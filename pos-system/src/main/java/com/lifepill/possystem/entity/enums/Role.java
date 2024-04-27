package com.lifepill.possystem.entity.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.lifepill.possystem.entity.enums.Permission.*;

/**
 * The enum Role.
 */
@RequiredArgsConstructor
public enum Role {
    /**
     * Owner role.
     */
// CASHIER,MANAGER,OWNER,OTHER
   OWNER(
            Set.of(
            OWNER_READ,
            OWNER_CREATE,
            CASHIER_READ,
            CASHIER_CREATE,
            OTHER_READ,
            OTHER_CREATE
            )
  ),

    /**
     * Manager role.
     */
    MANAGER(
            Set.of(
                    MANAGER_READ,
                    MANAGER_CREATE,
                    CASHIER_READ,
                    CASHIER_CREATE,
                    OTHER_READ,
                    OTHER_CREATE
            )
    ),
    /**
     * Cashier role.
     */
    CASHIER(
            Set.of(
            CASHIER_READ,
            CASHIER_CREATE
            )
    ),

    /**
     * Other role.
     */
    OTHER(
            Set.of(
            OTHER_READ,
            OTHER_CREATE
            )
    )

    ;

    @Getter
    private final Set<Permission> permissions;

    /**
     * Gets authorities.
     *
     * @return the authorities
     */
    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_"+ this.name()));
        return authorities;
    }
}
