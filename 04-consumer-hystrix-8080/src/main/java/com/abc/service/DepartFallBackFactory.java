package com.abc.service;

import com.abc.bean.Depart;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * 服务降级类
 */
@Component  //注入bean
public class DepartFallBackFactory implements FallbackFactory<DepartService> {

    @Override
    public DepartService create(Throwable cause) {
        return new DepartService() {
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
        };
    }
}
