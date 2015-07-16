/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.webservice1.db;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import kz.webservice1.WS1Endpoint;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Nurakhmetov
 */
public class StudentDBAdapter {
    
    private Connection conn = null;

    public StudentDBAdapter(Connection conn) {
        this.conn = conn;
    }
    
    public String submitStudent(String id, String name, String[] subjects) throws IOException, SQLException{
        String  queryString = "INSERT INTO student (id, name, subjects) " +
                "VALUES(?, ?, ?)";
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        DataOutputStream dataOutStream = new DataOutputStream(byteOutStream);
        for(String s : subjects){
            dataOutStream.writeUTF(s);
        }
        dataOutStream.close();
        byte[] subjectsAsBytes = byteOutStream.toByteArray();
        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setBytes(3, subjectsAsBytes);
            stmt.execute();
        } catch(Exception e){
            WS1Endpoint.logger.error(e.getMessage(),e);
            return e.getMessage();
        }
        
        return "Success";
    }
    
    public String submitStudent(String id, String name, String surname, String[] subjects) throws IOException, SQLException{
        String  queryString = "INSERT INTO student (id, name, surname, subjects) " +
                "VALUES(?, ?, ?, ?)";
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        DataOutputStream dataOutStream = new DataOutputStream(byteOutStream);
        for(String s : subjects){
            dataOutStream.writeUTF(s);
        }
        dataOutStream.close();
        byte[] subjectsAsBytes = byteOutStream.toByteArray();
        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, surname);
            stmt.setBytes(4, subjectsAsBytes);
            stmt.execute();
        } catch(Exception e){
            WS1Endpoint.logger.error(e.getMessage(),e);
            return e.getMessage();
        }
        
        return "Success";
    }
    
    public String updateStudent(String id, String status) throws IOException, SQLException{
        String  queryString = "UPDATE student SET status = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {
            stmt.setString(1, status);
            stmt.setString(2, id);
            stmt.execute();
        } catch(Exception e){
            WS1Endpoint.logger.error(e.getMessage(),e);
            return e.getMessage();
        }
        
        return "Success";
    }
    
    public String checkGuid(String guid) throws SQLException {
        String checkQuery = "SELECT * FROM student WHERE id = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.prepareStatement(checkQuery);
            stmt.setString(1, guid);
            rs = stmt.executeQuery();
            rs.next();
            if (!rs.first()) {
                return "Success";
            } 
        } catch (SQLException e) {
            WS1Endpoint.logger.error(e.getMessage(),e);
            return e.getMessage();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
                return "Duplicate";
    }
    
    
}
