<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<jsp:include page="header.jsp" />
<title>TODOタスクの一覧</title>
</head>
<body>

   <jsp:include page="nav.jsp" />
    <div class="container">
    <tr>

        <td>
            <select name="status" id="status">
                <option value="5">完了</option>
                <option value="0">未着手</option>
                <option value="1">着手</option>
                <option value="2">完了</option>
                <option value="3">凍結</option>
            </select>
        </td>
    </tr>
    <table class="table table-bordered">


     <tr>
       <th>番号</th>
       <th>タイトル</th>
       <th>タスク内容</th>
       <th>期限</th>
       <th>最終更新</th>
       <th>ユーザーID</th>
       <th>状況</th>
       <th>詳細画面へ</th>
     </tr>
     <c:forEach items="${todoList }" var="dto">
       <tr>
         <td><c:out value="${dto.id }" /></td>
         <td><c:out value="${dto.title }" /></td>
         <td><c:out value="${dto.task}" /></td>
                <td><fmt:formatDate value="${dto.limitdate }"
                        pattern="yyyy-MM-dd" /></td>
                <td><fmt:formatDate value="${dto.lastupdate }"
                        pattern="yyyy-MM-dd" /></td>
                <td><c:out value="${dto.userid}" /></td>
                <td><c:out value="${dto.label}" /></td>
                <td><a href="detail?id=<c:out value="${dto.id}" />">詳細画面へ</a></td>


       </tr>
       </c:forEach>
       </table>

</body>
</html>