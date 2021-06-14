package com.sergeyrozhkov.crud.app.cotrollers;

import com.sergeyrozhkov.crud.app.dao.PersonDAO;
import com.sergeyrozhkov.crud.app.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    ///////////////////lesson 22//////////////////

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
    public String create(@ModelAttribute("person")  @Valid Person person, BindingResult bindingResult) { // 4. в person получаем объект из формы
        if(bindingResult.hasErrors()) {
            return "people/new";
        }
        personDAO.save(person); // 5. и сохраняем его в базу
        return "redirect:/people";
    }

    ///////////////////lesson 23//////////////////
    @GetMapping("/{id}/edit") // 1. при вводе урл /people/id/edit  вызывается данный метод
    public String edit(Model model, @PathVariable("id") int id) { // 2. создается модель и считывается значение id из урл
        model.addAttribute("person", personDAO.show(id)); // 3. в модель добавляется пара: person:объект person c id
        return "/people/edit"; // 4. модель передается в представление edit.html
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult , @PathVariable("id") int id) { // 5. получаем объект из формы

        if(bindingResult.hasErrors()) {
            return "people/edit";
        }
        personDAO.update(id, person); //6. и сохраняем его в базу
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        personDAO.delete(id);
        return "redirect:/people";
    }
}
