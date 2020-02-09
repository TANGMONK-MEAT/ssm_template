package com.zwl.aop.controller;

import com.zwl.aop.pojo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * @Auther:zwl
 * @Version: 1.0
 * @create: 2020/2/8 14:42
 * Spring的控制器
 * 运行流程
 * 1、客户端发送请求 http://localhost:8080/spring_aop_war_exploded/to_login
 * 2、tomcat服务器接收请求
 *      1）、SpringMVC的前端控制器，先收到所有请求
 *      2）、根据  请求地址 和 @RequestMapping("/to_login")的value值进行匹配，来找到哪个Controller类的哪个方法来处理请求
 *
 * 3、SpringMVC的前端控制器找到了目标Controller类和目标方法，利用反射来执行目标方法
 * 4、方法完成后，有一个返回值，SpringMVC的前端控制器 拿到返回值，根据 返回值，寻找目标页面的地址
 * 5、用SpringMVC的视图解析器，根据返回值，拼接出完整的目标页面地址
 * 6、SpringMVC的前端控制器，根据完整的目标页面地址，转发到该页面
 *
 * 注意：
 * 1、@RequestMapping("/to_login")
 *          就是告诉SpringMVC，这个方法用来处理哪个请求
 *          / 可以省略，默认是当前项目下的开始
 *          该注解可以标注在方法和类上
 *2、如果不指定SpringMVC配置文件的路径
 *      1）、默认的SpringMVC配置文件
 *      /WEB-INF/前端控制器名-servlet.xml
 *      例如：/WEB-INF/springDispatcherServlet-servlet.xml
 *
 *      2）、配置配置文件的路径
 *     <init-param>
 *       <param-name>contextConfigLocation</param-name>
 *       <param-value>classpath:springMVC.xml</param-value>
 *     </init-param>
 *
 */
@Controller
@RequestMapping(value = "/test")
public class MyFirstController {

    /**
     *  / 代表当前项目，处理当前项目下的login请求
     *  RequestMapping的属性
     *
     *  value ：处理的请求，映射地址
     *  method ： 限定请求方式（GET、POST。。。）默认是接收所有类型的请求
     *      例如：method = RequestMethod.POST
     *      HTTP协议中的所有请求方式：GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACE
     *      如果不是规定的请求方式会报错：HTTP Status 405 – 方法不允许
     *
     *  params : 规定请求参数，如果参数不符合规定会报错
     *          params 和 headers支持简单的表达式：
     *          param1: 表示请求必须包含名为 param1 的请求参数
     *          !param1: 表示请求不能包含名为 param1 的请求参数
     *          param1 != value1: 表示请求包含名为 param1 的请求参数，但 必须带有param1，其值不能为 value1且不能为空空
     *          {"param1=value1", "param2"}: 请求必须包含名为 param1 和param2 的两个请求参数，且 param1 参数的值必须为 value1
     *
     * headers ： 规定请求头，headers支持简单的表达式
     *
     * consumes : 规定接收的内容类型，设置请求头中的Content-Type
     *              例如：Content-Type = "text/html;charset=utf-8"
     *
     * produces : 规定返回给浏览器的内容类型，设置响应头中的Content-Type
     *
     */
    @RequestMapping(value = "/to_login",method = RequestMethod.POST, params = {"username","password"})
    public String myFirstRequest(String username,String password){
        System.out.println(username + password + "处理login请求。。。");
//        <property name="prefix" value="/WEB-INF/view/"/>
//        <property name="suffix" value=".jsp"/>
//       前缀（/WEB-INF/view/） + 返回值(login) + 后缀（.jsp）
        return "login";
    }

    /**
     * RequestMapping-Ant风格的URL
     * 1、Ant 风格资源地址支持 3 种匹配符：
     *      ?：匹配文件名中的一个字符
     *      *：匹配文件名中的任意字符
     *      **：** 匹配多层路径
     */
    @RequestMapping(value = "/to_register")
    public String register(){
        return "register";
    }

    /**
     * 1、@PathVariable 绑定 URL 占位符到入参：
     * 通过 @PathVariable 可以将 URL 中占位符参数绑定到控制器处理方法的入参中：
     * URL 中的 {xxx} 占位符可以通过 @PathVariable("xxx") 绑定到操作方法的入参中
     *
     * 2、请求方式：
     *  HTTP 协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE。
     * 它们分别对应四种基本操作：GET 用来获取资源，POST 用来新建资源，PUT 用来更新资源，DELETE 用来删除资源。
     *
     * 3、Rest设计URL地址：以简洁的URL提交请求，以请求方式区分对资源的操作
     * 对book的操作：
     * /book/1  ： GET请求-----查询1号图书
     * /book/1  : PUT请求------更新1号图书
     * /book/1  : DELETE请求---删除1号图书
     * /book    : POST请求-----增加图书
     *
     * 4、HiddenHttpMethodFilter过滤器：
     * 问题：浏览器 form 表单只支持 GET 与 POST 请求，而DELETE、PUT 等 method 并不支持。
     * 解决：Spring3.0 添加了一个过滤器，可以将这些请求转换为标准的 http 方法，使得支持 GET、POST、PUT 与 DELETE 请求。
     */
    @RequestMapping(value = "/{name}/{id}")
    public String testPathVariable(@PathVariable("id") String my_id, @PathVariable("name") String my_name){
        System.out.println(my_name + my_id);
        return "login";
    }

