/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.desktop.wsclients;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.jws.HandlerChain;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import javax.xml.ws.handler.Handler;
import kz.desktop.Main;
import kz.webservice1.ws1.ServiceService;
import kz.webservice1.ws1.*;

/**
 *
 * @author Nurakhmetov
 */
public class WS1Client {

    private ServiceService WS1Service = null;

    private String URLString = "http://localhost:9090/WS1/WS1.wsdl";
    private String namespace = "http://www.webservice1.kz/WS1";
    private String name = "serviceService";

    public WS1Client() throws MalformedURLException {

        URL url = new URL(URLString);
        QName qname = new QName(namespace, name);
        WS1Service = new ServiceService(url, qname);
        BindingProvider binding = (BindingProvider) WS1Service.getServiceSoap11();
        List<Handler> handlers = binding.getBinding().getHandlerChain();
        handlers.add(new LoggerHandler());
        binding.getBinding().setHandlerChain(handlers);
    }

    public String forwardRequest(String id, String name, String[] subjects) {
        String status = "Error";
        try {
            StudentRequest rq = new StudentRequest();
            rq.setId(id);
            rq.setName(name);
            for (String s : subjects) {
                rq.getSubject().add(s);
            }
            StudentResponse rs = WS1Service.getServiceSoap11().student(rq);
            status = rs.getStatus();
        } catch (Exception ex) {
            Main.logger.error(ex.getMessage(), ex);
            return ex.getMessage();
        }
        return status;
    }

    public String forwardRequest(String id, String name, String surname, String[] subjects) {
        String status = "Error";
        try {
            StudentRequest rq = new StudentRequest();
            rq.setId(id);
            rq.setName(name);
            rq.setSurname(surname);
            for (String s : subjects) {
                rq.getSubject().add(s);
            }
            status = WS1Service.getServiceSoap11().student(rq).getStatus();
        } catch (Exception ex) {
            Main.logger.error(ex.getMessage(), ex);
            return ex.getMessage();
        }
        return status;
    }

}
