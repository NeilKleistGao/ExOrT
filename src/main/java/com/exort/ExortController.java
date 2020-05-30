package com.exort;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExortController {

    @RequestMapping("/")
    public String getIndex(Model model) {
        return "index";
    }
}
