# StudentManagementSystem

学生信息管理系统 - Java Web 应用 (v2.0)

> **更新说明**：从 v1.0 (命令行版) 升级为 v2.0 (Web 版)

---

## 📋 项目概述

学生信息管理系统是一个完整的 JavaEE Web 应用，用于学生信息的增删改查管理。

### 版本对比

| 特性 | v1.0 (命令行) | v2.0 (Web) |
|------|-------------|-----------|
| 界面 | 控制台菜单 | Web 页面 (JSP) |
| 架构 | 单层 (DAO + Entity) | MVC 三层架构 |
| 数据库 | SQL Server | SQL Server |
| 部署 | Java 应用 | Tomcat 服务器 |
| 新增 | ✓ | ✓ |
| 编辑 | ✓ | ✓ |
| 删除 | ✓ | ✓ |
| 查询 | ✓ | ✓ |
| Web 界面 | ✗ | ✓ |

---

## 🚀 快速开始 (v2.0 Web 版)

### 环境要求

- Java JDK 21+
- Apache Tomcat 11.0+
- SQL Server 2019+
- IntelliJ IDEA 2024+

## 📁 项目结构

```
StudentManagementSystem/
├── src/
│   └── com/student/
│       ├── main/
│       │   └── Main.java              # v1.0 命令行主程序
│       ├── servlet/
│       │   └── StudentServlet.java    # v2.0 Web 控制层
│       ├── dao/
│       │   └── StudentDao.java        # 数据访问层
│       ├── entity/
│       │   └── Student.java           # 实体类
│       └── util/
│           └── DBUtil.java            # 数据库工具类
│
├── web/                               # v2.0 Web 文件
│   ├── WEB-INF/
│   │   ├── web.xml                    # Servlet 配置
│   │   └── lib/
│   │       └── mssql-jdbc-13.4.0.jre11.jar
│   ├── list.jsp                       # 学生列表页面
│   ├── add.jsp                        # 新增学生页面
│   └── edit.jsp                       # 编辑学生页面
│
├── lib/
│   └── mssql-jdbc-13.4.0.jre11.jar   # SQL Server JDBC 驱动
│
├── resources/
│   └── config.properties              # 数据库配置
│
└── README.md                          # 项目说明
```

---

## ✨ 功能特性 (v2.0)

### 学生列表页面 (list.jsp)
- 显示所有学生信息
- 支持"编辑"和"删除"操作
- "新增学生"按钮

### 新增学生页面 (add.jsp)
- 表单输入：学号、姓名、班级、成绩
- 客户端参数验证
- 服务端校验（成绩范围 0-100）

### 编辑学生页面 (edit.jsp)
- 回显学生当前信息
- 修改任意字段
- 提交更新

### 删��功能
- 确认删除对话框
- 删除后自动重定向到列表

---

## 🏗️ 架构设计 (v2.0)

### MVC 三层架构

```
Request
  ↓
StudentServlet (Controller)
  ├── doGet()  → 处理查询、编辑、删除
  ├── doPost() → 处理新增、修改提交
  ↓
StudentDao (Model/DAO)
  ├── selectAll()       → 查询所有学生
  ├── selectById(id)    → 按 ID 查询
  ├── insert(student)   → 新增学生
  ├── update(student)   → 更新学生
  └── delete(id)        → 删除学生
  ↓
Database (SQL Server)
  ↓
Response → JSP (View)
  ├── list.jsp
  ├── add.jsp
  └── edit.jsp
```

### 数据流

1. **查询列表**：`doGet(action=list)` → `selectAll()` → 渲染 `list.jsp`
2. **新增学生**：`GET /student?action=add` → 显示 `add.jsp` → `POST` → `insert()` → 重定向列表
3. **编辑学生**：`GET /student?action=edit&id=1` → `selectById()` → 显示 `edit.jsp` → `POST` → `update()` → 重定向列表
4. **删除学生**：`GET /student?action=delete&id=1` → `delete()` → 重定向列表

---

## 🔧 技术栈

### 后端
- **Language**: Java 21
- **Framework**: Jakarta EE (Servlet + JSP)
- **Database**: SQL Server 2019+
- **Driver**: Microsoft JDBC Driver for SQL Server 13.4.0
- **Server**: Apache Tomcat 11.0.20

### 前端
- HTML5
- CSS3
- JSP

### 工具
- IntelliJ IDEA 2025.1
- Git

---

## 📝 数据库设计

### student 表

| 字段 | 类型 | 说明 |
|------|------|------|
| id | INT | 主键，自增 |
| student_id | NVARCHAR(50) | 学号 |
| name | NVARCHAR(50) | 学生姓名 |
| class_name | NVARCHAR(50) | 班级 |
| score | FLOAT | 成绩 |

---

## 📄 版本历史

### v2.0 (2026-05-15) - Web 版本
- ✨ 完整的 MVC 架构
- ✨ Web 前端界面 (JSP)
- ✨ 前后端数据交互
- ✨ 表单验证和错误处理
- 🔧 升级到 Jakarta EE
- 🔧 支持 Tomcat 部署

### v1.0 (2026-04-24) - 命令行版本
- 基础的学生信息管理
- 命令行菜单界面
- JDBC 数据库连接

## 📜 License

MIT License - 可自由使用和修改

---

## 🎯 后续改进方向

- [ ] 前端美化 (Bootstrap/Layui)
- [ ] 分页功能
- [ ] 搜索和排序
- [ ] 成绩统计
- [ ] 班级管理
- [ ] 导出 Excel
- [ ] 用户认证登录
- [ ] 单元测试
- [ ] 日志框架
