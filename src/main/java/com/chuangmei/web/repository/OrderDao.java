package com.chuangmei.web.repository;

import org.springframework.stereotype.Repository;

import com.chuangmei.web.core.jdbc.BaseRepository;
import com.chuangmei.web.entity.Order;

@Repository
public class OrderDao extends BaseRepository<Order, Long> {

}
