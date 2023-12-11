package com.portfolio.carfactoryapi.controler;

import com.portfolio.carfactoryapi.model.User;
import com.portfolio.carfactoryapi.service.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Profile("prod")
@RestController
@RequestMapping()
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) {
        userService.registerNewUser(newUser);
        return ResponseEntity.ok(newUser);
    }
    @GetMapping("/user/{username}")
    @ResponseBody
    public ResponseEntity<User> getUser (@PathVariable String username){
        User user = userService.loadUserByUsername(username);
        user.setPassword(null);
        return ResponseEntity.ok(user);
    }

}
