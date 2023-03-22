package com.example.MovieStarter.controllers;

import com.example.MovieStarter.entities.User;
import com.example.MovieStarter.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User loginUser, HttpServletResponse response) {
        String jwt = null;

        return "Success";
    }

    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    public void signUp(@RequestBody User user, HttpServletResponse response) {
        LOG.info("Sign Up....");

        try {
            String status = userService.signUp(user);

            if (!"Succesfly created account".equalsIgnoreCase(status))
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, status);
            else
                response.sendError(HttpServletResponse.SC_OK, status);
        } catch (Exception e) {
            LOG.error("Exception : " + e);
        }
    }
}
