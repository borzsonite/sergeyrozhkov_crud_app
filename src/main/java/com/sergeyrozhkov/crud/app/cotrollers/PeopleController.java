package com.sergeyrozhkov.crud.app.cotrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @RequestMapping()
    public String index(Model model) {

        return null;
    }

    @RequestMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        return null;
    }
}
