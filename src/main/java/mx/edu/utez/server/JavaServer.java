package mx.edu.utez.server;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.webserver.WebServer;

import java.io.IOException;

public class JavaServer {
    public static void main(String[] args) throws XmlRpcException, IOException, InterruptedException {
        System.out.print("Iniciando servidor RPC");

        for (int i=0; i<=2; i++){
            System.out.print(".");
            Thread.sleep (1000);
        }
        System.out.println("");
        PropertyHandlerMapping mapping = new PropertyHandlerMapping();
        mapping.addHandler("Handler", Handler.class);

        WebServer server = new WebServer(1201);
        server.getXmlRpcServer().setHandlerMapping(mapping);
        server.start();
        System.out.println("Esperando peticion");
    }
}
