package aopTest;

import com.zwl.aop.service.Calculator;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2020/2/6 17:14
 */
public class test01 {
//    ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");

    @Test
    public void test(){
//        Calculator calculator = (Calculator) ioc.getBean("myMathCalculator");
//        calculator.add(1,2);
//        System.out.println(calculator.getClass());
        System.out.println(ContextLoader.getCurrentWebApplicationContext());
    }
}
