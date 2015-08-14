/*
 *    Copyright 2015 Veaceslav Gaidarji
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.donvigo.ormlitedatabase;

import com.donvigo.databaseinterface.model.UserModel;
import com.donvigo.ormlitedatabase.model.User;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDAO extends QuickDaoImpl<User, String> implements
        Dao<User, String> {

    public UserDAO(Class<User> dataClass) throws SQLException {
        super(dataClass);
    }

    public UserDAO(ConnectionSource connectionSource, Class<User> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public UserDAO(ConnectionSource connectionSource, DatabaseTableConfig<User> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        try {
            users = queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public List<UserModel> getUserModels() {
        List<User> users;
        List<UserModel> userModels = new ArrayList<>();

        try {
            users = queryForAll();
            for (User u :
                    users) {
                userModels.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userModels;
    }

    /**
     * @param users
     * @return true if at least one user was added successfully
     */
    public void addUsers(List<User> users) {
        for (User u : users) {
            addUser(u);
        }
    }

    private synchronized User addUser(User user) {
        try {
            createOrUpdate(user);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}