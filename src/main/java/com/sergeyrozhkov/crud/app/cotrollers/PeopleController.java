package com.sergeyrozhkov.crud.app.cotrollers;

import com.sergeyrozhkov.crud.app.dao.PersonDAO;
import com.sergeyrozhkov.crud.app.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PersonDAO personDAO;

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

//    @GetMapping("/new") // 1.при наоборе адреса people/new
//    public String newPerson(Model model) {
//        model.addAttribute("person", new Person()); // 2. добавляем в модель аттрибут
//        return "people/new"; // 3. и передаем в представление new.html (форму) через метод post, указанный в форме
//    }
///////////////////////////////Аналог метода выше///////////////////////////////
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) { // аннотация создаст пустой объект Person,
        return "people/new";                                          // положит его в переменную person и передаст в представление
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person) { // 4. в person получаем объект из формы
        personDAO.save(person); // 5. и сохраняем его в базу
        return "redirect:/people";
    }
}
