package com.lifepill.possystem.entity.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The enum Permission.
 */
@Getter
@RequiredArgsConstructor
public enum Permission {

    /**
     * Owner read permission.
     */
    OWNER_READ("owner:read"),
    /**
     * Owner create permission.
     */
    OWNER_CREATE("owner:create"),
    /**
     * Cashier read permission.
     */
    CASHIER_READ("cashier:read"),
    /**
     * Cashier create permission.
     */
    CASHIER_CREATE("cashier:create"),
    /**
     * Manager read permission.
     */
    MANAGER_READ("manager:read"),
    /**
     * Manager create permission.
     */
    MANAGER_CREATE("manager:create"),
    /**
     * Other read permission.
     */
    OTHER_READ("other:read"),
    /**
     * Other create permission.
     */
    OTHER_CREATE("other:create")
    ;

    private final String permission;
}