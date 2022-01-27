package org.ericghara.controller;

import org.ericghara.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  //only acting as an mvc controller (not rest)
public class IndexController {

    private final NumberService numberService; // proxy

    @Autowired
   public IndexController(NumberService randomNumberService) {
        this.numberService = randomNumberService;
    }

    // front controller finds page based on the url
    @GetMapping("/home")
    public String indexAction(Model model) {
        model.addAttribute("message", String.format("Lucky Number: %s", numberService) );
        return "index.html";
    }
}
