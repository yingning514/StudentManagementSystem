package com.student.dao;


import com.student.entity.Student;
import com.student.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生数据访问层 - 所有数据库操作都在这里
 */
public class StudentDao {
    /**
     * 查询所有学生
     */
    public List<Student> selectAll() {
        List<Student> students = new ArrayList<>();
        String sql = "select id, student_id, name, class, score from student";

        Connection conn = DBUtil.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setClassName(rs.getString("class"));
                student.setScore(rs.getDouble("Score"));
                students.add(student);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("查询失败");
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }
        return students;
    }

    /**
     * 根据Id查询单个学生
     */
    public Student selsctById(Integer id) {
        //where id SQL命令只查询id列
        String sql = "select id, student_id, name, class, score, create_at from student where id = ?";
        Connection conn = DBUtil.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setClassName(rs.getString("class"));
                student.setScore(rs.getDouble("score"));
                return student;
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println("查询失败");
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }
        return null;
    }
    /**
     * 添加学生
     */
    public boolean insert(Student student) {
        String sql = "insert into student (student_id, name, class, score) values (?, ?, ?, ?)";
        Connection conn = DBUtil.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getClassName());
            pstmt.setDouble(4, student.getScore());

            int rows = pstmt.executeUpdate();
            pstmt.close();

            return rows > 0;
        } catch (SQLException e) {
            System.err.println("✗ 添加失败");
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }

        return false;
    }

    /**
     * 修改学生成绩
     */
    public boolean updateScore(Integer id, Double score) {
        String sql = "UPDATE student SET score = ? WHERE id = ?";
        Connection conn = DBUtil.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, score);
            pstmt.setInt(2, id);

            int rows = pstmt.executeUpdate();
            pstmt.close();

            return rows > 0;
        } catch (SQLException e) {
            System.err.println("✗ 修改失败");
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }

        return false;
    }

    /**
     * 删除学生
     */
    public boolean delete(Integer id) {
        String sql = "DELETE FROM student WHERE id = ?";
        Connection conn = DBUtil.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int rows = pstmt.executeUpdate();
            pstmt.close();

            return rows > 0;
        } catch (SQLException e) {
            System.err.println("✗ 删除失败");
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }

        return false;
    }

    /**
     * 根据学号查询
     */
    public Student selectByStudentId(String studentId) {
        String sql = "SELECT id, student_id, name, class, score FROM student WHERE student_id = ?";
        Connection conn = DBUtil.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setClassName(rs.getString("class"));
                student.setScore(rs.getDouble("score"));
                return student;
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            System.err.println("✗ 查询失败");
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }

        return null;
    }
}
