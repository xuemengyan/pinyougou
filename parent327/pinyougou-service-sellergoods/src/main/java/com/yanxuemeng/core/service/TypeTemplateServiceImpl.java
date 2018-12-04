package com.yanxuemeng.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanxuemeng.core.dao.template.TypeTemplateDao;
import com.yanxuemeng.core.pojo.template.TypeTemplate;
import com.yanxuemeng.core.pojo.template.TypeTemplateQuery;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService {
    @Autowired
    TypeTemplateDao typeTemplateDao;

    @Override
    public PageResult search(Integer page, Integer rows, TypeTemplate typeTemplate) {
        PageHelper.startPage(page,rows);
        //设置查询信息
        TypeTemplateQuery query = new TypeTemplateQuery();
        TypeTemplateQuery.Criteria criteria = query.createCriteria();

        if(null != typeTemplate.getName() && !"".equals(typeTemplate.getName().trim())){
            criteria.andNameLike("%"+ typeTemplate.getName().trim() + "%");
        }
        List<TypeTemplate> list = typeTemplateDao.selectByExample(query);
        PageInfo<TypeTemplate> info = new PageInfo<>(list);
        return new PageResult(info.getTotal(),info.getList());

    }

    @Override
    public void add(TypeTemplate typeTemplate) {
        typeTemplateDao.insertSelective(typeTemplate);
    }

    @Override
    public void update(TypeTemplate typeTemplate) {
        typeTemplateDao.updateByPrimaryKeySelective(typeTemplate);
    }

    @Override
    public TypeTemplate findOne(Long id) {
        return typeTemplateDao.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {
        TypeTemplateQuery query = new TypeTemplateQuery();
        query.createCriteria().andIdIn(Arrays.asList(ids));
        typeTemplateDao.deleteByExample(query);
    }
}
