package com.zwl.aop.controller;

import com.zwl.aop.pojo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Method;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2020/2/8 17:16
 * 测试Rest风格的增删改查
 */
@Controller
public class BookController {

    /**
     * 新增图书
     */
    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public String addBook(Book book){
        System.out.println("add"+ book);
        return "book";
    }

    /**
     * 删除图书
     */
    @RequestMapping(value = "/book/{id}",method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("id") String bid){
        System.out.println("delete" + bid);
        return "book";
    }

    /**
     * 更新图书
     */
    @RequestMapping(value = "/book/{id}",method = RequestMethod.PUT)
    public String updateBook(@PathVariable("id") String bid){
        System.out.println("update" + bid);
        return "book";
    }

    /**
     * 查询图书
     */
    @RequestMapping(value = "/book/{id}",method = RequestMethod.GET)
    public String getBook(@PathVariable("id")String bid){
        System.out.println("get" + bid);
        return "book";
    }
}
