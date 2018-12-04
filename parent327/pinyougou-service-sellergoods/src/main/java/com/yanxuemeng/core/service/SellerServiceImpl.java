package com.yanxuemeng.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yanxuemeng.core.dao.seller.SellerDao;
import com.yanxuemeng.core.pojo.seller.Seller;
import com.yanxuemeng.core.pojo.seller.SellerQuery;
import entity.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerDao sellerDao;

    @Override
    public void add(Seller seller) {
        //商家入驻,未审核,设置状态码为0
        seller.setStatus("0");
        //注册时间
        seller.setCreateTime(new Date());
        //对密码进行加密
        seller.setPassword(new BCryptPasswordEncoder().encode(seller.getPassword()));
        sellerDao.insertSelective(seller);
    }

    @Override
    public PageResult search(Integer page, Integer rows, Seller seller) {
        PageHelper.startPage(page,rows);
        SellerQuery query = new SellerQuery();
        SellerQuery.Criteria criteria = query.createCriteria();
        if (null!=seller.getName()&&!"".equals(seller.getName().trim())){
            criteria.andNameLike("%"+seller.getName().trim()+"%");
        }
        if (null!=seller.getNickName()&&!"".equals(seller.getNickName().trim())){
            criteria.andNickNameLike("%"+seller.getNickName().trim()+"%");
        }

        List<Seller> list = sellerDao.selectByExample(query);
        PageInfo<Seller> pageInfo = new PageInfo<>(list);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());

    }

    @Override
    public Seller findOne(String sellerId) {
        return sellerDao.selectByPrimaryKey(sellerId);
    }

    @Override
    public void updateStatus(String sellerId, String status) {
        Seller seller = new Seller();
        seller.setSellerId(sellerId);
        seller.setStatus(status);
        sellerDao.updateByPrimaryKeySelective(seller);
    }


}
