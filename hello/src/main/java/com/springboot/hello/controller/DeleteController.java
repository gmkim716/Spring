package com.springboot.hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/delete-api")
public class DeleteController {

    // PathVariable을 사용한 delete 메서드
    @DeleteMapping(value = "/{variable}")
    public String deleteVariable(@PathVariable String variable) {
        return variable;
    }

    // RequestParam을 사용한 delete 메서드
    @DeleteMapping(value = "/request")
    public String deleteRequestParam(@RequestParam String email) {
        return "e-mail: " + email;
    }
}
