package edu.famu.gamebaggedappwspringboot.controllers;

import edu.famu.gamebaggedappwspringboot.models.Users;
import edu.famu.gamebaggedappwspringboot.services.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

@RequestMapping("/api/users") //base of the url for all mappings in this controller
@RestController
public class UsersController {

    //
    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    //
    @GetMapping("/")
    public ArrayList<Users> getUsers() throws ExecutionException, InterruptedException{
        return usersService.getUsers();
    }

    //
    @GetMapping("/{id}")
    public Users getUsers(@PathVariable(name = "id") String id) throws ExecutionException, InterruptedException {
        return usersService.getUsers(id);
    }

}
