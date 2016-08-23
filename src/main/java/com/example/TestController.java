package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ymolodkov on 23.08.16.
 */
@RestController
public class TestController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
