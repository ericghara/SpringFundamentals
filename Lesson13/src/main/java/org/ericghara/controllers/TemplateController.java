package org.ericghara.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping
    public String main() {
        return "main.html";
    }

    @GetMapping("/accepted")
    public String accepted() {
        return "accepted.html";
    }

    @GetMapping("/bad_request")
    public String badRequest() {
        return "bad_request.html";
    }

}
