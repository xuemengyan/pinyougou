package com.yanxuemeng.core.controller;

import com.yanxuemeng.core.pojo.good.Brand;
import com.yanxuemeng.core.service.BrandService;
import com.alibaba.dubbo.config.annotation.Reference;
import entity.PageResult;
import entity.Result;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.List;
import java.util.Map;

/**
 * 品牌管理
 */
@RestController
@RequestMapping("/brand")
public class BrandController {


    @Reference
    private BrandService brandService;


    //查询所有品牌结果集
    @RequestMapping("/findAll")
    public List<Brand> findAll(){
        return brandService.findAll();
    }
    //查询分页对象
    @RequestMapping("/findPage")
    public PageResult findPage(Integer pageNum,Integer pageSize){

        return brandService.findPage(pageNum,pageSize);

    }
    //查询分页对象  带条件   $scope.searchEntity = '{name:O,fr..:O}'
    @RequestMapping("/search")
    public PageResult search(Integer pageNum, Integer pageSize, @RequestBody Brand brand){

        return brandService.search(pageNum,pageSize,brand);

    }
    //添加品牌
    @RequestMapping("/add")
    public Result add(@RequestBody Brand brand){

        try {
            brandService.add(brand);
            return new Result(true,"保存成功");
        } catch (Exception e) {
            //e.printStackTrace();
            return new Result(false,"保存失败");
        }

    }
    //修改品牌
    @RequestMapping("/update")
    public Result update(@RequestBody Brand brand){

        try {
            brandService.update(brand);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            //e.printStackTrace();
            return new Result(false,"修改失败");
        }

    }

    //查询一个品牌
    @RequestMapping("/findOne")
    public Brand findOne(Long id){

        return brandService.findOne(id);

    }

    //删除
    @RequestMapping("/delete")
    public Result delete(Long[] ids){

        try {
            brandService.delete(ids);
            return new Result(true,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

    //查询一个品牌
    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){

        return brandService.selectOptionList();

    }
}
