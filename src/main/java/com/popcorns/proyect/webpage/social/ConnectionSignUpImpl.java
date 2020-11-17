package com.popcorns.proyect.webpage.social;

import com.popcorns.proyect.webpage.model.AppUser;
import com.popcorns.proyect.webpage.repository.AppUserRepository;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

public class ConnectionSignUpImpl implements ConnectionSignUp {
    private AppUserRepository appUserRepository;

    public ConnectionSignUpImpl(AppUserRepository appUser) {
        this.appUserRepository = appUser;
    }

    // After logging in social networking.
    // This method will be called to create a corresponding App_User record
    // if it does not already exist.
    @Override
    public String execute(Connection<?> connection) {
        AppUser account = appUserRepository.createAppUser(connection);

        return account.getUserName();
    }
}
