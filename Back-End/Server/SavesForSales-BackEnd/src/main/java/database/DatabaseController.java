/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import database.models.Usuario;

/**
 *
 * @author German le yo
 */
public class DatabaseController {

    private static final String DB_CONNECTION = "jdbc:mysql://root:0000@localhost:3306/savesforsales?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static DatabaseController instance;

    private final ConnectionSource connection;
    private final Dao<Usuario, Integer> userDao;

    private DatabaseController() throws SQLException {
        connection = new JdbcConnectionSource(DB_CONNECTION);
        userDao = DaoManager.createDao(connection, Usuario.class);
    }

    public static DatabaseController getInstance(){
        if(instance == null) try {
            instance = new DatabaseController();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    } 

    public Dao<Usuario, Integer> userDao(){
        return userDao;
    }

}
