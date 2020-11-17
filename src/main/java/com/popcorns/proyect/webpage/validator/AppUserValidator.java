package com.popcorns.proyect.webpage.validator;

import com.popcorns.proyect.webpage.form.AppUserForm;
import com.popcorns.proyect.webpage.model.AppUser;
import com.popcorns.proyect.webpage.repository.AppUserRepository;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AppUserValidator implements Validator {
    // common-validator library.
    private EmailValidator emailValidator = EmailValidator.getInstance();

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == AppUserForm.class;
    }

    @Override
    public void validate(Object target, Errors errors) {

        AppUserForm form = (AppUserForm) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "", "Email is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "", "User name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "", "First name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "", "Last name is required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "", "Password is required");

        if (errors.hasErrors()) {
            return;
        }

        if (!emailValidator.isValid(form.getEmail())) {

            errors.rejectValue("email", "", "Email is not valid");
            return;
        }

        AppUser userAccount = appUserRepository.findAppUserByUserName(form.getUserName());
        if (userAccount != null) {
            if (form.getUserId() == null) {
                errors.rejectValue("userName", "", "User name is not available");
                return;
            } else if (!form.getUserId().equals(userAccount.getUserId() )) {
                errors.rejectValue("userName", "", "User name is not available");
                return;
            }
        }

        userAccount = appUserRepository.findByEmail(form.getEmail());
        if (userAccount != null) {
            if (form.getUserId() == null) {
                errors.rejectValue("email", "", "Email is not available");
                return;
            } else if (!form.getUserId().equals(userAccount.getUserId() )) {
                errors.rejectValue("email", "", "Email is not available");
                return;
            }
        }
    }
}
