package com.abc.balance;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//自定义负载均衡策略 : 排除一部份服务器，在非排除的服务器中选择服务器进行轮询
public class InitRule implements IRule {
    private ILoadBalancer lb;
    private List<Integer> list; //要排除的端口号

    List userlist =new ArrayList();//剩余的排除后的端口号
    public InitRule() {
    }
    public InitRule(List<Integer> list) {
        this.list = list;
    }

    @Override
    public Server choose(Object key) {
        List<Server> servers = lb.getReachableServers();//tep1:获取UP状态的server
        List<Server> avaliableServers =getAvailableServers(servers);//tep2:获取排除掉指定端口剩余的servers
        return getAvailableRandomServer(avaliableServers);//tep3:剩余的servers进行随机获取访问
    }
    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
         this.lb=lb;
    }

    @Override
    public ILoadBalancer getLoadBalancer() {
        return lb;
    }
    //tep2:获取排除掉指定端口剩余的servers
    private List<Server> getAvailableServers(List<Server> servers) {
        if (list.size()==0||list ==null){
          return servers;
        }
        for (Server server:servers){
          boolean flag=false;
          for (Integer port:list){
             if (server.getPort() != port){
                  flag=true;
             }
          }
          if (flag){
              userlist.add(server);
          }
        }
        return userlist;
    }
    //tep3:剩余的servers进行随机获取访问
    private Server getAvailableRandomServer(List<Server> servers) {
        int nextInt = new Random().nextInt(servers.size());//随机生成一个servers.size()的随机数
        return servers.get(nextInt);
    }
}
