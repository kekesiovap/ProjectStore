package itsovy.sk.Database;

import itsovy.sk.Bill.Bill;
import itsovy.sk.Item.Drink.DraftInterface;
import itsovy.sk.Item.Food.Fruit;
import itsovy.sk.Item.Goods.Piece;
import itsovy.sk.Item.Item;
import itsovy.sk.Main.Global;

import java.sql.*;

public class Database {

    private static Database db = new Database();

    private Database() {
    }

    public static Database getInstanceDb(){
        return db;
    }

    private Connection getConnection(){
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(Global.url, Global.username, Global.password);
            System.out.println("Connecting");
            return connection;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public void insertNewBill(Bill bill) throws SQLException {
        Connection conn = getConnection();
        String query = "INSERT INTO bill(totalPrice, date, time) VALUES(?,?,?)";
        String queryB = "INSERT INTO billitem(orderId, name, price, count, unit) VALUES(?,?,?,?,?)";
        try {
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, bill.getFinalPrice());
            statement.setDate(2, new java.sql.Date(bill.getDate().getTime()));
            statement.setTime(3, new java.sql.Time(bill.getDate().getTime()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Changes weren't made.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()){
                    for (Item item : bill.getList()) {
                        PreparedStatement stmt = conn.prepareStatement(queryB, Statement.RETURN_GENERATED_KEYS);
                        stmt.setDouble(1, generatedKeys.getLong(1));
                        stmt.setString(2, item.getName());
                        stmt.setDouble(3, item.getPrice());

                        if (item instanceof DraftInterface) {
                            stmt.setDouble(4, ((DraftInterface) item).getVolume());
                            stmt.setString(5, "l");
                        } else if (item instanceof Fruit) {
                            stmt.setDouble(4, ((Fruit) item).getWeight());
                            stmt.setString(5, "kg");
                        } else if(item instanceof Piece) {
                            stmt.setDouble(4, ((Piece) item).getAmount());
                            stmt.setString(5, "pcs");
                        }
                        stmt.executeUpdate();
                    }
                }
                else {
                    throw new SQLException("Error.");
                }
            }
            conn.commit();
            conn.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        }
    }
}

