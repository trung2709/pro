package DAO;

import Entity.Account;
import Entity.Category;
import Entity.Product;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public DAO() {
        try {
            con = new DBContext().getConnection();
        } catch (Exception e) {
        }
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM HE141261_Product";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    public List<Account> getAllAccount() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT * FROM HE141261_account";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "SELECT * FROM HE141261_Category";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),
                        rs.getString(2)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Product> getAllProductByCateID(String cid) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM HE141261_product WHERE cateId = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Product> getAllProductByName(String txt) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM HE141261_product WHERE [name] like ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + txt + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Product> getAllProductBySellID(int id) {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM HE141261_product WHERE [sell_ID] = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, id + "");
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public Product getProductById(int id) {
        Product p = null;
        String sql = "SELECT * FROM HE141261_product WHERE id =" + id;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p = new Product(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (SQLException e) {
        }
        return p;
    }

    public Account Login(String username, String password) {
        String sql = "SELECT * FROM HE141261_account WHERE [user] = ? and pass = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public Account checkAccount(String username) {
        String sql = "SELECT * FROM HE141261_account WHERE [user] = ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while (rs.next()) {
                return new Account(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5));
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public void signup(String username, String password) {
        String sql = "insert into HE141261_account values(?,?,0,0)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    

    public void removeProduct(int id) {
        String sql = "delete from HE141261_product where [id] = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, id + "");
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void addProduct(String name, String image,double price, String title, String des, int cateID, int sellID) {
        String sql = "insert into HE141261_product values(?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, image);
            ps.setString(3, price+"");
            ps.setString(4, title);
            ps.setString(5, des);
            ps.setString(6, cateID+"");
            ps.setString(7, sellID+"");
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void setSeller(int uID, int value){
        String sql = "update HE141261_Account set isSell = ? where uID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, value+"");
            ps.setString(2, uID+"");
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void removeAccount(int uID){
        String sql = "delete from HE141261_Account where uID = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, uID+"");
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public int discount(String captcha){
        String sql = "select value from HE141261_Voucher where captcha = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, captcha);
            rs = ps.executeQuery();
            while(rs.next()){
               return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
    
    public void loadOrderToDB(int uID, int phone){
        int orderID = getMaxOrderID() + 1;
        String sql = "insert into HE141261_Order values(?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, orderID+"");
            ps.setString(2, uID+"");
            ps.setString(3, phone+"");
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public int getMaxOrderID(){
        int orderID = -1;
        String sql1 = "select MAX(OrderID) from HE141261_Order";
        try {
            ps = con.prepareStatement(sql1);
            rs = ps.executeQuery();
            while(rs.next()){
                orderID = rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return orderID; 
    }
    
    public void loadOrderDetailToDB(int pID, double unitPrice, int quantity){
        int orderID = getMaxOrderID();
        String sql = "insert into HE141261_OrderDetail values(?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, orderID+"");
            ps.setString(2, pID+"");
            ps.setString(3, unitPrice+"");
            ps.setString(4, quantity+"");
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
