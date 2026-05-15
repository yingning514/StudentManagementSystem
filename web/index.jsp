<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2026-04-02
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--
这一行告诉 Tomcat：
- contentType="text/html" : 返回给浏览器的是 HTML 文本
- charset=UTF-8 : 使用 UTF-8 编码（支持中文）
- language="java" : 这个页面使用 Java 语言
-->
<html>
<head> <!-- head 标签：放置页面的元数据和配置信息 -->
    <meta charset="UTF-8">
    <!-- 字符编码设置：告诉浏览器使用 UTF-8 编码 -->
    <title>学生管理系统 - 首页</title>
    <!-- 页面标题：显示在浏览器的标签栏上 -->
    <style> /* CSS 样式表：定义页面的外观和布局 */

        body/* body 选择器：设置整个页面的样式 */{
            font-family: Arial, sans-serif; /* 字体：使用 Arial 字体，如果没有就使用 sans-serif（无衬线字体） */
            background-color: #f5f5f5; /* 背景颜色：#f5f5f5 是浅灰色 */
            margin: 0;/* 页面外边距：设为 0，这样页面内容会贴着浏览器边界 */
            padding: 20px; /* 页面内边距：页面内容距离浏览器边界 20 像素 */
        }

        .container   /* 类选择器：选择 class="container" 的元素 */{
            max-width: 800px; /* 最大宽度：容器最宽不超过 800 像素（手机上会更窄） */
            margin: 0 auto; /* 上下外边距为 0，左右 auto：这样容器会水平居中 */
            background-color: white;  /* 背景颜色：白色 */
            padding: 30px; /* 内边距：内容距离容器边界 30 像素 */
            border-radius: 80px;  /* 圆角：容器的四个角有 80 像素的圆角 */
            box-shadow:0 2px 10px rgba(0,0,0,0.1);
        }

        h1{
            color: #333;
            text-align: center;
        }
        p {
            text-align: center;
            color: #666;
        }
        .button-group{
            display: flex;
            justify-content: center;
            gap: 20px;
            margin-top: 30px;
        }
        a{
            display: inline-block;
            padding: 12px 30px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        a:hover/* 伪类选择器：选择鼠标悬停的 <a> 标签 */{
            background-color: #45a049;
        }
    </style>
</head>
<body><!-- body 标签：页面的主要内容区域 -->
    <div class="container"><!-- div 标签：一个容器，使用 class="container" 应用 CSS 样式 -->
        <h1>学生信息管理系统 v2.0</h1><!-- h1 标题：页面的主标题，emoji 表情会直接显示 -->
        <p>欢迎使用学生管理系统 Web 版本</p> <!-- p 段落：副标题文本 -->

        <div class="button-group"><!-- div 容器：使用 class="button-group" 应用 CSS 样式，用来放置两个按钮 -->
            <a href="/hello">测试 Servlet</a> <!--
              a 标签：超链接（按钮效果）
              - href="/hello" : 点击后跳转到 /hello（HelloServlet）
              - 显示文本：测试 Servlet
            -->
            <a href="/login">进入系统</a>
        </div>
    </div>
</body>
</html>
