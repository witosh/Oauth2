package com.github.java.controllers;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceContoller {

	@GetMapping("/hasscope")
    @PreAuthorize("hasScope('GITHUB')")
    public String helloScope(final Principal principal) {
        return "Hello";
    }

    @GetMapping("/me")
    public String helloNoScope(final Principal principal) {
        return "Hello no Scope";
    }
}
