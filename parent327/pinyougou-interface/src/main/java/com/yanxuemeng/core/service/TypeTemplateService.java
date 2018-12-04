package com.yanxuemeng.core.service;

import com.yanxuemeng.core.pojo.template.TypeTemplate;
import entity.PageResult;

public interface TypeTemplateService {
    PageResult search(Integer page, Integer rows, TypeTemplate typeTemplate);

    void add(TypeTemplate typeTemplate);

    void update(TypeTemplate typeTemplate);

    TypeTemplate findOne(Long id);

    void delete(Long[] ids);
}
