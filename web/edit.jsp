<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2026-05-12
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.student.entity.Student" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑学生</title>
    <style>
        body { font-family: Arial, sans-serif; padding: 20px; background:#f5f5f5; }
        .box { max-width: 600px; margin: 0 auto; background:#fff; padding: 20px; border-radius: 8px; }
        h1 { text-align:center; }
        label { display:block; margin-top: 12px; }
        input { width:100%; padding:10px; margin-top:6px; }
        .btn { margin-top:16px; padding:10px 16px; background:#4CAF50; color:#fff; border:none; border-radius:6px; cursor:pointer; }
        .btn:hover { background:#45a049; }
        .error { background:#ffecec; color:#c62828; padding:10px; border-radius:6px; margin:10px 0; }
        a { color:#4CAF50; text-decoration:none; }
    </style>
</head>
<body>
<div class="box">
    <h1>编辑学生</h1>

    <p><a href="/student?action=list">返回列表</a></p>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <div class="error"><%= error %></div>
    <%
        }

        Student s = (Student) request.getAttribute("student");
        if (s == null) {
    %>
    <div class="error">student 为空，无法编辑</div>
    <%
    } else {
    %>

    <form method="post" action="/student">
        <input type="hidden" name="action" value="edit"/>
        <input type="hidden" name="id" value="<%= s.getId() %>"/>

        <label>学号</label>
        <input name="studentId" value="<%= s.getStudentId() %>" required/>

        <label>姓名</label>
        <input name="name" value="<%= s.getName() %>" required/>

        <label>班级</label>
        <input name="className" value="<%= s.getClassName() %>" required/>

        <label>成绩</label>
        <input name="score" value="<%= s.getScore() %>" required/>

        <button class="btn" type="submit">保存修改</button>
    </form>

    <%
        }
    %>
</div>
</body>
</html>
