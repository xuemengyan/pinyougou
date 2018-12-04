package com.yanxuemeng.core.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yanxuemeng.core.pojo.seller.Seller;
import com.yanxuemeng.core.service.SellerService;
import entity.PageResult;
import entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Reference
    private SellerService sellerService;

    @RequestMapping("/add")
    public Result add(@RequestBody Seller seller){
        try{
            sellerService.add(seller);
            return new Result(true,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"添加失败");
        }

    }

    @RequestMapping("/search")
    public PageResult search(Integer page,Integer rows,@RequestBody Seller seller){
        return sellerService.search(page,rows,seller);

    }

    @RequestMapping("/findOne")
    public Seller findOne(String id){
        return sellerService.findOne(id);

    }

    @RequestMapping("/updateStatus")
    public Result updateStatus(String sellerId,String status){
        try{
            sellerService.updateStatus(sellerId,status);
            return new Result(true,"审核成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"审核失败");
        }

    }


}
