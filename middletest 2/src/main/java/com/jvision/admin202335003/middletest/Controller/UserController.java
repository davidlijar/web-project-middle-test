package com.jvision.admin202335003.middletest.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jvision.admin202335003.middletest.Domain.Users;
import com.jvision.admin202335003.middletest.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private final UserService userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserService userService, ObjectMapper objectMapper) {
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/create-user")
    public ResponseEntity<Map<String, Boolean>> createUser(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String tel = request.get("tel");
        String address = request.get("address");
        String intro = request.get("intro");
        Users user = userService.createUser(name, tel, address, intro);
        Map<String, Boolean> response = new HashMap<>();
        response.put("success", user != null);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable String id) {
        Users user = userService.getUserById(id);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);




    }


    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Boolean>> updateUser(@RequestBody Map<String, String> request, @PathVariable String id) {
        Users user = userService.getUserById(id);
        String name = request.get("name");
        String tel = request.get("tel");
        String address = request.get("address");
        String intro = request.get("intro");

        user.setName(name);
        user.setTel(tel);
        user.setIntro(intro);
        user.setAddress(address);

        Users updatedUser = userService.updateUser(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("success", updatedUser != null);
        return ResponseEntity.ok(response);
    }


}
