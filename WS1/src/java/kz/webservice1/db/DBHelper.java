/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.webservice1.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import kz.webservice1.WS1Endpoint;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author Nurakhmetov
 */
public class DBHelper {
    private Connection fConnetion = null;
    private BasicDataSource ds;
    private String dbDriver;
    private static DBHelper dbHelper = null;
            
    public static DBHelper getDBHelper() {
        if (dbHelper == null) {
            dbHelper = new DBHelper();
        }
        return dbHelper;
    }

    public DBHelper() {
        Properties properties = new Properties();
        try{
            properties.load(DBHelper.class.getResourceAsStream("/application.properties"));
        } catch(IOException e){
            WS1Endpoint.logger.error(e.getMessage(), e);
        }
        String dbUrl = properties.getProperty("db_url");
        String dbUser = properties.getProperty("db_user");
        String dbPassword = properties.getProperty("db_password");
        dbDriver = properties.getProperty("db_driver");
        ds = prepareDataSource(dbUser, dbPassword, dbDriver, dbUrl);
    }
    
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(dbDriver);
            return ds.getConnection();

        } catch (SQLException e) {
            WS1Endpoint.logger.error(e.getMessage(), e);
            throw e;
        }
    }
    
    private BasicDataSource prepareDataSource(String user, String password, String driver, String url) {
        BasicDataSource datasource = new BasicDataSource();
        datasource.setDriverClassName(driver);
        datasource.setUsername(user);
        datasource.setPassword(password);
        datasource.setUrl(url);
        return datasource;
    }
    
    
    
}
