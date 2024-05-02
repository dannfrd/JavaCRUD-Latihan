package data_kelompok_4;

import java.sql.*;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DatabaseManager {

    java.sql.Connection conn;

    public DatabaseManager() {
        var koneksi = new connection();
        conn = koneksi.GetConnection();
    }

    public void FetchDatabase(DefaultTableModel model) {
        try {
            var koneksi = new connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            String query = "SELECT * FROM pmb";
            var res = stmt.executeQuery(query);

            if (res == null) {
                throw new NullPointerException("Table not found!");
            }

            model.setRowCount(0); // reset row

            while (res.next()) {
                int id = res.getInt("id");
                String nama = res.getString("nama");
                model.addRow(new Object[]{id, nama});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void DeleteTable(DefaultTableModel model, JTable table) {
        try {
    Connection conn = null;
    PreparedStatement stmt = null;
    
    try {
        var koneksi = new connection();
        conn = koneksi.GetConnection();

        var selectedRow = table.getSelectedRow();
        var selectedId = model.getValueAt(selectedRow, 0);

        String query = "DELETE FROM pmb WHERE ID = ?";
        stmt = conn.prepareStatement(query);
        stmt.setInt(1, (int) selectedId);
        
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Data berhasil dihapus.");
        } else {
            System.out.println("Data dengan ID tersebut tidak ditemukan.");
        }
    } catch (SQLException ex) {
        System.out.println("Gagal menghapus data: " + ex.getMessage());
    } finally {
        // Tutup statement dan koneksi
        if (stmt != null) {
            stmt.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
} catch (Exception ex) {
    ex.printStackTrace();
}

    }

    public void AddRow(String nama) {
        try {
            var koneksi = new connection();
            conn = koneksi.GetConnection();

            String query = "INSERT INTO pmb (nama) VALUES (?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, nama);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new SQLException("Failed to add row to the table!");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the SQLException appropriately, e.g., display a user-friendly error message
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void EditValue(int selectedID, String value) {
        try {
            var koneksi = new connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            String query = "UPDATE pmb SET nama = \"" + value + "\" WHERE id = " + selectedID;
            stmt.executeUpdate(query);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String ReadValue(int selectedID) {
        try {
            var koneksi = new connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            String query = "SELECT * FROM pmb WHERE id = " + selectedID;
            var res = stmt.executeQuery(query);

            if (res == null) {
                throw new NullPointerException("Table not found!");
            }

            while (res.next()) {
                return res.getString("nama");
            }

            return null;

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void FetchDatabaseSearchResult(DefaultTableModel model, String searchTarget) {
        try {
            var koneksi = new connection();
            conn = koneksi.GetConnection();

            var stmt = conn.createStatement();

            String query = "SELECT * FROM pmb WHERE nama LIKE '%" + searchTarget + "%'";
            var res = stmt.executeQuery(query);

            if (res == null) {
                throw new NullPointerException("Table not found!");
            }

            model.setRowCount(0); // reset row

            while (res.next()) {
                int id = res.getInt("id");
                String nama = res.getString("nama");
                model.addRow(new Object[]{id, nama});
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
