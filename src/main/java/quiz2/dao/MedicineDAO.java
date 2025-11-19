package quiz2.dao;

import quiz2.controller.MedicineServlet;
import quiz2.model.Medicine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicineDAO {

    private static final Logger LOGGER = Logger.getLogger(MedicineDAO.class.getName());

    
    public List<Medicine> getAllMedicines() {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT m.*, c.name AS categoryName " +
                    "FROM medicines m " +
                    "LEFT JOIN categories c ON m.categoryId = c.id";

        try (Connection conn = DBConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Medicine med = new Medicine(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("categoryId"),
                    rs.getInt("stock")
                );
                med.setCategoryName(rs.getString("categoryName")); 
                list.add(med);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public Medicine getMedicineById(int id) {
        String sql = "SELECT m.*, c.name AS categoryName " +
                    "FROM medicines m " +
                    "LEFT JOIN categories c ON m.categoryId = c.id " +
                    "WHERE m.id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Medicine med = new Medicine(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("categoryId"),
                        rs.getInt("stock")
                    );
                    med.setCategoryName(rs.getString("categoryName")); 
                    return med;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    
    public void createMedicine(Medicine med) {
        String sql = "INSERT INTO medicines (name, price, stock, categoryId) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, med.getName());
            stmt.setDouble(2, med.getPrice());
            stmt.setInt(3, med.getStock());
            stmt.setInt(4, med.getCategoryId());

            LOGGER.info("Executing SQL: " + stmt.toString());

            int affected = stmt.executeUpdate();
            LOGGER.info("Rows affected: " + affected);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting medicine into DB", e);
        }
    }


    
    public boolean updateMedicine(Medicine med) {
        String sql = "UPDATE medicines SET name = ?, price = ?, categoryId = ?, stock = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, med.getName());
            stmt.setDouble(2, med.getPrice());
            stmt.setInt(3, med.getCategoryId());
            stmt.setInt(4, med.getStock());
            stmt.setInt(5, med.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteMedicine(int id) {
        String sql = "DELETE FROM medicines WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void increaseStock(int id) {
        String sql = "UPDATE medicines SET stock = stock + 1 WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error increasing stock", e);
        }
    }

    
    public void decreaseStock(int id) {
        String sql = "UPDATE medicines SET stock = CASE WHEN stock > 0 THEN stock - 1 ELSE 0 END WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error decreasing stock", e);
        }
    }

    public List<Medicine> searchMedicines(String keyword) {
        List<Medicine> list = new ArrayList<>();
        String sql = "SELECT m.*, c.name AS categoryName " +
                    "FROM medicines m " +
                    "LEFT JOIN categories c ON m.categoryId = c.id " +
                    "WHERE m.name LIKE ?";

        try (Connection conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Medicine med = new Medicine(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("categoryId"),
                    rs.getInt("stock")
                );
                med.setCategoryName(rs.getString("categoryName"));
                list.add(med);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
