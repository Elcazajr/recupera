package mx.edu.utez.client;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws MalformedURLException, XmlRpcException, InterruptedException {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL("http://localhost:1201"));

        XmlRpcClient client = new XmlRpcClient();
        client.setConfig(config);
        Scanner sc = new Scanner(System.in);
        System.out.println("INICIANDO SISTEMA");
        for (int i = 0; i <= 3; i++) {
            System.out.print(".");
            Thread.sleep(1000);
        }
        System.out.println("");
        int status = 1;
        String res = "";
        System.out.println("Este es el menu de uso");
        System.out.println("1.- Registrar Usuario\n2.- Ver todos\n3.- Actualizar\n4.- Eliminar");
        System.out.println("Elije una opcion por favor.");
        int op = sc.nextInt();
        switch (op){
            case 1:
                System.out.println("Se registrara un usuario nuevo");
                System.out.println("Ingresa nombre");
                String nombre = sc.next();
                System.out.println("Ingresa apellido");
                String apellido = sc.next();
                System.out.println("Ingresa correo electronico");
                String email = sc.next();
                System.out.println("Ingresa contraseña");
                String pass = sc.next();
                Object[] params = {nombre,apellido,email,pass,status};
                boolean result= (boolean) client.execute("Handler.insertUser",params);
                System.out.println(res = result ? "Registro exitoso" : "Registro Erroneo");
                break;
            case 2:
                Object[] params1 ={0};

                int resu =  (Integer) client.execute("Handler.countUser",params1);
                System.out.println(resu);
                for (int i=0;i<=resu;i++){
                    Object[] params2 ={i};
                    String rea = (String) client.execute("Handler.readUser",params2);
                    System.out.println(rea);
                }
                System.out.println("listado exitoso");
                break;
            case 3:
                System.out.println("Se actualizara un usuario");
                System.out.println("Ingresa nombre");
                nombre = sc.next();
                System.out.println("Ingresa apellido");
                apellido = sc.next();
                System.out.println("Ingresa correo electronico");
                email = sc.next();
                System.out.println("Ingresa contraseña");
                pass = sc.next();
                System.out.println("Ingresar ID");
                int id = sc.nextInt();
                Object[] params2 = {nombre,apellido,email,pass,id};
                result= (boolean) client.execute("Handler.updateUser",params2);
                System.out.println(result ? "Actualizacion exitosa" : "Actualizacion erronea");
                break;
            case 4:
                System.out.println("Se eliminara un usuario");
                System.out.println("Ingresa un ID a eliminar");
                id = sc.nextInt();
                Object[] params3 = {id};
                result = (boolean) client.execute("Handler.deleteUser",params3);
                System.out.println(result ? "Eliminacion exitosa" : "Eliminacion erronea");
                break;
            default:
                System.out.println("Opcion invalida saliendo del sistema");
        }
    }
}
