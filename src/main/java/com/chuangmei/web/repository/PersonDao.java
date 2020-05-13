package com.chuangmei.web.repository;

import com.chuangmei.web.core.jdbc.BaseRepository;
import com.chuangmei.web.core.jdbc.JdbcUtils;
import com.chuangmei.web.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PersonDao extends BaseRepository<Person, Long> {


    public List<Person> getOpenidTel(String tel){
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        String condition = " username = :username";
//        paramMap.put("username", username);
//        List<Person> list = find(condition, paramMap, "");
        StringBuilder sql = new StringBuilder();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        sql.append("SELECT p.* FROM t_person p ");
        sql.append("WHERE p.tel = :tel ");
        paramMap.put("tel", tel);
        return new JdbcUtils(jdbcTemplate).find(sql.toString(), paramMap, Person.class);
    }

}
