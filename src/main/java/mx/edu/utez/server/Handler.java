package mx.edu.utez.server;

import mx.edu.utez.database.ConexionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;

public class Handler {
    Connection con;
    PreparedStatement pstm;
    ResultSet rs;

    final String SQLCREATE = "INSERT INTO `institute`.`user` (`name`, `lastname` , `email`, `password`, date_registered, `status`) VALUES ( ?, ?, ?, ?, NOW(), ?);";

    final String SQLUPDATE = "UPDATE `user` SET name = ?, lastname = ?, email = ?, password = ? WHERE id = ?;";
    final String SQLDELETE = "DELETE FROM `user` WHERE id = ?;";
    public boolean insertUser(String name, String last, String email,  String pass, int st ){
        boolean res = false;
        try {
            con = ConexionMySQL.getConexion();
            pstm = con.prepareStatement(SQLCREATE);

            pstm.setString(1, name);
            pstm.setString(2, last);
            pstm.setString(3, email);
            pstm.setString(4, pass);
            pstm.setInt(5, st);
            if (pstm.executeUpdate() == 1) {
                res = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al insertar usuario" + e.getMessage());

        } finally {
            try {

                pstm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error en cierre");
            }
        }
        return res;
    }
    public boolean updateUser(String name, String last, String email,  String pass, int id){
        boolean res = false;
        try {
            con = ConexionMySQL.getConexion();
            pstm = con.prepareStatement(SQLUPDATE);

            pstm.setString(1, name);
            pstm.setString(2, last);
            pstm.setString(3, email);
            pstm.setString(4, pass);
            pstm.setInt(5, id);
            if (pstm.executeUpdate() == 1) {
                res = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario" + e.getMessage());

        } finally {
            try {

                pstm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error en cierre");
            }
        }
        return res;
    }
    public int countUser(int num){
     Connection con;
     PreparedStatement ps;
     ResultSet res;
     final String SQLREAD = "SELECT * FROM USER";
     int cont = num;
     try {
         con = ConexionMySQL.getConexion();
         ps = con.prepareStatement(SQLREAD);
         res = ps.executeQuery();

         while (res.next()) {

         cont++;
         }
         ps.close();
         con.close();

     } catch (SQLException e) {
        
         System.out.println("El error es : " + e.getMessage());
     }
     return cont;
 }
    public String readUser(int num){
        String or = "";
        Connection con;
        PreparedStatement ps;
        ResultSet res;
        final String SQLREAD = "SELECT * FROM USER  WHERE ID = ?";

        try {
            con = ConexionMySQL.getConexion();
            ps = con.prepareStatement(SQLREAD);
            ps.setInt(1,num);
            res = ps.executeQuery();

            while (res.next()) {

                or = "ID -> "+res.getString(1)+"\n"+"Nombre -> "+res.getString(2)+"\n"+"Apellido -> "+res.getString(3)+"\n"+"Email -> "+res.getString(4)+"\n";
            }
            ps.close();
            con.close();

        } catch (SQLException e) {

            System.out.println("El error es : " + e.getMessage());
        }
        return or;
    }
    public boolean deleteUser(int id){
        boolean res = false;
        try {
            con = ConexionMySQL.getConexion();
            pstm = con.prepareStatement(SQLDELETE);
            pstm.setInt(1, id);
            if (pstm.executeUpdate() == 1) {
                res = true;
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario" + e.getMessage());

        } finally {
            try {

                pstm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error en cierre");
            }
        }
        return res;
    }
}
