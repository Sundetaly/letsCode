package com.example.servingwebcontent.controller;

import com.example.servingwebcontent.domain.Message;
import com.example.servingwebcontent.repos.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Iterator;
import java.util.List;

@Controller
public class GreetingController {
@Autowired
private MessageRepository messageRepository;

    @GetMapping("/")
    public String greeting(Model model) {
        return "home";
    }

    @GetMapping("/main")
    public String main(Model model){
        Iterable<Message> messages = messageRepository.findAll();
        System.out.println("---------------------------");
        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/main")
    public String add(@RequestParam String text,
                      @RequestParam String tag,
                      Model model) {
        Message message = new Message(text, tag);
        messageRepository.save(message);

        Iterable<Message> messages = messageRepository.findAll();

        model.addAttribute("messages", messages);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String tag, Model model){
        List<Message> filterMessages = messageRepository.findByTag(tag);
        model.addAttribute("filterMessages", filterMessages);
        return "main";
    }
}
