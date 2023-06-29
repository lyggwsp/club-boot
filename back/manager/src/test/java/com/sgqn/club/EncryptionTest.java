package com.sgqn.club;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author wspstart
 * @description
 * @create 2023-06-29 16:01
 */
public class EncryptionTest {

//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @Test
    public void testEncoder() {
//        String encode = passwordEncoder.encode("123456");
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
    }
}
