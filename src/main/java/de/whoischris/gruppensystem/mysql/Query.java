package de.whoischris.gruppensystem.mysql;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Query {

    private static Connection connection;

    public Query() {
        connection = MySQL.getConnection();
    }

    public static Object select(String sql, Object[] params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(params.length > 0) {
                for(int i = 1; i < params.length + 1; i++) {
                    Object param = params[i-1];
                    if(param instanceof Integer) {
                        preparedStatement.setInt(i, (Integer) param);
                    } else if(param instanceof String) {
                        preparedStatement.setString(i, (String) param);
                    }
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                return resultSet.getObject(sql.split(" ")[1]);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }
    public static List<HashMap<String, Object>> selectAll(String sql, short cols, Object[] params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(params.length > 0) {
                for(int i = 1; i < params.length + 1; i++) {
                    Object param = params[i-1];
                    if(param instanceof Integer) {
                        preparedStatement.setInt(i, (Integer) param);
                    } else if(param instanceof String) {
                        preparedStatement.setString(i, (String) param);
                    }
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData metaData = resultSet.getMetaData();
            List<HashMap<String, Object>> result = new ArrayList<>();
            while(resultSet.next()) {
                HashMap<String, Object> set = new HashMap<>();
                for(int i = 0; i < cols; i++) {
                    set.put(metaData.getColumnName(i+1), resultSet.getObject(i+1));
                }
                result.add(set);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void execute(String sql, Object[] params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(params.length > 0) {
                for(int i = 1; i < params.length + 1; i++) {
                    Object param = params[i-1];
                    if(param instanceof Integer) {
                        preparedStatement.setInt(i, (Integer) param);
                    } else if(param instanceof String) {
                        preparedStatement.setString(i, (String) param);
                    }
                }
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Integer insert(String sql, Object[] params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if(params.length > 0) {
                for(int i = 1; i < params.length + 1; i++) {
                    Object param = params[i-1];
                    if(param instanceof Integer) {
                        preparedStatement.setInt(i, (Integer) param);
                    } else if(param instanceof String) {
                        preparedStatement.setString(i, (String) param);
                    }
                }
            }
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    public static boolean existsInTable(String sql, Object[] params) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            if(params.length > 0) {
                for(int i = 1; i < params.length + 1; i++) {
                    Object param = params[i-1];
                    if(param instanceof Integer) {
                        preparedStatement.setInt(i, (Integer) param);
                    } else if(param instanceof String) {
                        preparedStatement.setString(i, (String) param);
                    }
                }
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
