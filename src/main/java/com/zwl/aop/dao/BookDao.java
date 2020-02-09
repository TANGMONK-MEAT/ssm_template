package com.zwl.aop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2020/2/7 11:11
 */
@Repository
public class BookDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    /**
     * 减去指定用户的余额
     */
    public int updateBalance(String userName,int price){
        String sql = "UPDATEAA account SET balance=balance-? WHERE username=?";
        return jdbcTemplate.update(sql,price,userName);
    }

    /**
     * 获取图书的价格
     */
    public int getPrice(String isbn){
        String sql = "SELECT price FROM book WHERE isbn=?";
        int price = 0;
        try{
            price = jdbcTemplate.queryForObject(sql, Integer.class, isbn);
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return price;
    }

    /**
     * 减少库存
     */
    public int updateStock(String isbn){
        String sql = "UPDATE book_stock SET stock=stock-1 WHERE isbn=?";
        return jdbcTemplate.update(sql,isbn);
    }
}
