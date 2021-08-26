package com.example.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JDBCController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 原生jdbc查询表数据
     * @return
     */
    @GetMapping("/getUserList")
    public List<Map<String, Object>> getUserList(){
        String sql = "select * from user";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        return mapList;
    }

    /**
     * 原生JDBC新增数据
     * @return
     */
    @GetMapping("/addUser")
    public String addUser(){
        String sql = "insert into user (id, name, pwd) values (5, '田七', '123456')";
        jdbcTemplate.execute(sql);
        return "success";
    }

    /**
     * 原生JDBC修改数据
     * @return
     */
    @GetMapping("/updateUser")
    public String updateUser(){
        String sql = "update user set name = '天一' where id = 1";
        jdbcTemplate.update(sql);
        return "success";
    }

    /**
     * 原生JDBC删除数据
     * @return
     */
    @GetMapping("/deleteUser")
    public String deleteUser(){
        String sql = "delete from user where id = 1";
        jdbcTemplate.execute(sql);
        return "success";
    }
}
