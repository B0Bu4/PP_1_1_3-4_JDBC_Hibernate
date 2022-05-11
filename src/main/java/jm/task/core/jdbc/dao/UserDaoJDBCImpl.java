package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        try (Statement statement = Util.getConnection().createStatement()){
            statement.execute("CREATE TABLE IF NOT EXISTS users ( " +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(45), lastName VARCHAR(45), age INT) ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {

        try (Statement statement = Util.getConnection().createStatement()){
            statement.execute("DROP TABLE IF EXISTS users");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement pStatement = Util.getConnection().prepareStatement("INSERT INTO users " +
                "(name, lastName, age) VALUES (?, ?, ?) ")){

            pStatement.setString(1, name);
            pStatement.setString(2, lastName);
            pStatement.setByte(3, age);

            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement pStatement = Util.getConnection().prepareStatement("DELETE FROM users WHERE id=?")){

            pStatement.setLong(1, id);
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List <User> userList = new ArrayList<>();

        try (Statement statement = Util.getConnection().createStatement()){

            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");

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
            e.printStackTrace();
        }

        return userList;
    }

    public void cleanUsersTable() {

        try (Statement statement = Util.getConnection().createStatement() ){
            statement.executeUpdate("TRUNCATE users");
            System.out.println("Таблица успешно очищена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
