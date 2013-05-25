package middleware.igor_marques_basic_remote_patterns.soap;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPBodyElement;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.Name;

import middleware.igor_marques_basic_remote_patterns.client.AbstractClientRequestHandler;
import middleware.igor_marques_basic_remote_patterns.common.Message;

public class SOAPClientRequestHandler extends AbstractClientRequestHandler{
	private String namespaceURI;
	
	public SOAPClientRequestHandler(String namespaceURI) {
		this.namespaceURI = namespaceURI;
	}
	
	@Override
	public void sendMessage(Message message, URL endpoint) throws SOAPException {
		
		//criando conexao 
	     SOAPConnectionFactory sfc = SOAPConnectionFactory.newInstance();
	     SOAPConnection connection = sfc.createConnection();
	      
		//criando mensagem		
		SOAPMessage soapMessage = null;
		
		soapMessage = MessageFactory.newInstance().createMessage();	
			
//		SOAPPart soapPart = soapMessage.getSOAPPart();
//		SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
//		SOAPHeader header = soapEnvelope.getHeader();	
//		SOAPBody soapBody = soapEnvelope.getBody();
		
		//soapEnvelope.addNamespaceDeclaration("ns1", "http://tempuri.org/");
		
		//header

		//MimeHeaders mimeHeader = soapMessage.getMimeHeaders();
	
		//mimeHeader.setHeader("SOAPAction", namespaceURI + message.method);
		
		
		StringBuilder result = new StringBuilder();
		
		result.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<soap:Envelope xmlns:xsi=\"http://w3.org/2001/XMLSchema-instance\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">"
		        + "<soap:Header>"
		        + "</soap:Header>\n");
		
		result.append("<soap:Body>\n");
		
		result.append("<" + message.method + " xmlns=\"" + namespaceURI + "\">\n");
		
		for(String p:message.getParamsName()){
			result.append("<" + p + ">" + message.getParam(p) + "</" + p + ">\n");
		}
		
		result.append("</" + message.method + ">\n");
		result.append("</soap:Body></soap:Envelope>");

		
		InputStream is = new ByteArrayInputStream(result.toString().getBytes());
		try {
			soapMessage = MessageFactory.newInstance().createMessage(null, is);
			MimeHeaders mimeHeader = soapMessage.getMimeHeaders();
			
			mimeHeader.setHeader("SOAPAction", namespaceURI + "/"+ message.method);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		//Enviando mensagem
	     SOAPMessage response = connection.call(soapMessage, endpoint);		
	     
		 try {
			response.writeTo(System.out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		
	}
	public static void main(String[] args) {
		String namespace= "http://www.webserviceX.NET";

		
		SOAPClientRequestHandler sh = new SOAPClientRequestHandler(namespace);
		
		Message message = new Message();
		
		message.setMethod("GetWeather");

	    message.setObjectID("");
	    message.setObject("");
	    
		//String namespace= "http://tempuri.org";
//	   	message.setMethod("CalcPrecoPrazo");
//	    message.setParam("nCdEmpresa", "");
//	    message.setParam("sDsSenha", "");
//	    message.setParam("nCdServico", "40010");
//	    message.setParam("sCepOrigem", "59040226");
//	    message.setParam("sCepDestino", "59151250");
//	    message.setParam("nVIPeso", "1");
//	    message.setParam("nCdFormato", "1");
//	    message.setParam("nVIComprimento", "80.5");
//	    message.setParam("nVIAltura", "10.5");
//	    message.setParam("nVILargura", "20.5");
//	    message.setParam("nVIDiametro", "0");
//	    message.setParam("sCdMaoPropria", "N");
//	    message.setParam("nVIValorDeclarado", "0");
//	    message.setParam("sCdAvisoRecebimento", "N");
		
		message.setParam("CountryName", "Brazil");
		message.setParam("CityName", "Natal");

//		
////		message.setParams(new String[]{"hue", "br"});
//		try {
//			sh.sendMessage(message, new URL("http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx"));
//		} catch (MalformedURLException | SOAPException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
		try {
			sh.sendMessage(message, new URL("http://www.webservicex.net/globalweather.asmx"));
			//sh.sendMessage(message, new URL("http://ws.correios.com.br/calculador/calcprecoprazo.asmx"));
		} catch (MalformedURLException | SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public void listenMessage() {
		// TODO ouvir messages soap
		//abrir threaad
		//ficar ouvindo
			//message chegou
			//transforma em message
			//passa pro ClientRequestListener	
	}
	
}