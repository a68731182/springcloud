package com.abc.controller;

import com.abc.bean.Depart;
import com.abc.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/provider/depart")
public class DepartController {
    @Autowired
    private DepartService service;
    @Autowired //服务发现客户端
    private DiscoveryClient discoveryClient;

    @PostMapping("/save")
    public boolean saveHandler(@RequestBody Depart depart) {
        return service.saveDepart(depart);
    }

    @DeleteMapping("/del/{id}")
    public boolean delHandler(@PathVariable("id") Integer id) {
        return service.removeDepartById(id);
    }

    @PutMapping("/update")
    public boolean updateHandler(@RequestBody Depart depart) {
        return service.modifyDepart(depart);
    }

    @GetMapping("/get/{id}")
    public Depart getHandler(@PathVariable("id") Integer id) {
        return service.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> listHandler() {
        return service.listAllDeparts();
    }


    @GetMapping("/discovery")
    public List<String> discoveryHandler(){
        List<String> list = discoveryClient.getServices();//获取全部客户端
        for (String name : list){
          List<ServiceInstance> instances  = discoveryClient.getInstances(name); //微服务提供者主机
          System.out.println(instances);
          for (ServiceInstance instance : instances){
             String serviceId = instance.getServiceId(); //服务唯一性标识
             String host = instance.getHost();
             Map<String, String> metedata = instance.getMetadata();
             System.out.println("serviceId为:"+serviceId);
             System.out.println("host为:"+host);
              System.out.println("metedata为:"+metedata);
          }
        }
        return list;
    }
}
