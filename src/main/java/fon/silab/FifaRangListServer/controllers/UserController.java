package fon.silab.FifaRangListServer.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import fon.silab.FifaRangListServer.exceptions.BusyUsernameException;
import fon.silab.FifaRangListServer.exceptions.UserNotFoundException;
import fon.silab.FifaRangListServer.exceptions.WrongCredentials;
import fon.silab.FifaRangListServer.model.User;
import fon.silab.FifaRangListServer.services.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author Veljko
 */
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/users")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@Valid @RequestBody User user) {
         userService.saveUser(user);
    }


    @GetMapping("{id}")
    public User getUserById(@PathVariable(value = "id") int id) {
        User user =  userService.getUserById(id);
        if(user == null) throw new UserNotFoundException("User with id "+id+" does not exists.");
        return user;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable(value = "id") int id) {
        userService.deleteUser(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable int id,@Valid @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @GetMapping("")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @PostMapping("login")
    public User login(@RequestBody User userRequest) {
        User user = userService.login(userRequest);
        if(user == null) throw new WrongCredentials("Username or password are wrong. Please, try again.");
        return user;
    }
    
    
    @PostMapping("username")
    public User findByUsername(@RequestBody User requestUser) {
        User user = userService.findByUsername(requestUser);
        if(user != null) throw new BusyUsernameException("User with username "+requestUser.getUsername()+"exists.");
        return requestUser;
    }

    @PutMapping("update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUsers(@RequestBody List<User> users) {
        userService.updateUsers(users);
    }
}
