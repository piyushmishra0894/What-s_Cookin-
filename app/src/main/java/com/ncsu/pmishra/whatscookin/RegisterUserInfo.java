package com.ncsu.pmishra.whatscookin;

import java.util.ArrayList;

public class RegisterUserInfo {

    public String email;
    public String password;
    public ArrayList<String> preferences;

    public RegisterUserInfo() {
    }

    public RegisterUserInfo(String email, String password, ArrayList<String> preferences) {
        this.email = email;
        this.password = password;
        this.preferences = preferences;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<String> getPreferences() {
        return preferences;
    }

    public RegisterUserInfo(String email, ArrayList<String> preferences) {
        this.email = email;
        this.preferences = preferences;
    }
}
