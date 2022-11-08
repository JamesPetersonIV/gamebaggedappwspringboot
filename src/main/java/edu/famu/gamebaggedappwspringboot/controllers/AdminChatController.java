package edu.famu.gamebaggedappwspringboot.controllers;

import edu.famu.gamebaggedappwspringboot.models.Users;
import edu.famu.gamebaggedappwspringboot.services.UsersService;
import edu.famu.gamebaggedappwspringboot.models.AdminChat;
import edu.famu.gamebaggedappwspringboot.services.AdminChatService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/adminChat")
@RestController
public class AdminChatController {

    AdminChatService adminChatService;

    public AdminChatController(AdminChatService service) {
        this.adminChatService = service;
    }

    //
    @GetMapping("/users/{id}")
    public ArrayList<AdminChat> getAdminChatByUsers (@PathVariable(name="id") String id) throws ExecutionException, InterruptedException {
        return adminChatService.retrieveAdminChatByUsers(id);
    }

}
