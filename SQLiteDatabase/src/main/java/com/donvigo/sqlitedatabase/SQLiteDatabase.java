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

package com.donvigo.sqlitedatabase;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.donvigo.databaseinterface.DatabaseInterface;
import com.donvigo.databaseinterface.model.User;

import java.util.List;

/**
 * Created by vgaidarji on 8/14/15.
 */
public class SQLiteDatabase extends SQLiteOpenHelper implements DatabaseInterface {

    private static SQLiteDatabase database;
    private static final String DATABASE_NAME = "sqliteDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_USERS = "users";
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_USER_ADDRESS = "address";
    private static final String KEY_USER_SSN = "ssn";
    private static final String KEY_USER_EMAIL = "email";
    private static final String KEY_USER_HOME_PHONE = "homePhone";
    private static final String KEY_USER_WORK_PHONE = "workPhone";

    public SQLiteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public SQLiteDatabase(Context context, String name, CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void open(Context context) {
        if (database == null) {
            database = new SQLiteDatabase(context.getApplicationContext());
        }
        getWritableDatabase();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS +
                "(" +
                KEY_USER_ID + " INTEGER PRIMARY KEY," +
                KEY_USER_NAME + " TEXT," +
                KEY_USER_ADDRESS + " TEXT," +
                KEY_USER_SSN + " TEXT," +
                KEY_USER_EMAIL + " TEXT," +
                KEY_USER_HOME_PHONE + " TEXT," +
                KEY_USER_WORK_PHONE + " TEXT" +
                ")";

        db.execSQL(CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            onCreate(db);
        }
    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
