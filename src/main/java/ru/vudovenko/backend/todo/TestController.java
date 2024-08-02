package ru.vudovenko.backend.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test()  {
        return "test work";
    }

    @GetMapping("/test2")
    public String test2() {
        return "test work2";
    }

    @GetMapping("/test3")
    public String test3() {
        return "test work3";
    }
}
