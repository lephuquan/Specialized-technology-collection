package com.practice.userservice.utils;

public class EmailUtils {

    public static  String getEmailMessage(String name, String host, String token){
        return "hello " + name + "\n\n your new account has been created. Please click link below to verify your account. \n\n" +
                getVerificationUrl(host, token) + "\n\nThe support team";
    }

    public static String getVerificationUrl(String host, String token) {
        return host + "/api/users?token=" + token;
    }
}
