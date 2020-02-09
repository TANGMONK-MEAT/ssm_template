package com.zwl.aop.service.serviceImpl;

import com.zwl.aop.dao.BookDao;
import com.zwl.aop.service.BookService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import java.sql.SQLException;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2020/2/7 11:46
 */

@Service
public class BookServiceImpl implements BookService {

    private BookDao bookDao;

    @Autowired
    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }



    /**
     * 结账事务
     *
     * @param userName
     * @param isbn
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW,
    isolation = Isolation.READ_COMMITTED,
    rollbackFor = {DataAccessException.class,NullPointerException.class, SQLException.class},
            readOnly = true,
            timeout = 30)
    @Override
    public void checkOut(String userName, String isbn) {
        //减库存
        bookDao.updateStock(isbn);
        //获取价格
        int price = bookDao.getPrice(isbn);
        //减余额
        bookDao.updateBalance(userName,price);
    }
}
