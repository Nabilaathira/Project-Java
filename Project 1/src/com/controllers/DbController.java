package com.controllers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.config.MyConfig;

import com.models.Produk;

public class DbController extends MyConfig {

    public static void getDatabase() {
        connection();
        try {
            // query = "SELECT nama, harga, stok FROM tb_produk ORDER BY ID DESC";
            query = "SELECT NAMA, HARGA, JUMLAH FROM produk";

            statement = connect.prepareStatement(query);
            resultSet = statement.executeQuery(query);

            while(resultSet.next()) {
                System.out.println(
                    String.format("%s - Rp.%d - Stok %d", resultSet.getString("NAMA"), resultSet.getInt("HARGA"), resultSet.getInt("JUMLAH"))
                );
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Produk getProdukByNama(String nama ) {
        Produk produk = null;
        connection();
        query = "SELECT * FROM produk WHERE ID=?";
        try {
            statement = connect.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, nama);
            resultSet = statement.executeQuery(nama);
            while (resultSet.next()) {
                produk = new Produk(resultSet.getInt("ID"), resultSet.getString("NAMA"), resultSet.getLong("HARGA"), resultSet.getInt("JUMLAH"));
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produk;
    }

    public static boolean insertData(String nama, long harga, int jumlah) {
        connection();
        query = "INSERT INTO produk (NAMA, HARGA, JUMLAH) VALUES (?, ?, ?)";
        try {
            statement = connect.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, nama);
            ((PreparedStatement) statement).setLong(2, harga);
            ((PreparedStatement) statement).setInt(3, jumlah);
            statement.executeUpdate(nama);
            statement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void updateNama(int id, String nama) {
        connection();
        query = "UPDATE produk SET nama=? WHERE id=?";
        try {
            statement = connect.prepareStatement(query);
            ((PreparedStatement) statement).setString(1, nama);
            ((PreparedStatement) statement).setInt(2, id);
            statement.executeUpdate(nama);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateHarga(int id, long harga) {
        connection();
        query = "UPDATE produk SET harga=? WHERE id=?";
        try {
            statement = connect.prepareStatement(query);
            ((PreparedStatement) statement).setLong(1, harga);
            ((PreparedStatement) statement).setInt(2, id);
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStok(int id, int jumlah) {
        connection();
        query = "UPDATE produk SET jumlah=? WHERE id=?";
        try {
            statement = connect.prepareStatement(query);
            ((PreparedStatement) statement).setInt(1, jumlah);
            ((PreparedStatement) statement).setInt(2, id);
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//     public static boolean deleteData(int id) {
//         connection();
//         query = "DELETE FROM produk WHERE nama=?";
//         try {
//             Connection connect;
//             Connection connection;
//             statement = connect.prepareStatement(query);
//             ((PreparedStatement) statement).setInt(1, id);
//             int affectedRowDelete = statement.executeUpdate(query);
//             if (affectedRowDelete > 0) {
//                 return true;
//             }
//             statement.close();
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return false;
//     }
// }
public static boolean deleteData(int id) {
    connection();
    query = "DELETE FROM tb_produk WHERE nama=?";
    try {
        statement = connect.prepareStatement(query);
        ((PreparedStatement) statement).setString(1, id);
        int affectedRowDelete = statement.executeUpdate(id);
        if (affectedRowDelete > 0) {
            return true;
        }
        statement.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}}