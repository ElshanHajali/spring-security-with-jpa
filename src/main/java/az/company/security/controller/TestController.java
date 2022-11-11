package az.company.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/test")
public class TestController {

    @GetMapping
    public String test() {
        return "Test passed..!";
    }

    @GetMapping("/user")
    public String testUser() {
        return "Test passed..! USER";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String testAdmin() {
        return "Test passed..! ADMIN";
    }

    @PreAuthorize("hasAuthority('update')")
    @GetMapping("/management")
    public String testManagement() {
        return "Test passed..! ADMIN,MANAGER";
    }

}













