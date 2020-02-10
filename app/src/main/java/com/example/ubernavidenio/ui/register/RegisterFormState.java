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
    @Nullable
    private Integer identityCardError;
    @Nullable
    private Integer birthDateError;
    @Nullable
    private Integer cardNumberError;
    @Nullable
    private Integer CVVError;
    @Nullable
    private Integer SectorError;
    @Nullable
    private Integer StreetError;
    @Nullable
    private Integer storeNameError;
    @Nullable
    private Integer verificationCodeError;

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
        } else if (attributeName.equals("identityCard")){
            this.identityCardError = value;
        } else if (attributeName.equals("birthDate")){
            this.birthDateError = value;
        } else if (attributeName.equals("cardNumber")){
            this.cardNumberError = value;
        } else if (attributeName.equals("CVV")){
            this.CVVError = value;
        } else if (attributeName.equals("sector")){
            this.SectorError = value;
        } else if (attributeName.equals("street")){
            this.StreetError = value;
        } else if (attributeName.equals("storeName")){
            this.storeNameError = value;
        } else if (attributeName.equals("verifyCode")){
            this.verificationCodeError = value;
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
        this.identityCardError = null;
        this.birthDateError = null;
        this.cardNumberError = null;
        this.CVVError = null;
        this.SectorError = null;
        this.StreetError = null;
        this.storeNameError = null;
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
    @Nullable
    Integer getIdentityCardError(){
        return identityCardError;
    }
    @Nullable
    Integer getBirthDateError(){
        return birthDateError;
    }
    @Nullable
    Integer getCardNumberError(){
        return cardNumberError;
    }
    @Nullable
    Integer getCVVError(){
        return CVVError;
    }
    @Nullable
    Integer getSectorError(){
        return SectorError;
    }
    @Nullable
    Integer getStreetError(){
        return StreetError;
    }
    @Nullable
    Integer getStoreNameError(){
        return storeNameError;
    }
    @Nullable
    Integer getVerificationCodeError(){
        return verificationCodeError;
    }
    boolean isDataValid() {
        return isDataValid;
    }



}
