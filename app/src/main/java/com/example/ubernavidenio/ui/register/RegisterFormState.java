package com.example.ubernavidenio.ui.register;

import androidx.annotation.Nullable;

public class RegisterFormState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    @Nullable
    private Integer nameError;
    @Nullable
    private Integer lastNameError;
    @Nullable
    private Integer phoneNumberError;
    @Nullable
    private Integer passwordCheckerError;

    private boolean isDataValid;

    RegisterFormState(String attributeName, Integer value) {
        if(attributeName.equals("username")){
            this.usernameError = value;
        }  else if (attributeName.equals("name")){
            this.nameError = value;
        } else if (attributeName.equals("lastName")){
            this.lastNameError = value;
        } else if (attributeName.equals("phoneNumber")){
            this.phoneNumberError = value;
        } else if (attributeName.equals("password")){
            this.passwordError = value;
        } else if (attributeName.equals("passwordChecker")){
            this.passwordCheckerError = value;
        }
        this.isDataValid = false;
    }

    RegisterFormState(boolean isDataValid) {
        this.usernameError = null;
        this.nameError = null;
        this.lastNameError = null;
        this.phoneNumberError = null;
        this.passwordError = null;
        this.passwordCheckerError = null;
        this.isDataValid = isDataValid;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    @Nullable
    Integer getNameError() {
        return nameError;
    }

    @Nullable
    Integer getLastNameError() {
        return lastNameError;
    }

    @Nullable
    Integer getPhoneNumberError(){
        return phoneNumberError;
    }

    @Nullable
    Integer getPasswordCheckerError(){
        return passwordCheckerError;
    }

    boolean isDataValid() {
        return isDataValid;
    }


}
