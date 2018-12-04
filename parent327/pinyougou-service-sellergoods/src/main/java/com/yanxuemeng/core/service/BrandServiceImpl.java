package com.yanxuemeng.core.service;

import com.yanxuemeng.core.dao.good.BrandDao;
import com.yanxuemeng.core.pojo.good.Brand;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanxuemeng.core.pojo.good.BrandQuery;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 品牌管理
 */
@Service
@Transactional
public class BrandServiceImpl implements  BrandService {

    @Autowired
    private BrandDao brandDao;

    //查询所有
    public List<Brand> findAll(){
        return brandDao.selectByExample(null);
    }

    @Override
    public PageResult findPage(Integer pageNum, Integer pageSize) {
        //分页插件
        PageHelper.startPage(pageNum,pageSize);
        //查询
        Page<Brand> p = (Page<Brand>) brandDao.selectByExample(null);


        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public PageResult search(Integer pageNum, Integer pageSize, Brand brand) {
        //分页插件
        PageHelper.startPage(pageNum,pageSize);

        BrandQuery brandQuery = new BrandQuery();

        BrandQuery.Criteria criteria = brandQuery.createCriteria();

        //判断品牌名称   "   "
        if(null != brand.getName() && !"".equals(brand.getName().trim())){
            criteria.andNameLike("%"+ brand.getName().trim() + "%");
        }
        //判断品牌首字母
        if(null != brand.getFirstChar() && !"".equals(brand.getFirstChar().trim())){
            criteria.andFirstCharEqualTo(brand.getFirstChar().trim());
        }
        //查询
        Page<Brand> p = (Page<Brand>) brandDao.selectByExample(brandQuery);

        return new PageResult(p.getTotal(), p.getResult());
    }

    @Override
    public void delete(Long[] ids) {
        BrandQuery brandQuery = new BrandQuery();
        brandQuery.createCriteria().andIdIn(Arrays.asList(ids));
        brandDao.deleteByExample(brandQuery);
    }



    @Override
    public void add(Brand brand) {
        brandDao.insertSelective(brand);

             /*   insert into tb_brand (id,name,100) values (null,宝马,B,null,null,...); 100+
                insert into tb_brand (name,firstChar) values (宝马,B)  好*/
    }

    @Override
    public Brand findOne(Long id) {
        return brandDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Brand brand) {
        brandDao.updateByPrimaryKeySelective(brand);
    }

    @Override
    public List<Map> selectOptionList() {
        return brandDao.selectOptionList();
    }

}
