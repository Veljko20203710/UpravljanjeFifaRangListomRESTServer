/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fon.silab.FifaRangListServer.services;

import fon.silab.FifaRangListServer.exceptions.UserNotFoundException;
import fon.silab.FifaRangListServer.model.User;
import fon.silab.FifaRangListServer.repositories.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Veljko
 */
@Service
@Transactional
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(int id, User updatedUser) {
        User user = getUserById(id);
        if(user == null) throw new UserNotFoundException("User with id "+id+" does not exists.");
        user.setUsername(updatedUser.getUsername());
        user.setPassword(updatedUser.getPassword());
        user.setActive(updatedUser.isActive());
        user.setAdministator(updatedUser.isAdministator());
        userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User login(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    public User findByUsername(User user) {
        return userRepository.findByUsername(user.getUsername());
    }

    public void updateUsers(List<User> users) {
        users.forEach((user) -> {
            userRepository.save(user);
        });
    }

}
