package com.flying.airports;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class TemplateController {

    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }
    @GetMapping("project")
    public String getProject() {
        return "project";
    }
}
