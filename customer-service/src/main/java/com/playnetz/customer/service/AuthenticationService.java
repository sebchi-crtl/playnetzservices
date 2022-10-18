package com.playnetz.customer.service;

import com.playnetz.customer.model.User;

public interface AuthenticationService {
    User signInReturnJWT(User userSignInRequest);
}
