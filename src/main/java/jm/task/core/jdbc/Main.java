package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Ivan", "Petrov", (byte) 33);
        userDao.saveUser("Sergey", "Ivanov", (byte) 29);
        userDao.saveUser("Nataliya", "Petrova", (byte) 31);
        userDao.saveUser("Marina", "Ivanova", (byte) 28);

        for (User user : userDao.getAllUsers()) {
            System.out.println(user);
        }

        userDao.removeUserById(1);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

    }
}
