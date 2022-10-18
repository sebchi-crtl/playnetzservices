package com.playnetz.customer.model;

public enum UserPermission {

    PRODUCER_READ("producer:read"),
    PRODUCER_WRITE("producer:write"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_WRITE("customer:write"),
    PLANS_READ("plans:read"),
    PLANS_WRITE("plans:write"),
    MOVIES_READ("movies:read"),
    MOVIES_WRITE("movies:write"),
    CATEGORY_READ("category:read"),
    CATEGORY_WRITE("category:write");


    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
