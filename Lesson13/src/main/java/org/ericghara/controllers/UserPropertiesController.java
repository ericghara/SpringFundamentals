package org.ericghara.controllers;

import org.ericghara.model.UserModel;
import org.ericghara.services.UserPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class UserPropertiesController {

    private final UserPropertiesService userPropertiesService;

    @Autowired
    public UserPropertiesController(UserPropertiesService userPropertiesService) {
        this.userPropertiesService = userPropertiesService;
    }

    @GetMapping("/getAuths")
    public String getAuths(Authentication authentication) {
        return userPropertiesService.getAuths(authentication);
    }

    @GetMapping("/getUsername")
    public String getUsername(Authentication authentication) {
        return userPropertiesService.getUsername(authentication);
    }

    @PostMapping("/addUser")
    public void addUser(@RequestBody UserModel userModel, HttpServletResponse response) {
        try {
            userPropertiesService.addUser(userModel);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } catch(Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
