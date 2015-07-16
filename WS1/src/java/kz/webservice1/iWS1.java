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
public interface iWS1 {
    public String getStatus(String id, String name, String[] subject );
    
    public String getStatus(String id, String name, String surname, String[] subject );
    
}
