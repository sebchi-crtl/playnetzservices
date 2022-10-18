package com.playnetz.customer.model;

public record CustomerRegistrationRequest(
        String firstName,

        String lastName,

        String email,

        String address,

        String phoneNumber,

        String customerPassword ) {
}
