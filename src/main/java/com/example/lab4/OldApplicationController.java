package com.example.lab4;

import com.example.lab4.Entity.Jewel;
import com.example.lab4.Entity.Role;
import com.example.lab4.Entity.User;
import com.example.lab4.Repository.UserRepo;
import com.example.lab4.Service.CRUDJewel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.xml.validation.Validator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

@Controller
public class OldApplicationController {

    private final CRUDJewel crudJewel;

    public OldApplicationController(CRUDJewel crudJewel) {
        this.crudJewel = crudJewel;
    }
    @Autowired
    private UserRepo usrRepo;

    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("user") User user) {
        Optional<User> usrFromDb = usrRepo.findByUsername(user.getUsername());

        if (usrFromDb.isPresent()) {
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        PasswordEncoder passwordEncoders = new BCryptPasswordEncoder();

        user.setPassword(passwordEncoders.encode(user.getPassword()));
        usrRepo.save(user);

        return "redirect:/login";
    }
    @GetMapping
    public String menu(){
        return "menu";
    }

    @GetMapping("/getAllValue")
    public String getAll(Model model) {
        model.addAttribute("allJewels", crudJewel.findAll());
         return "getAll";
    }

    @DeleteMapping("/deleteValue")
    public String delete(@ModelAttribute("jewel") Jewel jewel) {
        crudJewel.deleteById(jewel.getId());
        return "redirect:";
    }

    @PostMapping(value = "/deleteJewel")
    public String deletePost(@RequestBody String id) {
        String temp = id.substring(3);
        crudJewel.deleteById(Long.parseLong(temp));
        return "redirect:";
    }

    @PostMapping("/addNewValue")
    public String add(@ModelAttribute("jewel") Jewel jewel) {
        crudJewel.add(jewel);
        return "redirect:";
    }

    @PostMapping("/updateValue")
    public String  update(@RequestBody String id, Model model){
        String temp = id.substring(3);
        model.addAttribute("jewel", crudJewel.findById(Long.parseLong(temp)));
        return "edit";
    }
    @PostMapping("/obnovi")
    public String updateJew(@ModelAttribute("jewel") Jewel jewel) {
        crudJewel.update(jewel);
        return "redirect:";
    }

    @GetMapping("/getByBrandName")
    public ResponseEntity<ArrayList<Jewel>> findByBrandName(@RequestBody String name) {
        return ResponseEntity
                .ok(crudJewel.findJewelsByBrandName(name));
    }
    @GetMapping("/new")
    public String newJewel(Model model){
        model.addAttribute("jewel",new Jewel());
        return "new";
    }
    @GetMapping("deleter")
    public String deleteForm() {
        return "delete";
    }
    @GetMapping("updater")
    public String updateForm() {
        return "updateForm";
    }
    @GetMapping("customer")
    public String customerForm() {
        return "customerForm";
    }

    @GetMapping("customerView")
    public String customerInfo(@PathParam("id") String id, Model model) {
        model.addAttribute("allJewels", crudJewel.findJewelsByBrandName(id));
        return "getAll";
    }
}
