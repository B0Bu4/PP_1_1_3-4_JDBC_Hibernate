package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Ivan", "Petrov", (byte) 33);
        userDao.saveUser("Sergey", "Ivanov", (byte) 29);
        userDao.saveUser("Nataliya", "Petrova", (byte) 31);
        userDao.saveUser("Marina", "Ivanova", (byte) 28);

        userDao.getAllUsers();
        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}
