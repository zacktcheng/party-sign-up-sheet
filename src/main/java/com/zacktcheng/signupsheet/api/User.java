package com.zacktcheng.signupsheet.api;

import org.springframework.stereotype.Component;

@Component
public class User {

    private static final String PATTERN = System.getenv("PartySignupSheetAdminPassword");
    private String password;
    private int validInBinary;
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getValidInBinary() {
        return validInBinary;
    }
    public void setValidInBinary() {
        validInBinary = isInputPasswordValid() ? 1 : 0;
    }    
    public boolean isInputPasswordValid() {
        return (password != null && password.equals(PATTERN)) ? true : false;
    }
}
