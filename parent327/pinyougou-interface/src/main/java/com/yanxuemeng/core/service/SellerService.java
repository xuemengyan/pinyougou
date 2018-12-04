package com.yanxuemeng.core.service;

import com.yanxuemeng.core.pojo.seller.Seller;
import entity.PageResult;

public interface SellerService {
    void add(Seller seller);


    PageResult search(Integer page, Integer rows, Seller seller);

    Seller findOne(String sellerId);

    void updateStatus(String sellerId, String status);
}
