package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;
    public UserDaoJDBCImpl() {
        Util util = new Util();
        try {
            this.connection = util.getConnection();
            System.out.println("Соединение с БД установленно");
        } catch (SQLException e) {
            System.out.println("Соединение с БД не установленно!!!");
        }
    }

    public void createUsersTable() {
        Statement statement = null;
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(45),
                lastName VARCHAR(45),
                age INT)
                """;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Таблица успешно создана");
        } catch (SQLException e) {
            System.out.println("Не удалось создать таблицу");
        }
    }

    public void dropUsersTable() {
        Statement statement = null;
        String sql = """
                DROP TABLE IF EXISTS users
                """;
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Таблица успешно удалена");
        } catch (SQLException e) {
            System.out.println("Не удалось удалить таблицу");
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        PreparedStatement pStatement = null;

        String sql = """
                INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)
                """;
        try {
            pStatement = connection.prepareStatement(sql);

            pStatement.setString(1, name);
            pStatement.setString(2, lastName);
            pStatement.setByte(3, age);

            pStatement.executeUpdate();

            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Ошибка! Не удалось добавить user - " + name);
        }



    }

    public void removeUserById(long id) {

        PreparedStatement pStatement = null;
        String sql = """
                DELETE FROM users WHERE id=?
                """;
        try {
            pStatement = connection.prepareStatement(sql);
            pStatement.setLong(1, id);

            pStatement.executeUpdate();
            System.out.println("User c ID - "+ id + " удален");
        } catch (SQLException e) {
            System.out.println("Не удалось удалить user c ID "+ id);
        }

    }

    public List<User> getAllUsers() {

        List <User> userList = new ArrayList<>();

        Statement statement = null;

        String sql = "SELECT * FROM users";
        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
                System.out.println(user);
            }

        } catch (SQLException e) {
            System.out.println("Не удалось добавить новых users");
        }

        return userList;
    }

    public void cleanUsersTable() {
        Statement statement = null;
        String sql = """
                DELETE FROM users
                """;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица успешно очищена");
        } catch (SQLException e) {
            System.out.println("Не удалось очистить таблицу");
        }

    }
}
