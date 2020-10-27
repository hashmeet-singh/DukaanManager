package com.rrdlabs.dukaanmanager.services;

import com.rrdlabs.dukaanmanager.entities.User;
import com.rrdlabs.dukaanmanager.exceptions.KeyColumnDuplicationException;
import com.rrdlabs.dukaanmanager.exceptions.RecordNotFoundException;
import com.rrdlabs.dukaanmanager.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Invalid User id: " + id));
    }

    public boolean validateUser(User user) {

        if (!user.getEmail().isEmpty() && !userRepository.findByEmail(user.getEmail()).isEmpty()) {
            throw new KeyColumnDuplicationException("Email address: " + user.getEmail() + " is already registered.");
        }

        if (!userRepository.findByPhoneNo(user.getPhoneNo()).isEmpty()) {
            throw new KeyColumnDuplicationException("Phone Number: " + user.getPhoneNo() + " is already registered.");
        }
        return true;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }
}
