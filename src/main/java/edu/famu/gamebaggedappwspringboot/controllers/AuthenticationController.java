package edu.famu.gamebaggedappwspringboot.controllers;


import edu.famu.gamebaggedappwspringboot.auth.services.SecurityService;
import edu.famu.gamebaggedappwspringboot.models.Users;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    protected final Log logger = LogFactory.getLog(this.getClass());

    //function to create user sessions and log user in
    @PostMapping("/session")
    public ResponseEntity createSession()
    {
        logger.info("Create session executed.");
        SecurityService securityService = new SecurityService();
        Users users = securityService.getFireBaseUser().getUsers();

        //return the user object in JSON format
        return ResponseEntity.ok(Collections.singletonMap("users", users));
    }
}
