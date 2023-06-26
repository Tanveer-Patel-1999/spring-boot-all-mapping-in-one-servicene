package com.tanveer.onetoone.controller;

import com.tanveer.onetoone.model.UserRequest;
import com.tanveer.onetoone.model.UserResponse;
import com.tanveer.onetoone.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/validation/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) {
        UserResponse response = userService.createUser(userRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRequest> getUserById(@PathVariable Long id) {
        UserRequest request = userService.getUserById(id);
        return ResponseEntity.ok(request);

    }

    @GetMapping("/all")
    public ResponseEntity<List<UserRequest>> getAll() {
        List<UserRequest> requestList = userService.getAllUser();
        return ResponseEntity.ok(requestList);

    }

    @PostMapping("/{id}")
    public ResponseEntity<UserRequest> updateUser(@RequestBody UserRequest userRequest, @PathVariable Long id){
        UserRequest request = userService.updateUser(userRequest,id);
        return ResponseEntity.ok(request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

}
