package txTest;

import com.zwl.aop.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2020/2/7 11:51
 */
public class TxTest {

//    private ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
    @Test
    public void test01(){
//        BookService bookService = (BookService) ioc.getBean("bookServiceImpl");
////      bookService.checkOut("Tom","ISBN-001");
//        System.out.println(bookService.getClass());

    }

}
