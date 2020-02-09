<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<%--<a href="${pageContext.request.contextPath}/test/to_login"><h1>to_login</h1></a>--%>

<%--<form action="${pageContext.request.contextPath}/test/to_book" method="post">--%>
<%--    <label>--%>
<%--        用户名<input type="text" is="username" name="username" />--%>
<%--    </label>--%>
<%--    <label for="password">--%>
<%--        密码<input type="password" id="password" name="password" />--%>
<%--    </label>--%>
<%--    <input type="submit" id="sub_btn" value="提交"/>--%>
<%--</form>--%>

<%--
发起图书的增删改查请求，使用Rest风格的URL地址

     /book/1  ： GET请求-----查询1号图书
     /book/1  : PUT请求------更新1号图书
     /book/1  : DELETE请求---删除1号图书
     /book    : POST请求-----增加图书
--%>
<form action="${pageContext.request.contextPath}/test/create_book" method="post">
    书名：<input type="text" name="bookName"/><br/>
    作者：<input type="text" name="author" /><br/>
    价格：<input type="text" name="price"/><br/>
    省：<input type="text" name="address.province"/>
    市：<input type="text" name="address.city"/>
    街道：<input type="text" name="address.street"/>
    说明：<input type="text" name="detail"/><br/>
    <input type="submit" id="sub1" value="增加图书"/>
</form>
<%--<form action="${pageContext.request.contextPath}/book/1" method="get">--%>
<%--    <input type="submit" id="sub2" value="查询图书"/>--%>
<%--</form>--%>
<%--&lt;%&ndash;--%>
<%--从页面发送put、delete请求--%>
<%--Spring提供了对Rest的支持--%>
<%--步骤：--%>
<%--1）、SpringMVC提供了一个过滤器（HiddenHttpMethodFilter），把普通的请求转化为规定形式的请求--%>
<%--2）、发送delete、put类型的请求的步骤--%>
<%--        1、新建一个post类型的表单--%>
<%--        2、表单项中携带一个_method的参数--%>
<%--        3、_method的值就是delete或者put--%>
<%--&ndash;%&gt;--%>
<%--<form action="${pageContext.request.contextPath}/book/1" method="post">--%>
<%--    <input type="hidden" name="_method" value="delete"/>--%>
<%--    <input type="submit" id="sub3" value="删除图书"/>--%>
<%--</form>--%>
<%--<form action="${pageContext.request.contextPath}/book/1" method="post">--%>
<%--    <input type="hidden" name="_method" value="put"/>--%>
<%--    <input type="submit" id="sub4" value="更新图书"/>--%>
<%--</form>--%>

<%--<a href="${pageContext.request.contextPath}/test/testAPI">book.jsp</a>--%>
</body>
</html>
