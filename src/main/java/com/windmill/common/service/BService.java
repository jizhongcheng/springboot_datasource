package com.windmill.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.windmill.common.entity.PagedEntity;

import tk.mybatis.mapper.common.Mapper;

public abstract class BService<M extends Mapper<T>, T extends PagedEntity> {
    @Autowired
    protected M mapper;

    public T get(T t) {
        return (T) this.mapper.selectOne(t);
    }

    public T get(Long id) {
        return (T) this.mapper.selectByPrimaryKey(id);
    }

    public PageInfo<T> pagedQuery(T t) {
        if ((t.getPage() != null) && (t.getRows() != null)) {
            PageHelper.startPage(t.getPage().intValue(), t.getRows().intValue());
        }
        List<T> list = query(t);

        return new PageInfo(list);
    }

    public List<T> query(T t) {
        return this.mapper.select(t);
    }

    @Transactional
    public void save(T t) {
        this.mapper.insert(t);
    }

    @Transactional
    public void update(T t) {
        this.mapper.updateByPrimaryKey(t);
    }

    @Transactional
    public void remove(T t) {
        this.mapper.delete(t);
    }
}
