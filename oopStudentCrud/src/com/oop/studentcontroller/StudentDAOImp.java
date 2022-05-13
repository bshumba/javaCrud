
package com.oop.studentcontroller;

import com.oop.studentdb.StudentDb;
import com.oop.studentmodel.Student;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class StudentDAOImp implements StudentDAO {

    @Override
    public void save(Student students) {
         
        try{
            Connection con = StudentDb.getConnection();
            String insertSql = "INSERT INTO testcon(fname, surname, username) VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(insertSql);
            ps.setString(1, students.getFname());
            ps.setString(2, students.getSurname());
            ps.setString(3, students.getUsername());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Student saved");
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "There was an error on insertion");
        }
    }

    @Override
    public void update(Student students) {
        
        try{
            Connection con = StudentDb.getConnection();
            String updateSql = "UPDATE testcon SET fname=?, surname=?, username=? WHERE testID=?";
            PreparedStatement ps = con.prepareStatement(updateSql);
            ps.setString(1, students.getFname());
            ps.setString(2, students.getSurname());
            ps.setString(3, students.getUsername());
            ps.setInt(4, students.getTestID());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Student updated");
            
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "There was an error on updating");
        }
    }

    @Override
    public void delete(Student students) {
        
        try{
            Connection con = StudentDb.getConnection();
            String delSql = "DELETE FROM testcon WHERE testID=?";
            PreparedStatement ps = con.prepareStatement(delSql);
            ps.setInt(1, students.getTestID());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Student deleted");
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "There was an error on deleting");
        }
    }

    @Override
    public Student get(int id) {
        
        Student st = new Student();
        
        try{
            Connection con = StudentDb.getConnection();
            String searchQuery = "SELECT * FROM testcon WHERE testID=?";
            PreparedStatement ps = con.prepareStatement(searchQuery);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
             
             while(rs.next()) {
                 
                 st.setTestID(rs.getInt("testID"));
                 st.setFname(rs.getString("fname"));
                 st.setSurname(rs.getString("surname"));
                 st.setUsername(rs.getString("username"));
                 
             }
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Error on searching");
        }
        
        return st;
    }

    @Override
    public List<Student> list() {
        
         List<Student> list = new ArrayList<Student>();
         
         try{
             Connection con = StudentDb.getConnection();
             String selectQuery = "SELECT * FROM testcon";
             PreparedStatement ps = con.prepareStatement(selectQuery);
             ResultSet rs = ps.executeQuery();
             
             while(rs.next()) {
                 Student st = new Student();
                 st.setTestID(rs.getInt("testID"));
                 st.setFname(rs.getString("fname"));
                 st.setSurname(rs.getString("surname"));
                 st.setUsername(rs.getString("username"));
                 
                 list.add(st);
             }
         } catch(Exception e) {
             JOptionPane.showMessageDialog(null, "Error on displaying data");
         }
         
         return list;
    }
    
}
