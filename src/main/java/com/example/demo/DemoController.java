package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@Slf4j
public class DemoController {
    @GetMapping("get")
    public String get() {
        return "Requested GET";
    }


    @PostMapping("post")
    public String post(@RequestParam String name) {
        log.info(name);
        return "SUCCESS";
    }

}
