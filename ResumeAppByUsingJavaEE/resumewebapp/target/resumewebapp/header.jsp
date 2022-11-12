<%@ page import="com.company.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 03/02/22
  Time: 04:25
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%
    User user= (User) session.getAttribute("loggedInUser");
%>
<%="Wellcome, "+user.getName()+"!!!"%>