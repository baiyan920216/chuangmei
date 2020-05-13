package com.chuangmei.web.repository;

import org.springframework.stereotype.Repository;

import com.chuangmei.web.core.jdbc.BaseRepository;
import com.chuangmei.web.entity.Coin;

@Repository
public class CoinDao extends BaseRepository<Coin, Long> {

}
