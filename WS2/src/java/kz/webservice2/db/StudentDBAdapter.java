/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.webservice2.db;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import kz.webservice2.WS2Endpoint;
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
    
    public String submitStudent(String id, String[] subjects) throws IOException, SQLException{
        String  queryString = "INSERT INTO student2 (id, subjects) " +
                "VALUES(?, ?)";
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        DataOutputStream dataOutStream = new DataOutputStream(byteOutStream);
        for(String s : subjects){
            dataOutStream.writeUTF(s);
        }
        dataOutStream.close();
        byte[] subjectsAsBytes = byteOutStream.toByteArray();
        try (PreparedStatement stmt = conn.prepareStatement(queryString)) {
            stmt.setString(1, id);
            stmt.setBytes(2, subjectsAsBytes);
            stmt.execute();
        } catch(Exception e){
            WS2Endpoint.logger.error(e.getMessage(),e);
            return e.getMessage();
        }
        
        return "Success";
    }
    
    
}
