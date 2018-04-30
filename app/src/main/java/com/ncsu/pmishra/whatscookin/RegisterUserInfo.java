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
}
