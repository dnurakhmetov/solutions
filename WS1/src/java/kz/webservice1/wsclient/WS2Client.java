/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.webservice1.wsclient;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import kz.webservice2.ws2.*;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
/**
 *
 * @author Nurakhmetov
 */
public class WS2Client {
    private ServiceService WS2Service = null;

    private String URLString = "http://localhost:9090/WS2/WS2.wsdl";
    private String namespace = "http://www.webservice2.kz/WS2";
    private String name = "serviceService";
    public static Logger logger = null;
    
    public WS2Client() throws MalformedURLException {
        DOMConfigurator.configure(WS2Client.class.getResource("/log4j.xml"));
        logger = Logger.getLogger("WS2Client");
        URL url = new URL(URLString);
        QName qname = new QName (namespace, name);
        //Service service = Service.create(url,qname);
        //WS2Service = service.getPort(ServiceService.class);
        WS2Service = new ServiceService(url, qname);
    }
    
    public String forwardRequest(String id, String[] subjects){
        String status = "Error";
        StudentRequest rq = new StudentRequest();
        rq.setId(id);
        for(String s : subjects){
            rq.getSubject().add(s);
        }
        status = WS2Service.getServiceSoap11().student(rq).getStatus();
        return status;
    }    
    
    
}
