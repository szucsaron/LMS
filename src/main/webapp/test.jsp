<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.Article" %>
<%@ page import="com.codecool.web.model.Content" %>




<%
Article article = (Article) request.getAttribute("article");
%>
<p> <%=article.getTitle()%> </p>
</p> <%=article.getText()%> </p>