    /**
     * SpringMVC如何获取请求带来的参数
     *
     *  Spring MVC 框架会将 HTTP 请求的信息绑定到相应的方法入参中，并根据方法的返回值类型做出相应的后续处理
     *  （ @PathVariable 、@RequestParam、@RequestHeader 等）
     *
     * 默认方式获取请求参数：
     *          直接给方法入参上，和请求参数名相同的变量，这个变量就来接收请求参数的值
     *          有值：值；       没值：null
     *
     * 1、@RequestParam：获取请求参数
     *          例如：@RequestParam(value = "username")String name，此时，客户端必须提交一个名为username的请求参数，否则会报错
     *              name = request.getParameter("username")
     *          属性：
     *          value ： 指定要获取的参数的key
     *          required : 标明此参数是否是必须的 ，默认为 true, 表示请求参数中必须包含对应的参数，若不存在，将抛出异常
     *          defaultValue : 指定参数的默认值，此时客户端必须可以不提交一个名为username的请求参数
     * 2、@PathVariable ： 获取请求路径中的值 例如：/book/{id}
     *
     * 3、@RequestHeader : 获取请求头中某个key的值，如果请求头中没有指定的key，会报错
     *                      相当于 userAgent = request.getHeader("User-Agent");
     *                      属性：
     *                      value ： 指定要获取的参数的key
     *                      required : 标明此参数是否是必须的 ，默认为 true, 表示请求参数中必须包含对应的参数，若不存在，将抛出异常
     *                      defaultValue : 指定参数的默认值，此时请求头可以没有这个key
     * 4、@CookieValue ： 获取某个cookie的值，如果没有就会报错
     *      之前:
     *     Cookie[] c = request.getCookies();
     *     if(c.getName().equals("JSESSIONID")){
     *          String value = c.getValue();
     *     }
     *     现在：@CookieValue("JSESSIONID")String jsessionid
     *     属性：
     *      value ： 指定要获取的参数的key
     *      required : 标明此参数是否是必须的 ，默认为 true, 表示请求参数中必须包含对应的参数，若不存在，将抛出异常
     *      defaultValue : 指定参数的默认值，此时cookie可以没有这个key
     */
    @RequestMapping(value = "/to_book",method = RequestMethod.POST)
    public String getParams(@RequestParam(value = "username")String name,
                            @RequestParam("password")String password,
                            @RequestHeader("User-Agent")String userAgent,
                            @CookieValue("JSESSIONID")String jsessionid){
        System.out.println(name + password);
        System.out.println(userAgent);
        System.out.println(jsessionid);
        return "book";
    }

    /**
     * 如果我们的请求参数是一个POJO,使用 POJO 对象绑定请求参数值
     *
     * SpringMVC会自动地为这个POJO进行赋值
     * 1）、将POJO地每一个属性，从request参数中尝试获取出来，并进行封装即可
     * 2）、还可以级联封装，封装属性的属性
     */
    @RequestMapping(value = "/create_book",method = RequestMethod.POST)
    public String addBook(Book book,@RequestParam("bookName")String name,
                          @RequestParam("detail")String detail,HttpServletRequest request){
        System.out.println("add"+ book);
        System.out.println(name);
        System.out.println(detail);
        System.out.println(request.getCharacterEncoding());
        return "book";
    }

    /**
     * 使用 Servlet API 作为入参
     * SpringMVC 可以直接在方法参数上写原生地API
     * 例如：
     *      HttpServletRequest
     *      HttpServletResponse
     *      HttpSession
     *
     *      java.security.Principal
     *      Locale : 国际化有关的区域信息
     *
     *      InputStream ：  ServletOutputStream outputStream = response.getOutputStream();
     *      OutputStream :  ServletInputStream inputStream = request.getInputStream();
     *
     *      Reader :   BufferedReader reader = request.getReader();
     *      Writer :   PrintWriter writer = response.getWriter();
     */
    @RequestMapping(value = "/testAPI")
    public String testAPI(HttpSession session, HttpServletRequest request){
        request.setAttribute("requestMSg","request域中地信息");
        session.setAttribute("sessionMSG","session域中地信息");
        return "book";
    }

    /**
     * 数据输出 ： 如何将数据输出到页面？
     *
     */
    @RequestMapping(value = "/testModel")
    public String testModel(){
        return "";
    }
}
