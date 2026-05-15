package com.student.main;

import com.student.dao.StudentDao;
import com.student.entity.Student;

import java.util.List;
import java.util.Scanner;

/**
 * 学生信息管理系统 - 命令行版
 * 主程序入口
 */
public class Main {
    private static StudentDao studentDao = new StudentDao();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   欢迎使用学生信息管理系统 v1.0        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();

        boolean isRunning = true;
        while (isRunning) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    displayAllStudents();
                    break;
                case "2":
                    addStudent();
                    break;
                case "3":
                    queryStudent();
                    break;
                case "4":
                    updateStudentScore();
                    break;
                case "5":
                    deleteStudent();
                    break;
                case "6":
                    System.out.println("✓ 感谢使用，再见！");
                    isRunning = false;
                    break;
                default:
                    System.out.println("✗ 输入有误，请重新选择");
            }
            System.out.println();
        }

        scanner.close();
    }

    /**
     * 显示菜单
     */
    private static void showMenu() {
        System.out.println("┌────────────────────────────────────────┐");
        System.out.println("│          请选择操作：                  │");
        System.out.println("│  1. 查看所有学生                       │");
        System.out.println("│  2. 添加新学生                         │");
        System.out.println("│  3. 查询学生信息                       │");
        System.out.println("│  4. 修改学生成绩                       │");
        System.out.println("│  5. 删除学生                           │");
        System.out.println("│  6. 退出系统                           │");
        System.out.println("└────────────────────────────────────────┘");
        System.out.print("请输入选项 (1-6): ");
    }

    /**
     * 显示所有学生
     */
    private static void displayAllStudents() {
        System.out.println("\n【查看所有学生】");
        List<Student> students = studentDao.selectAll();

        if (students.isEmpty()) {
            System.out.println("✗ 暂无学生数据");
            return;
        }

        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│  ID  │  学号  │  姓名  │  班级  │  成绩  │");
        System.out.println("├─────────────────────────────────────────────────┤");
        for (Student student : students) {
            System.out.println(student);
        }
        System.out.println("└─────────────────────────────────────────────────┘");
        System.out.println("✓ 共 " + students.size() + " 条记录");
    }

    /**
     * 添加学生
     */
    private static void addStudent() {
        System.out.println("\n【添加新学生】");

        System.out.print("请输入学号: ");
        String studentId = scanner.nextLine().trim();

        // 检查学号是否已存在
        if (studentDao.selectByStudentId(studentId) != null) {
            System.out.println("✗ 学号已存在，请重新输入");
            return;
        }

        System.out.print("请输入姓名: ");
        String name = scanner.nextLine().trim();

        System.out.print("请输入班级: ");
        String className = scanner.nextLine().trim();

        System.out.print("请输入成绩: ");
        Double score;
        try {
            score = Double.parseDouble(scanner.nextLine().trim());
            if (score < 0 || score > 100) {
                System.out.println("✗ 成绩必须在 0-100 之间");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ 成绩输入格式错误");
            return;
        }

        Student student = new Student(studentId, name, className, score);
        if (studentDao.insert(student)) {
            System.out.println("✓ 添加成功");
        } else {
            System.out.println("✗ 添加失败");
        }
    }

    /**
     * 查询学生
     */
    private static void queryStudent() {
        System.out.println("\n【查询学生信息】");
        System.out.print("请输入学号: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentDao.selectByStudentId(studentId);
        if (student == null) {
            System.out.println("✗ 未找到该学生");
            return;
        }

        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│ ID   : " + student.getId());
        System.out.println("│ 学号 : " + student.getStudentId());
        System.out.println("│ 姓名 : " + student.getName());
        System.out.println("│ 班级 : " + student.getClassName());
        System.out.println("│ 成绩 : " + student.getScore());
        System.out.println("└─────────────────────────────────────────────────┘");
    }

    /**
     * 修改学生成绩
     */
    private static void updateStudentScore() {
        System.out.println("\n【修改学生成绩】");
        System.out.print("请输入学号: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentDao.selectByStudentId(studentId);
        if (student == null) {
            System.out.println("✗ 未找到该学生");
            return;
        }

        System.out.println("当前成绩: " + student.getScore());
        System.out.print("请输入新成绩: ");

        Double newScore;
        try {
            newScore = Double.parseDouble(scanner.nextLine().trim());
            if (newScore < 0 || newScore > 100) {
                System.out.println("✗ 成绩必须在 0-100 之间");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ 成绩输入格式错误");
            return;
        }

        if (studentDao.update(student.getId(), newScore)) {
            System.out.println("✓ 修改成功");
        } else {
            System.out.println("✗ 修改失败");
        }
    }

    /**
     * 删除学生
     */
    private static void deleteStudent() {
        System.out.println("\n【删除学生】");
        System.out.print("请输入学号: ");
        String studentId = scanner.nextLine().trim();

        Student student = studentDao.selectByStudentId(studentId);
        if (student == null) {
            System.out.println("✗ 未找到该学生");
            return;
        }

        System.out.print("确认删除该学生吗？(y/n): ");
        String confirm = scanner.nextLine().trim().toLowerCase();

        if ("y".equals(confirm)) {
            if (studentDao.delete(student.getId())) {
                System.out.println("✓ 删除成功");
            } else {
                System.out.println("✗ 删除失败");
            }
        } else {
            System.out.println("✓ 已取消删除");
        }
    }
}