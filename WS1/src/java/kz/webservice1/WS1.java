/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.webservice1;

/**
 *
 * @author Nurakhmetov
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kz.webservice1.db.DBHelper;
import kz.webservice1.db.StudentDBAdapter;
import kz.webservice1.wsclient.WS2Client;
import org.springframework.stereotype.Service;
@Service
public class WS1 implements iWS1{

    @Override
    public String getStatus(String id, String name, String[] subject) {
        String status = "Error";
        Connection conn = null;
        try {
            conn = DBHelper.getDBHelper().getConnection();
            StudentDBAdapter studentDB = new StudentDBAdapter(conn);
            status = studentDB.checkGuid(id);
            WS1Endpoint.logger.debug("Trying to check for duplicate ... \t" + "Result is: "+status);
            if(status.equals("Success")){
                status = studentDB.submitStudent(id, name, subject);
                WS1Endpoint.logger.debug("Trying to submit student info ... \t" + "Result is: "+status);
                WS2Client ws2Client = new WS2Client();
                
                status = ws2Client.forwardRequest(id, subject);
                WS1Endpoint.logger.debug("Trying to forward request to WS2 ... \t" + "Result is: "+status);
                status = studentDB.updateStudent(id, status);
                WS1Endpoint.logger.debug("Trying to update forward request result ... \t" + "Result is: "+status);
            }
        } catch (SQLException ex) {
            WS1Endpoint.logger.error(ex.getMessage(), ex);
            return ex.getMessage();
        } catch (ClassNotFoundException ex) {
            WS1Endpoint.logger.error(ex.getMessage(), ex);
            return ex.getMessage();
        } catch (IOException ex) {
            WS1Endpoint.logger.error(ex.getMessage(), ex);
            return ex.getMessage();
        }
        return status;
    }

    @Override
    public String getStatus(String id, String name, String surname, String[] subject) {
        String status = "Error";
        Connection conn = null;
        try {
            conn = DBHelper.getDBHelper().getConnection();
            StudentDBAdapter studentDB = new StudentDBAdapter(conn);
            status = studentDB.checkGuid(id);
            WS1Endpoint.logger.debug("Trying to check for duplicate ... \t" + "Result is: "+status);
            if(status.equals("Success")){
                status = studentDB.submitStudent(id, name, surname, subject);
                WS1Endpoint.logger.debug("Trying to submit student info ... \t" + "Result is: "+status);
                WS2Client ws2Client = new WS2Client();
                
                status = ws2Client.forwardRequest(id, subject);
                WS1Endpoint.logger.debug("Trying to forward request to WS2 ... \t" + "Result is: "+status);
                status = studentDB.updateStudent(id, status);
                WS1Endpoint.logger.debug("Trying to update forward request result ... \t" + "Result is: "+status);
            }
        } catch (SQLException ex) {
            WS1Endpoint.logger.error(ex.getMessage(), ex);
            return ex.getMessage();
        } catch (ClassNotFoundException ex) {
            WS1Endpoint.logger.error(ex.getMessage(), ex);
            return ex.getMessage();
        } catch (IOException ex) {
            WS1Endpoint.logger.error(ex.getMessage(), ex);
            return ex.getMessage();
        }
        return status;
    }
    
}
