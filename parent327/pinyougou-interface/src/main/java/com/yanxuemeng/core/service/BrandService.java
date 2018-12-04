package com.yanxuemeng.core.service;

import com.yanxuemeng.core.pojo.good.Brand;
import entity.PageResult;

import java.util.List;
import java.util.Map;

public interface BrandService {

    //查询所有
    public List<Brand> findAll();

    public PageResult findPage(Integer pageNum, Integer pageSize);

    public void add(Brand brand);

    public Brand findOne(Long id);

    void update(Brand brand);

    PageResult search(Integer pageNum, Integer pageSize, Brand brand);

    void delete(Long[] ids);

    List<Map> selectOptionList();
}
