package com.student.servlet;

import com.student.dao.StudentDao;
import com.student.entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class StudentServlet extends HttpServlet {

    private final StudentDao studentDao = new StudentDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "list" -> doList(request, response);
            case "add" -> showAdd(request, response);
            case "edit" -> showEdit(request, response);
            case "delete" -> doDelete(request, response);
            default -> response.sendError(404, "Unknown action: " + action);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "add" -> doAdd(request, response);
            case "edit" -> doEdit(request, response);
            default -> response.sendError(404, "Unknown action: " + action);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = studentDao.selectAll();
        request.setAttribute("students", students);
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    private void showAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/add.jsp").forward(request, response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        String className = request.getParameter("className");
        String scoreStr = request.getParameter("score");

        // 简单校验
        if (studentId == null || studentId.isBlank()
                || name == null || name.isBlank()
                || className == null || className.isBlank()
                || scoreStr == null || scoreStr.isBlank()) {
            request.setAttribute("error", "请完整填写所有字段");
            request.getRequestDispatcher("/add.jsp").forward(request, response);
            return;
        }

        double score;
        try {
            score = Double.parseDouble(scoreStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "成绩必须是数字");
            request.getRequestDispatcher("/add.jsp").forward(request, response);
            return;
        }

        Student s = new Student(studentId, name, className, score);
        studentDao.insert(s);

        response.sendRedirect("/student?action=list");
    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendError(400, "Missing id");
            return;
        }

        int id = Integer.parseInt(idStr);
        Student s = studentDao.selectById(id);
        if (s == null) {
            response.sendError(404, "Student not found: id=" + id);
            return;
        }

        request.setAttribute("student", s);
        request.getRequestDispatcher("/edit.jsp").forward(request, response);
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String idStr = request.getParameter("id");
        String studentId = request.getParameter("studentId");
        String name = request.getParameter("name");
        String className = request.getParameter("className");
        String scoreStr = request.getParameter("score");

        if (idStr == null) {
            response.sendError(400, "Missing id");
            return;
        }

        if (studentId == null || studentId.isBlank()
                || name == null || name.isBlank()
                || className == null || className.isBlank()
                || scoreStr == null || scoreStr.isBlank()) {
            request.setAttribute("error", "请完整填写所有字段");
            // 回显
            Student s = new Student(studentId, name, className, 0.0);
            s.setId(Integer.parseInt(idStr));
            request.setAttribute("student", s);
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
            return;
        }

        double score;
        try {
            score = Double.parseDouble(scoreStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "成绩必须是数字");
            Student s = new Student(studentId, name, className, 0.0);
            s.setId(Integer.parseInt(idStr));
            request.setAttribute("student", s);
            request.getRequestDispatcher("/edit.jsp").forward(request, response);
            return;
        }

        Student st = new Student(studentId, name, className, score);
        st.setId(Integer.parseInt(idStr));
        studentDao.update(st);

        response.sendRedirect("/student?action=list");
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String idStr = request.getParameter("id");
        if (idStr == null) {
            response.sendError(400, "Missing id");
            return;
        }
        int id = Integer.parseInt(idStr);
        studentDao.delete(id);

        response.sendRedirect("/student?action=list");
    }
}