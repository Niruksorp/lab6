package com.example.lab4;

import com.example.lab4.Entity.Jewel;
import com.example.lab4.Service.CRUDJewel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/jewels/")
public class ApplicationController {

    private final CRUDJewel crudJewel;

    public ApplicationController(CRUDJewel crudJewel) {
        this.crudJewel = crudJewel;
    }

    @GetMapping("/all")
    ResponseEntity<List<Jewel>> getAllJewels() {
        return ResponseEntity
                .ok(crudJewel.findAll());
    }
    @GetMapping("/{brand}")
    ResponseEntity<List<Jewel>> getCustom(@PathVariable("brand") String brandName){
        return ResponseEntity
                .ok(crudJewel.findJewelsByBrandName(brandName));
    }

    @DeleteMapping("/{id}")
    public void deleter(@PathVariable("id") Long id) {
        crudJewel.deleteById(id);
    }

    @PostMapping()
    public void create(@ModelAttribute("jewel") Jewel jewel) {
        crudJewel.add(jewel);
    }

    @PostMapping("/update/")
    public void update(@RequestBody Jewel jewel) {
        crudJewel.update(jewel);
    }
}
