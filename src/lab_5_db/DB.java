/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab_5_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ektasharma
 */
public class DB {
    public static void addFaculty(Faculty record) throws DBException {
        String sqlQuery = "Insert into faculty (id, firstname, lastname) values (?,?,?)";
  
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, record.getId().trim());
            ps.setString(2, record.getFirstName().trim());
            ps.setString(3, record.getLastName());
            
            ps.executeUpdate();
            connection.commit();

        } catch (SQLException sqle) {
            throw new DBException(sqle);
        }
    }
    
    public static void assignFaculty(Assignment record) throws DBException {
        String sqlQuery = "Insert into assignment (id, code, term) values (?,?,?)";
  
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ps.setString(1, record.getId().trim());
            ps.setString(2, record.getCode().trim());
            ps.setString(3, record.getTerm());
            
            ps.executeUpdate();
            connection.commit();

        } catch (SQLException sqle) {
            throw new DBException(sqle);
        }
    }
    
    public static void updateAssignedFaculty(Assignment recordToUpdate) throws DBException, Exception {
        String sql = "update assignment set code = ?, term = ? where trim(id) = trim(?)";
        
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, recordToUpdate.getCode());
            ps.setString(2, recordToUpdate.getTerm());
            ps.setString(3, recordToUpdate.getId());
            int countOfUpdation = ps.executeUpdate();
            conn.commit();
            
            if (countOfUpdation <= 0) {
                throw new Exception("No record was updated !");
            }
      
            
            }  catch (Exception e) {
                 throw new Exception(e);
        }

    }
    
    public static List<Course> searchCourse(String title) throws DBException, Exception {
        String sql = "select code,title, credits, description,firstname, lastname, term from course c,faculty f, assignment a where c.code=a.code AND f.id=a.id";
        List<Course> courseList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            
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
}
