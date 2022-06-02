package Main;
import java.sql.*;

public class Menu {
    Connection connect;
    ResultSet rs;
    PreparedStatement ps;
    Statement stmt;

    public Menu() {
        try {
            String url = "jdbc:mysql://localhost:3306/menuptpudding";
            String username = "root";
            String password = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException e) {
            System.out.println("Line 20 init gagal");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connect.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertQuery(String statement) {
        try {
            stmt = connect.createStatement();
            stmt.executeUpdate("insert into menu values (" + statement + ")");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteRow(String statement) {
    	try {
    		stmt = connect.createStatement();
			stmt.executeUpdate("delete from menu where kode = " + statement);
		}
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public void updateQuery(String kode, String nama, Integer harga, Integer stok) {
    	try {
    		stmt = connect.createStatement();
    		stmt.executeUpdate("update menu set nama = " + "'" + nama + "'" + ", harga = " + harga + ", stok = " + stok + " where kode = " + kode);
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public ResultSet getTabel() {
    	try {
    		ps = connect.prepareStatement("select * from menu");
    		rs = ps.executeQuery();
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return rs;
    }
    
    public ResultSet getKode() {
        try {
            ps = connect.prepareStatement("select kode from menu");
            rs = ps.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet getNama() {
        try {
            ps = connect.prepareStatement("select nama from menu");
            rs = ps.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet getHarga() {
        try {
            ps = connect.prepareStatement("select harga from menu");
            rs = ps.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public ResultSet getStok() {
        try {
            ps = connect.prepareStatement("select stok from menu");
            rs = ps.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
}
