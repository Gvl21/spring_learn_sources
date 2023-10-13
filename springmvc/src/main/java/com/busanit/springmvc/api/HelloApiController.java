package com.busanit.springmvc.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController     // ResponseBody + Controller
public class HelloApiController {
    @ResponseBody
    @GetMapping("/api/hello")
    public String hello(){
        return "Hello API!";  // <- responseBody
    }
}
