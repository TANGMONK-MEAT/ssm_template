package com.zwl.aop.service;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2020/2/7 11:45
 */
public interface BookService {

    /**
     * 结账事务
     */
    void checkOut(String userName, String isbn);
}
