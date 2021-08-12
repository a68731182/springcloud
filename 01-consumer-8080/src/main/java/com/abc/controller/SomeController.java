package com.abc.controller;

import com.abc.bean.Depart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController //Controller, RestController的共同点都是用来表示Spring某个类的是否可以接收HTTP请求
               // @Controller+@ResponseBody    返回页面JSON XML或自定义mediaType内容到页面
               // @Controller
@RequestMapping("/consumer/depart")
public class SomeController {
    @Autowired
    private RestTemplate restTemplate;
    String SERVICE_PROVIDER = "http://localhost:8081";
    @PostMapping("/save")
    public boolean saveHandler(@RequestBody Depart depart){

        String url = SERVICE_PROVIDER + "/provider/depart/save";
        return restTemplate.postForObject(url,depart,Boolean.class);
    }
    @DeleteMapping("/del/{id}")
    public void deleteHandler(@PathVariable Integer id){
        String url = SERVICE_PROVIDER + "/provider/depart/del";
         restTemplate.delete(url);
    }
    @PutMapping("/update")
    public void updateHandler(@RequestBody Depart depart){
        String url = SERVICE_PROVIDER + "/provider/depart/update";
        restTemplate.put(url,depart);
    }
    @GetMapping("/get/{id}")
    public Depart getByIdHandler(@PathVariable Integer id){
        String url = SERVICE_PROVIDER + "/provider/depart/get";
        return restTemplate.getForObject(url,Depart.class);
    }
    @GetMapping("/list")
    public List<Depart> list(){
        String url = SERVICE_PROVIDER + "/provider/depart/list";
        return restTemplate.getForObject(url,List.class);
    }
}
