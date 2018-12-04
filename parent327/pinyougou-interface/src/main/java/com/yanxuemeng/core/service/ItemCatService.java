package com.yanxuemeng.core.service;

import com.yanxuemeng.core.pojo.item.ItemCat;

import java.util.List;

public interface ItemCatService {
    List<ItemCat> findByParentId(Long parentId);
}
