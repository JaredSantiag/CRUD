package Conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Conexion {
    static Connection contacto = null;

    //Login
    public static String usuario;
    public static String password;
    public static boolean status=false;
       
    public static Connection getConexion(){
        status=false;
        String url="jdbc:sqlserver://TOSHIBA:1433;databaseName=TiendaY";
        try{
           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
        }catch (ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, "No Se Puede Establecer La Conexion... Revisar Driver\n"+e.getMessage(),"Error de Conexion",JOptionPane.ERROR_MESSAGE);
        }
        try {
            contacto=DriverManager.getConnection(url,Conexion.usuario,Conexion.password);
            status=true;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "No Se Puede Establecer La Conexion...\n"+e.getMessage(),"Error de Conexion",JOptionPane.ERROR_MESSAGE);
        }
        return contacto;
    }
    
    public static void setCuenta(String usuario, String password){
        Conexion.usuario=usuario;
        Conexion.password=password;
    }
    
    public static boolean getStatus(){
        return status;
    }
    
    //Metodo Para Realizar Consultas
    public static ResultSet Consulta(String consulta){
        Connection con=getConexion();
        Statement declara;
        try{
            declara=con.createStatement();
            ResultSet respuesta =declara.executeQuery(consulta);
            return respuesta;
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage(),"Error de Conexion",JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    
    //Metodo que comprueba si un elemento está repetido en Proveedor 
    private static boolean duplicadoProv(){
        return false;
    }
    
    //Metodo Para Insertar un Proveedor
    public static boolean InsertarProveedor(int noProv, String nombre, String calle, String colonia, String numero, String estado,
                        String municipio, String cp, String tel, String email) throws SQLException{
        if(duplicadoProv()){
            JOptionPane.showMessageDialog(null, "Este Elemento Ya Existe");
            return false;
        }
        PreparedStatement stmt = null;
        Connection con=getConexion();
        try{
            stmt=con.prepareStatement("insert into Proveedor "
                    + "values(?,?,?,?,?,?,?,?,?,?);");
            stmt.setInt(1,noProv);
            stmt.setString(2, nombre);
            stmt.setString(3, calle);
            stmt.setString(4, colonia);
            stmt.setInt(5, Integer.parseInt(numero));
            stmt.setString(6, estado);
            stmt.setString(7, municipio);
            stmt.setInt(8, Integer.parseInt(cp));
            stmt.setString(9, tel);
            stmt.setString(10, email);
            
            int retorno = stmt.executeUpdate();
            if (retorno>0){
               JOptionPane.showMessageDialog(null, "Insertado Correctamente");
               return true;
            }
            JOptionPane.showMessageDialog(null, "No se pudo insertar correctamente. Verificar los datos proporcionados");
            return false;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al insertar");
            return false;
        }
    } 
    
    public boolean insertarProducto(int a,int b,String c,Float d,String e,int f,String g,Float h,Float i,int j){
        PreparedStatement stmt = null;
        Connection con=getConexion();
        try{
            stmt=con.prepareStatement("insert into Producto values(?,?,?,?,?,?,?,?,?,?);");
             stmt.setInt(1, a);
             stmt.setInt(2, b);
             stmt.setString(3, c);
             stmt.setFloat(4, d);
             stmt.setString(5, e);
             stmt.setInt(6, f);
             stmt.setString(7, g);
             stmt.setFloat(8, h);
             stmt.setFloat(9, i);
             stmt.setInt(10, j);
            int retorno = stmt.executeUpdate();
            if (retorno>0){
               JOptionPane.showMessageDialog(null, "Insertado Correctamente");
               return true;
            }
            JOptionPane.showMessageDialog(null, "No se pudo insertar correctamente. Verificar los datos proporcionados");
            return false;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al insertar");
            return false;
        }        
    }
    
    public boolean insertarProvProducto(int a, int b, int c){
        PreparedStatement stmt = null;
        Connection con=getConexion();
        try{
            stmt=con.prepareStatement("insert into ProvProducto values(?,?,?);");
             stmt.setInt(1, a);
             stmt.setInt(2, b);
             stmt.setInt(3, c);
            int retorno = stmt.executeUpdate();
            if (retorno>0){
               JOptionPane.showMessageDialog(null, "Insertado Correctamente");
               return true;
            }
            JOptionPane.showMessageDialog(null, "No se pudo insertar correctamente. Verificar los datos proporcionados");
            return false;
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error al insertar");
            return false;
        }        
    }
    
    //Metodo Para Eliminar una Venta
    public static void BorrarProveedor(String noProv) throws SQLException{
        Connection con=getConexion();
        Statement st = con.createStatement();
        String sec="DELETE FROM Proveedor WHERE NoProveedor="+noProv;
        try{
            st.execute(sec);
        }catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    }
    
    //Metodo Para Actualizar Datos
    public static void ActualizarVenta(String a, String b, String c, String d, String e, String f, String g, String h, String i,String j)throws SQLException{
            PreparedStatement pps=Conexiones.Conexion.getConexion().prepareStatement("update Proveedor set NombreProveedor='"+b+"',Calle='"+c+"',Colonia='"+d+"',Numero="+e+",Estado='"+f+"',Municipio='"+g+"',CP="+h+",Telefono='"+i+"',Email='"+j+"' where NoProveedor="+a+";");
            pps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos Modificados");
    } 
}