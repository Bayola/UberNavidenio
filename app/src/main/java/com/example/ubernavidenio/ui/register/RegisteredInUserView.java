package com.example.ubernavidenio.ui.register;

public class RegisteredInUserView {
    private String displayName;
    //... other data fields that may be accessible to the UI

    RegisteredInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}
