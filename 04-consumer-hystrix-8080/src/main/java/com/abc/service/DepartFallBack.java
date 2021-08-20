package com.abc.service;

import com.abc.bean.Depart;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Component
@RequestMapping("/fallback/consumer/depart")
public class DepartFallBack implements DepartService{
    @Override
    public boolean saveDepart(Depart depart) {
        return false;
    }

    @Override
    public boolean removeDepartById(Integer id) {
        return false;
    }

    @Override
    public boolean modifyDepart(Depart depart) {
        return false;
    }

    @Override
    public Depart getDepartById(int id) {
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart -- class");
        return depart;
    }

    @Override
    public List<Depart> listAllDeparts() {
        return null;
    }
}
