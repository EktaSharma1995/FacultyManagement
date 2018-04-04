/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_5_db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author ektasharma
 */
public class CourseDB {
    //Method to add a record to the database table

    public static void addCourse(Course record) throws DBException {
        String sql = "Insert into course (code, title, credits, description) values (?,?,?,?)";
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, record.getCode().trim());
            ps.setString(2, record.getTitle().trim());
            ps.setInt(3, record.getCredits());
            ps.setString(4, record.getDescription().trim());
            ps.executeUpdate();
            connection.commit();

        } catch (SQLException sqle) {
            throw new DBException(sqle);
        }
    }

    //Method to extract the records from the table
    public static List<Course> getAllCourses() throws DBException {
        String sql = "Select * from course";
        List<Course> courseList = new ArrayList<Course>();
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course courseObj = new Course();

                courseObj.setCode(rs.getString("code"));
                courseObj.setTitle(rs.getString("title"));
                courseObj.setCredits(rs.getInt("credits"));
                courseObj.setDescription(rs.getString("description"));
                courseList.add(courseObj);
            }
            return courseList;
        } catch (SQLException sqle) {
            throw new DBException(sqle);
        }
    }

    public static List<Course> searchCourse(String title) throws DBException, Exception {
        String sql = "select * from course where title like ?";
        List<Course> courseList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%"+title+ "%");
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Course courseObj = new Course();
                courseObj.setCode(rs.getString("code"));
                courseObj.setTitle(rs.getString("title"));
                courseObj.setCredits(rs.getInt("credits"));
                courseObj.setDescription(rs.getString("description"));

                courseList.add(courseObj);
            }

            return courseList;

        } catch (SQLException sqle) {
            throw new DBException(sqle);
        }

    }

    public static void updateCourse(Course recordToUpdate) throws DBException, Exception {
        String sql = "update course set title = ?, credits = ?, description = ? where trim(code) = trim(?)";
        
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, recordToUpdate.getTitle());
            ps.setInt(2, recordToUpdate.getCredits());
            ps.setString(3, recordToUpdate.getDescription());
            ps.setString(4, recordToUpdate.getCode());
            int countOfUpdation = ps.executeUpdate();
            conn.commit();
            
            if (countOfUpdation <= 0) {
                throw new Exception("No record was updated !");
            }
      
            
            }  catch (Exception e) {
                 throw new Exception(e);
        }

    }

    public static void deleteCourse(String code) throws DBException, Exception {
        String sql = "delete from course where trim(code) = trim(?)";
        
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, code.trim().toUpperCase());
            
            int countOfDeletion = ps.executeUpdate();
            conn.commit();
            
            if (countOfDeletion <= 0) {
                throw new Exception("No record was deleted !");
            }
      
        } catch (SQLException sqle) {
            throw new DBException(sqle);
        }

    }

}
