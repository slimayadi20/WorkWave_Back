package com.example.workwave.controllers;


import com.example.workwave.entities.User;
import com.example.workwave.repositories.UserRepository;
import com.example.workwave.services.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    UserRepository userRepository;


  // @PostConstruct //lors de l'execution
  // public void initRoleAndUser() {
  //     userService.initRolesAndUser();
  // }


    @GetMapping(path = "/ImgUsers/{userName}")
    public byte[] getPhoto(@PathVariable("userName") String userName) throws Exception {
        return userService.getPhoto(userName);
    }


    @GetMapping("/users") //didn't use it
    public List<User> retrieveAllUsers() {
        return userService.retrieveAllUsers();
    }

    @DeleteMapping("/delete/{userName}")
    public String deleteUser(@PathVariable String userName) {
        return userService.deleteUser(userName);
    }

 /*   @GetMapping("/list")//affichage+pagination
    public Page<User> showPage(@RequestParam(defaultValue = "0") int page) {
        return userRepository.findAll(PageRequest.of(page, 4));
    }*/


    @PostMapping({"/registerNewUser"})
    public String registerNewUser(@RequestParam("user") String user, @RequestParam("file") MultipartFile file) throws JsonProcessingException {
        return userService.registerNewUser(user, file);
    }

    @PutMapping("/updateUser/{userName}")
    public User updateUser(@RequestBody User user, @PathVariable("userName") String userName) {
        return userService.updateUser(user, userName);
    }

    @GetMapping(path = "/getUser/{userName}")
    public User getUserByUsername(@PathVariable("userName") String userName) throws Exception {
        return userService.GetUserByUsername(userName);
    }


}