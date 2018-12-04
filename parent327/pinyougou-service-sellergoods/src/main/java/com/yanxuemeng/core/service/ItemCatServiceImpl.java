package com.yanxuemeng.core.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.yanxuemeng.core.dao.item.ItemCatDao;
import com.yanxuemeng.core.pojo.item.ItemCat;
import com.yanxuemeng.core.pojo.item.ItemCatQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatDao itemCatDao;
    @Override
    public List<ItemCat> findByParentId(Long parentId) {
        ItemCatQuery query = new ItemCatQuery();
        query.createCriteria().andParentIdEqualTo(parentId);
        return itemCatDao.selectByExample(query);
    }
}
