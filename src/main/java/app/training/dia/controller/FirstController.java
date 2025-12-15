package app.training.dia.controller;

import app.training.dia.service.FirstService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/first")
public class FirstController {

    @Autowired
    private FirstService firstService;

    @GetMapping
    public String helloWorld(@RequestParam("string") String string){
        return "Hello World "+string;
    }

    @GetMapping("/login")
    public boolean login(@RequestParam("username") String username){
        return firstService.validateUsername(username);
    }
}
