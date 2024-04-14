package com.lifepill.possystem.entity.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    OWNER_READ("owner:read"),
    OWNER_CREATE("owner:create"),
    CASHIER_READ("cashier:read"),
    CASHIER_CREATE("cashier:create"),

    ;

    private final String permission;
}