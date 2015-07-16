/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kz.desktop.wsclients;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import kz.desktop.Main;
/**
 *
 * @author Nurakhmetov
 */
public class LoggerHandler implements SOAPHandler<SOAPMessageContext>{
    
	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close(MessageContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean handleFault(SOAPMessageContext arg0) {
		SOAPMessage message= arg0.getMessage();
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			message.writeTo(out);
                        Main.logger.debug(out.toString());
		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
		}
		return false;
	}

	@Override
	public boolean handleMessage(SOAPMessageContext arg0) {
		SOAPMessage message= arg0.getMessage();
		boolean isOutboundMessage=  (Boolean)arg0.get (MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if(isOutboundMessage){
                    Main.logger.debug("OUTBOUND MESSAGE");			
		}else{
                    Main.logger.debug("INBOUND MESSAGE");
		}
		try {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    message.writeTo(out);
                    Main.logger.debug(out.toString());
		} catch (SOAPException e) {
                    java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
		} catch (IOException e) {
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
		}
		return true;
	}
}
