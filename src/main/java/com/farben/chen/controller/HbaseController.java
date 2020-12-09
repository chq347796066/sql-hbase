package com.farben.chen.controller;


import com.farben.chen.dto.QueryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HbaseController {

    @Autowired
    @Qualifier("impalaJdbcTemplate")
    private JdbcTemplate impalaJdbcTemplate;


    @GetMapping("/query")
    public List<Map<String, Object>>  query(@RequestParam(required = false)String key,@RequestParam(required = false)String buyerMobile){
        StringBuilder sql=new StringBuilder("select * from hbase_hive");
        if(!StringUtils.isEmpty(key)){
            sql.append(" where key = '").append(key).append("'");
            if(!StringUtils.isEmpty(buyerMobile)){
                sql.append(" or buyer_mobile = '").append(buyerMobile).append("'");
            }
        }else {
            if(!StringUtils.isEmpty(buyerMobile)){
                sql.append(" where buyer_mobile = '").append(buyerMobile).append("'");
            }
        }
        String querySql=sql.toString();
        System.out.println("querySql:"+querySql);
        List<Map<String, Object>> maps = impalaJdbcTemplate.queryForList(querySql);
        return maps;
    }
}
