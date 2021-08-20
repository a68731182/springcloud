package com.abc.controller;

import com.abc.bean.Depart;
import com.abc.service.DepartService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//基于Feign代理消费
@RestController
@RequestMapping("/consumer/depart")
public class FegionController {
    @Autowired
    private DepartService service;

    @PostMapping("/save")
    private boolean saveHandler(@RequestBody Depart depart){
        return service.saveDepart(depart);
    }
    @DeleteMapping("/del/{id}")
    public boolean deleteHandler(@PathVariable("id") int id) {
        return service.removeDepartById(id);
    }

    @PutMapping("/update")
    public boolean updateHandler(@RequestBody Depart depart) {
        return service.modifyDepart(depart);
    }
    @HystrixCommand(fallbackMethod="method")
    @GetMapping("/get/{id}")
    public Depart getByIdHandler(@PathVariable("id") int id) {
        return service.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> listHandler() {
        return service.listAllDeparts();
    }
    //服务降级方法
    public Depart method(@PathVariable("id") int id){
        Depart depart =new Depart();
        depart.setId(id);
        depart.setName("no this depart");
        return depart;
    }

}
