package com.example.lab4;

import com.example.lab4.Entity.Jewel;
import com.example.lab4.Service.CRUDJewel;
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
    public ResponseEntity<Jewel> create(@RequestBody Jewel jewel) {
        return ResponseEntity
                .ok(crudJewel.add(jewel));
    }

    @PutMapping()
    public void update(@RequestBody Jewel jewel) {
        crudJewel.update(jewel);
    }
}
