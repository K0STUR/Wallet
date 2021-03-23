/*
package pl.edu.wszib.wallet.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.wallet.model.Expense;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ExpanseDAOImpl {

    @Autowired
    Connection connection;

    @Override
    public Expense getexpenseById(int id) {
        String sql = "SELECT * FROM tproduct WHERE id = ?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Expense(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("value"),
                        resultSet.getDate("date"),
                        resultSet.get("category")
                        );

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

}
*/
