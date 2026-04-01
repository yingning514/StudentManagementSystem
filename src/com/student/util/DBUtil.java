package com.student.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接工具类
 */
public class DBUtil {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    // 静态代码块：程序启动时加载配置
    static {
        try {
            Properties props = new Properties();
            // 从类路径加载配置文件
            InputStream input = DBUtil.class.getClassLoader()
                    .getResourceAsStream("config.properties");
            props.load(input);

            // 读取配置参数
            driver = props.getProperty("db.driver");
            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");

            // 加载数据库驱动
            Class.forName(driver);
            System.out.println("✓ 数据库驱动加载成功");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("✗ 配置文件加载失败或驱动加载失败");
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("✓ 数据库连接成功");
            return conn;
        } catch (SQLException e) {
            System.err.println("✗ 数据库连接失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 关闭数据库连接
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("✓ 数据库连接已关闭");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试连接
     */
    public static void main(String[] args) {
        Connection conn = getConnection();
        if (conn != null) {
            System.out.println("✓ 连接测试成功！");
            closeConnection(conn);
        } else {
            System.out.println("✗ 连接测试失败！");
        }
    }
}