package com.example.demo_security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @GetMapping("/secure")
    public ResponseEntity<Object> demoadmin() {
        return ResponseEntity.ok("Security demo-ADMIN only");
    }

    @GetMapping("/users_only")
    public ResponseEntity<Object> demousers() {
        return ResponseEntity.ok("Security demo-ADMIN only");
    }


        @GetMapping("/all")
        public ResponseEntity<Object> demopublic() {
            return ResponseEntity.ok("Security demo - everything is open");
        }




}
