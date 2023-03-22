package com.example.MovieStarter.services;

import com.example.MovieStarter.entities.User;
import com.example.MovieStarter.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class UserService {
    private  UserRepository userRepository;

    public String signUp(User user) {
        final Logger LOG = LoggerFactory.getLogger(UserService.class);

        String statusMessage = "Error! User already exists. Please Sign in!";

        List<User> existing_users = userRepository.findAllByEmail(user.getEmail());

        if (existing_users.isEmpty()) {
            User new_user = new User();
            new_user.setEmail(user.getEmail());
            new_user.setIsadmin(false);
            new_user.setFirstName(user.getFirstName());
            new_user.setLastName(user.getLastName());
            new_user.setPassword(user.getPassword());
            statusMessage = "Succesfly created account";

        } else {
            LOG.info(statusMessage);
        }

        return statusMessage;
    }

    private String hashPassword(String password) {
        // TODO : Do an encypted and decrypted function for passwords
        return "Create function to encrypt passwords";
    }
}
