package com.practice.event.EventSystem.user;

import com.practice.event.EventSystem.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void addUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()) throw new IllegalStateException("User with email already exists");
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new IllegalStateException("User with that id does not exist");
        userRepository.delete(userOptional.get());
    }

    public void updateUser(Long userId, String name, String email, LocalDate birthdate) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new IllegalStateException("User with id: " + userId + " does not exist.");

        User user = userOptional.get();
        if(name != null) user.setName(name);
        if(email != null) {
            Optional<User> emailOptional = userRepository.findByEmail(email);
            if(emailOptional.isPresent()) throw new IllegalStateException("User with new email exists already");
            user.setEmail(email);
        }
        if(birthdate != null) user.setBirthdate(birthdate);

        userRepository.save(user);
    }

    public User getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()) throw new IllegalStateException("No user with id found");
        return userOptional.get();
    }

    public User getuserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()) throw new IllegalStateException("No user with id found");
        return userOptional.get();
    }
}
