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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.donvigo.databaseinterface.DatabaseInterface;
import com.donvigo.databaseinterface.model.UserModel;
import com.donvigo.ormlitedatabase.migration.Migration;
import com.donvigo.ormlitedatabase.migration.Migrations;
import com.donvigo.ormlitedatabase.model.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vgaidarji on 8/14/15.
 */
public class OrmLiteDatabase extends OrmLiteSqliteOpenHelper implements DatabaseInterface {

    private static final String DATABASE_NAME = "ormliteDB";
    private static final int DATABASE_VERSION = 1;
    private UserDAO userDAO;

    public OrmLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        onUpgrade(db, connectionSource, 0, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion,
                          int newVersion) {
        if (newVersion > oldVersion) {
            for (int ver = oldVersion; ver < newVersion; ++ver) {
                Migration migration = Migrations.MIGRATION_ARRAY[ver];
                migration.migrate(db, connectionSource);
            }
        }
    }

    @Override
    public void open(Context context) {
    }

    @Override
    public List<UserModel> getUsers() {
        return getUserDAO().getUserModels();
    }

    @Override
    public void addUsers(List<UserModel> userModels) {
        List<User> users = new ArrayList<>();
        for (UserModel u :
                userModels) {
            users.add(new User(
                    u.getId(),
                    u.getName(),
                    u.getAddress(),
                    u.getSsn(),
                    u.getEmail(),
                    u.getHomePhone(),
                    u.getWorkPhone()
            ));
        }
        getUserDAO().addUsers(users);
    }

    @Override
    public boolean isEncrypted() {
        return false;
    }

    @Override
    public void setDatabasePassword(String dbPassword) {
    }

    public UserDAO getUserDAO() {
        if(userDAO == null) {
            try {
                userDAO = getDao(User.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userDAO;
    }

}
