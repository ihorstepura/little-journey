package org.vector.littlejourney.database.service;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EntityHelper {

    private EntityHelper() {
    }

    public static int getEntityId(CallableStatement statement, int id) throws SQLException {

        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            id = resultSet.getInt(1);
        }
        return id;
    }
}
