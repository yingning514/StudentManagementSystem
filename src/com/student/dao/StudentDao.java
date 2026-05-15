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
        String sql = "SELECT id, student_id, name, class_name, score FROM student";

        Connection conn = DBUtil.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setStudentId(rs.getString("student_id"));
                student.setName(rs.getString("name"));
                student.setClassName(rs.getString("class_name"));  // ✅ 改成 class_name
                student.setScore(rs.getDouble("score"));
                students.add(student);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("✗ 查询失败");
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn);
        }
        return students;
    }

    /**
     * 根据Id查询单个学生
     */
    public Student selectById(Integer id) {
        String sql = "SELECT id, student_id, name, class_name, score FROM student WHERE id = ?";
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
                student.setClassName(rs.getString("class_name"));  // ✅ 改成 class_name
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

    /**
     * 添加学生
     */
    public boolean insert(Student student) {
        String sql = "INSERT INTO student (student_id, name, class_name, score) VALUES (?, ?, ?, ?)";
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
     * 修改学生成绩（仅修改成绩）
     */
    public boolean update(Integer id, Double score) {
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
     * 修改整个学生信息
     */
    public boolean update(Student student) {
        String sql = "UPDATE student SET student_id = ?, name = ?, class_name = ?, score = ? WHERE id = ?";
        Connection conn = DBUtil.getConnection();

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, student.getStudentId());
            pstmt.setString(2, student.getName());
            pstmt.setString(3, student.getClassName());
            pstmt.setDouble(4, student.getScore());
            pstmt.setInt(5, student.getId());

            int rows = pstmt.executeUpdate();
            pstmt.close();
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("✗ 更新学生失败");
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
        String sql = "SELECT id, student_id, name, class_name, score FROM student WHERE student_id = ?";
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
                student.setClassName(rs.getString("class_name"));  // ✅ 改成 class_name
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