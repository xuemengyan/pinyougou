package com.yanxuemeng.core.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanxuemeng.core.dao.specification.SpecificationDao;
import com.yanxuemeng.core.dao.specification.SpecificationOptionDao;
import com.yanxuemeng.core.pojo.specification.Specification;
import com.yanxuemeng.core.pojo.specification.SpecificationOption;
import com.yanxuemeng.core.pojo.specification.SpecificationOptionQuery;
import com.yanxuemeng.core.pojo.specification.SpecificationQuery;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pojogroup.SpecificationVo;


import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 规格管理
 */
@Service
@Transactional
public class SpecificationServiceImpl implements  SpecificationService {


    @Autowired
    private SpecificationDao specificationDao;
    @Autowired
    private SpecificationOptionDao specificationOptionDao;
    @Override
    public PageResult search(Integer page, Integer rows, Specification specification) {
        //分页插件
        PageHelper.startPage(page,rows);
        //查询 分页

        SpecificationQuery specificationQuery = new SpecificationQuery();
        SpecificationQuery.Criteria criteria = specificationQuery.createCriteria();
        if (null!=specification.getSpecName()&&!"".equals(specification.getSpecName().trim())){
            criteria.andSpecNameLike("%"+specification.getSpecName().trim()+"%");
        }

        List<Specification> list = specificationDao.selectByExample(specificationQuery);
        PageInfo<Specification> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public void add(SpecificationVo specificationVo) {
        //插入规格表,并返回ID
        specificationDao.insertSelective(specificationVo.getSpecification());
        //规格选项表结果集
        List<SpecificationOption> list = specificationVo.getSpecificationOptionList();
        for (SpecificationOption specificationOption : list) {
            //将规格表ID设置为选项表外键
            specificationOption.setSpecId(specificationVo.getSpecification().getId());
            specificationOptionDao.insertSelective(specificationOption);
            
        }
    }

    @Override
    public SpecificationVo findOne(Long id) {
        SpecificationVo vo = new SpecificationVo();
        vo.setSpecification(specificationDao.selectByPrimaryKey(id));
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        query.createCriteria().andSpecIdEqualTo(id);
        vo.setSpecificationOptionList(specificationOptionDao.selectByExample(query));
        return vo;

    }

    @Override
    public void update(SpecificationVo vo) {
        //修改规格表
        specificationDao.updateByPrimaryKeySelective(vo.getSpecification());
        //删除属性表
        SpecificationOptionQuery query = new SpecificationOptionQuery();
        query.createCriteria().andSpecIdEqualTo(vo.getSpecification().getId());
        specificationOptionDao.deleteByExample(query);
        List<SpecificationOption> list = vo.getSpecificationOptionList();
        for (SpecificationOption option : list) {
            option.setSpecId(vo.getSpecification().getId());
            specificationOptionDao.insertSelective(option);
        }

    }

    @Override
    public void delete(Long[] ids) {
        //删除规格表
        SpecificationQuery specificationQuery = new SpecificationQuery();
        specificationQuery.createCriteria().andIdIn(Arrays.asList(ids));
        specificationDao.deleteByExample(specificationQuery);
        //删除规格项目表
        SpecificationOptionQuery specificationOptionQuery = new SpecificationOptionQuery();
        specificationOptionQuery.createCriteria().andSpecIdIn(Arrays.asList(ids));
        specificationOptionDao.deleteByExample(specificationOptionQuery);

    }

    @Override
    public List<Map> selectOptionList() {
        return specificationDao.selectOptionList();
    }


}
