package com.yanxuemeng.core.service;

import com.yanxuemeng.core.pojo.specification.Specification;
import entity.PageResult;
import pojogroup.SpecificationVo;

import java.util.List;
import java.util.Map;


public interface SpecificationService {
    PageResult search(Integer page, Integer rows, Specification specification);


    void add(SpecificationVo specificationVo);

    SpecificationVo findOne(Long id);

    void update(SpecificationVo vo);

    void delete(Long[] ids);

    List<Map> selectOptionList();
}
