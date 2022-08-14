package de.whoischris.gruppensystem.mysql;

import de.whoischris.gruppensystem.utils.Data;

import java.sql.*;

public class MySQL {

    private static Connection connection;
    private final Data data;
    public MySQL(Data data) {
        this.data = data;
        String host = data.getConnectionData("host");
        String port = data.getConnectionData("port");
        String database = data.getConnectionData("database");
        String user = data.getConnectionData("user");
        String password = data.getConnectionData("password");
        try {
            String connectionString = "jdbc:mysql://" + host + ":" + port + "/" + database;
            this.connection = DriverManager.getConnection(connectionString, user, password);
            System.out.println("MySQL verbunden");
            init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    static Connection getConnection() {
        return connection;
    }

    private void init() {
        String sql = "CREATE TABLE IF NOT EXISTS groups (id INT NOT NULL AUTO_INCREMENT,name VARCHAR(50),prefix VARCHAR(50),PRIMARY KEY (id))";
        try {
            getConnection().prepareStatement(sql).executeUpdate();
            sql = "CREATE TABLE IF NOT EXISTS players (id INT NOT NULL AUTO_INCREMENT,uuid VARCHAR(50),groupid INT(11),duration VARCHAR(50),PRIMARY KEY (id))";
            getConnection().prepareStatement(sql).executeUpdate();
            System.out.println("Tabellen erstellt");
            sql = "SELECT COUNT(*) AS gruppen FROM groups";
            PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            int count = 0;
            while (resultSet.next()) {
                count = resultSet.getInt("gruppen");
            }
            if(count == 0) {
                sql = "INSERT INTO groups (name, prefix) VALUES ('" + data.getDefaultGroupName() + "', '" + data.getDefaultGroupPrefix() + "')";
                getConnection().prepareStatement(sql).executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
