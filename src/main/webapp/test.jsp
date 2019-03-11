<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.Article" %>
<%@ page import="com.codecool.web.model.Content" %>




<%
Content content = (Content) request.getAttribute("content");
for (Article article : content) {

%>
<p>
<%=article.getTitle()%>
<br>
<%=article.getText()%>
</p>

<%
}


%>


