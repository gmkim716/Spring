package com.springboot.hello.controller;

import com.springboot.hello.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    // RequestMapping 사용법
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        return "Hello world";
    }

    // 매개변수가 없는 Get 메서드
    @GetMapping(value = "/name")
    public String getName() {
        return "Jayden";
    }

    // @PathVariable을 활용한 Get 메서드 구현
    // /variable1/{String 값}
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable("variable") String variable) {
        return variable;
    }

    // @RequestParam을 활용한 Get 메서드 구현: ?key=value 형태
    // /request1?name=value1&email=value2&organization=value3
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @RequestParam(name = "name", required = false, defaultValue = "Unknown") String name,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "organization") String organization ) {
        return name + " " + email + " " + organization;
    }

    // 쿼리스트링에 들어갈 값을 모른다면 Map 객체를 활용한다
    // /request2?key1=value1&key2=value2
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }

    // DTO 객체를 활용한 get 메서드 구현
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDto) {
        return memberDto.toString();
    }
}
