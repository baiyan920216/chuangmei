/**
 * Project Name:guoren-xintianyou-web
 * File Name:BaseService.java
 * Package Name:com.chuangmei.web.service
 * Date:2015年2月4日上午11:27:25
 * Copyright (c) 2015, www.sim.com All Rights Reserved.
 *
 */

package com.chuangmei.web.service;

import com.chuangmei.web.core.jdbc.BaseRepository;
import com.chuangmei.web.core.jdbc.common.DataGridModel;
import com.chuangmei.web.core.jdbc.common.PageModel;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:BaseService <br/>
 * service 基类 Date: 2015年2月4日 上午11:27:25 <br/>
 * 
 * @author gang.fan
 * @version
 * @see
 */
public abstract class BaseService<T, PK extends Serializable> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    protected BaseRepository<T, PK> res;

    /**
     * findOne:根据ID获得实体. <br/>
     *
     * @author gang.fanF
     * @param id
     * @return
     */
    public T findOne(PK id) {
        return res.findOne(id);
    }

    /**
     * findAll:获得所有实体<br/>
     *
     * @author gang.fan
     * @return
     */
    public List<T> findAll() {
        return res.findAll();

    }
    
    public List<T> find(String condition,Map<String,Object> paramMap,String order) {
        return res.find(condition, paramMap, order);

    }

    /**
     * delete:根据ID删除实体. <br/>
     *
     * @author gang.fan
     * @param id
     * @return
     */
    public int delete(PK id) {
        return res.delete(id);
    }


    /**
     * save:保存实体 <br/>
     *
     * @author gang.fan
     * @param t
     * @return
     */
    public T save(T t) {
        res.save(t);
        return t;
    }

    public void deleteAll(PK[] ids) {

        res.delete(ids);
    }

    /**
     * update:更新实体 <br/>
     *
     * @author gang.fan
     * @param t
     * @return
     */
    public T update(T t) {
        res.update(t);
        return t;
    }

    /**
     * update:更新或者保存实体 <br/>
     *
     * @author gang.fan
     * @param t
     * @return
     */
    public T saveOrUpdate(T t) {
        return res.saveOrUpdate(t);
    }

    /**
     * findPage:最简单的方式获得分页的信息列表.但是因为太简单了，估计都用不上了. <br/>
     *
     * @author gang.fan
     * @param dataGridModel
     * @return
     */
    public PageModel<T> findPage(DataGridModel dataGridModel) {
        // TODO Auto-generated method stub
        return res.getListByPage(dataGridModel.getPage(),
                dataGridModel.getRows(), null, null, null);
    }
    
    public PageModel<T> findPage(String condition,Map<String,Object> paramMap,DataGridModel dataGridModel) {
        // TODO Auto-generated method stub
        return res.getListByPage(dataGridModel.getPage(),
                dataGridModel.getRows(), condition, paramMap, dataGridModel.getOrder());
    }
}
