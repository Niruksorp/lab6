package com.example.lab4.Service;

import com.example.lab4.Entity.Jewel;
import com.example.lab4.Repository.JewelsRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CRUDJewel implements CRUDJewelInt {

    private final JewelsRepo jewelsRepo;

    CRUDJewel(JewelsRepo jewelsRepo) {
        this.jewelsRepo = jewelsRepo;
    }

    public Jewel add(Jewel jewel) {
        return jewelsRepo.save(jewel);
    }
    public ArrayList<Jewel> findAll() {
        return new ArrayList<>(jewelsRepo.findAll());
    }
    public void update(Jewel jewel) {
        jewelsRepo.save(jewel);
    }
    public void deleteById(Long id) {
        jewelsRepo.deleteById(id);
    }
    public Optional<Jewel> findById(Long id) { return jewelsRepo.findById(id); }
    public ArrayList<Jewel> findJewelsByBrandName(String name) {return jewelsRepo.findJewelsByBrandName(name);}
}
