package com.cook.baolema.controller;

import com.cook.baolema.respdata.Code;
import com.cook.baolema.pojo.Customer;
import com.cook.baolema.respdata.RankAndNumber;
import com.cook.baolema.respdata.Result;
import com.cook.baolema.service.CustomerService;
import com.github.pagehelper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    // 注意：这里@Autowired是报错的，因为@Autowired按照类名注入的
    @Resource
    private RedisTemplate<String, Customer> redisTemplate;

    @PostMapping("add")
    public Result add(Customer customer){
        redisTemplate.opsForValue().set(String.valueOf(customer.getCustomerID()),customer);
        Integer code = customer != null ? Code.SAVE_OK : Code.SAVE_ERR;
        String msg = customer != null ? "保存成功" : "保存失败";
        return new Result (code,customer,msg);
    }

    @GetMapping("find/{id}")
    public Result find(@PathVariable Integer id){
        Customer customer = redisTemplate.opsForValue().get(id);
        Integer code = customer != null ? Code.GET_OK : Code.GET_ERR;
        String msg = customer != null ? "" : "数据查询失败，请重试！";
        return new Result(code,customer,msg);
    }

    @GetMapping
    public Result selectAll(){
        List<Customer> customer = customerService.selectAll();
        Integer code = customer != null ? Code.GET_OK : Code.GET_ERR;
        String msg = customer != null ? "" : "数据查询失败，请重试！";
        return new Result(code,customer,msg);
    }
    @GetMapping("/{id}")
    public Result selectByID(@PathVariable Integer id){
        Customer customer = customerService.selectByID(id);
        Integer code = customer != null ? Code.GET_OK : Code.GET_ERR;
        String msg = customer != null ? "" : "数据查询失败，请重试！";
        return new Result(code,customer,msg);
    }
    @PostMapping
    public Result save(@RequestBody Customer customer){
        customer.setCreatedTime(new Date());
        if(customer.getProfilePhoto() == null){
            customer.setProfilePhoto("static/customer.jpg");
        }
        boolean flag = customerService.save(customer);
        return new Result(flag ? Code.SAVE_OK : Code.SAVE_ERR, flag, flag ? "保存成功！" : "保存失败！");

    }
    @PostMapping("/update")
    public Result update(@RequestBody Customer customer){
        boolean flag = customerService.update(customer);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag, flag ? "修改成功！" : "修改失败！");
    }
    @GetMapping("/delete/{id}")
    public Result deleteByID(@PathVariable Integer id){
        boolean flag = customerService.deleteByID(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag, flag ? "删除成功！" : "删除失败！");
    }

    @GetMapping("/{pageNum}/{pageSize}")
    public Result selectAllByPage(@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        PageInfo<Customer> customer = customerService.selectAllByPage(pageNum,pageSize);
        Integer code = customer != null ? Code.GET_OK : Code.GET_ERR;
        String msg = customer != null ? "" : "数据查询失败，请重试！";
        return new Result(code,customer,msg);
    }

    @GetMapping("/rank")
    public Result selectRank(){
        List<RankAndNumber> rankAndNumbers = customerService.selectRank();
        return new Result(rankAndNumbers != null?Code.GET_OK:Code.GET_ERR,rankAndNumbers,rankAndNumbers != null?"":"查询失败");
    }

    @GetMapping("/getbyrank/{rank}")
    public Result selectByRank(@PathVariable Integer rank){
        if(rank == -1){
            return selectAll();
        }
        List<Customer> customers = customerService.selectByRank(rank);
        Integer code = customers != null ? Code.GET_OK : Code.GET_ERR;
        String msg = customers != null ? "" : "数据查询失败，请重试！";
        return new Result(code,customers,msg);
    }
}
