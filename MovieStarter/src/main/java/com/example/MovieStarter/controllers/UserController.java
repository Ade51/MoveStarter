package com.example.MovieStarter.controllers;

import com.example.MovieStarter.DTO.LoginUser;
import com.example.MovieStarter.DTO.SignUpUser;
import com.example.MovieStarter.Responses.ServiceResponse;
import com.example.MovieStarter.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestBody LoginUser loginUser, HttpServletResponse response) throws IOException {
        String jwt = null;

        ServiceResponse status = userService.correctUser(loginUser);

        if (status.isOk()) {
            response.sendError(HttpServletResponse.SC_OK, "Successfully logged in !");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, status.getError().getMessage());
        }
    }


     @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public void signUp(@RequestBody SignUpUser user, HttpServletResponse response) {
        LOG.info("Sign Up");

        try {
            ServiceResponse status = userService.signUp(user);
            if (status.isOk())
                response.sendError(HttpServletResponse.SC_OK, "Created User");
            else
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, status.getError().getMessage());
        } catch (Exception e) {
            LOG.error("Exception : " + e);
        }
    }
}
