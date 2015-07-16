/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.webservice2;

/**
 *
 * @author Nurakhmetov
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import kz.webservice2.db.DBHelper;
import kz.webservice2.db.StudentDBAdapter;
import org.springframework.stereotype.Service;
@Service
public class WS2 implements iWS2{

    @Override
    public String getStatus(String id, String[] subject) {
        String status = "Error";
        Connection conn = null;
        try {
            conn = DBHelper.getDBHelper().getConnection();
            StudentDBAdapter studentDB = new StudentDBAdapter(conn);
            status = studentDB.submitStudent(id, subject);
            
        } catch (SQLException ex) {
            WS2Endpoint.logger.error(ex.getMessage(), ex);
            return ex.getMessage();
        } catch (ClassNotFoundException ex) {
            WS2Endpoint.logger.error(ex.getMessage(), ex);
            return ex.getMessage();
        } catch (Exception ex) {
            WS2Endpoint.logger.error(ex.getMessage(), ex);
            return ex.getMessage();
        }
        return status;
    }
    
}
