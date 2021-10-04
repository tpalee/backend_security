package com.example.demo_security.service;

import com.example.demo_security.exceptions.RecordNotFoundException;
import com.example.demo_security.exceptions.UserNotFoundException;
import com.example.demo_security.models.Authority;
import com.example.demo_security.models.User;
import com.example.demo_security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService (UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }


    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }


    public String createUser(User user) {
        String password=user.getPassword();
        String encrypted=passwordEncoder.encode(password);
        user.setPassword(encrypted);
        User newUser = userRepository.save(user);
        return newUser.getUsername();
    }


    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }


    public void updateUser(String username, User newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        String password=user.getPassword();
        String encrypted=passwordEncoder.encode(password);
        user.setPassword(encrypted);
        userRepository.save(user);
    }


    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        return user.getAuthorities();
    }


    public void addAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }


    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }


}
