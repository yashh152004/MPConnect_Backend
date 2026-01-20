package com.yashhh.Backend_MP.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodeAllPassword {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Users from your DB
        String[] passwords = {
                "123456",  // mp@gmail.com
                "123456",  // pa@gmail.com
                "123456",  // citizen@gmail.com
                "123456",  // staff@gmail.com
                "1234"     // bhuvan_kaalu@gmail.com
        };

        for (String pwd : passwords) {
            System.out.println(pwd + "  ->  " + encoder.encode(pwd));
        }
    }
}
