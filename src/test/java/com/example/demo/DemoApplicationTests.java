package com.example.demo;


import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@Import(TestSecurityConfig.class)
@SpringBootTest
class DemoApplicationTests {


    @Test
    void contextLoads() {
    }

}
