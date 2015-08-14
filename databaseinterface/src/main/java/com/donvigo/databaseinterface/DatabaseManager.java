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

package com.donvigo.databaseinterface;

import android.content.Context;

import com.donvigo.databaseinterface.model.UserModel;

import java.util.List;


/**
 * Created by vgaidarji on 8/14/15.
 */
public class DatabaseManager implements DatabaseInterface {
    static private DatabaseManager instance;
    private DatabaseInterface databaseInterface;
    private Context context;

    private DatabaseManager() {
    }

    private DatabaseManager(Context context, DatabaseInterface databaseInterface) {
        this.context = context;
        this.databaseInterface = databaseInterface;
    }

    public static synchronized DatabaseManager getInstance() {
        return instance;
    }

    /**
     * Initializes DatabaseManager and opens database.
     * @param ctx
     * @param databaseInterface Database implementation.
     */
    public static void init(Context ctx, DatabaseInterface databaseInterface) {
        if(instance == null) {
            instance = new DatabaseManager(ctx, databaseInterface);
        }
        instance.open(ctx);
    }

    /**
     * Opens database.
     * @param context
     */
    @Override
    public void open(Context context) {
        if(databaseInterface == null) {
            throw new NullPointerException("databaseInterface can't be null");
        }
        databaseInterface.open(context);
    }

    /**
     * Closes database associated with DatabaseManager.
     */
    @Override
    public void close(){
        if(databaseInterface == null) {
            throw new NullPointerException("databaseInterface can't be null");
        }
        databaseInterface.close();
        context = null;
        instance = null;
    }

    /**
     * Returns list of users from database.
     * @return
     */
    @Override
    public List<UserModel> getUsers() {
        if(databaseInterface == null) {
            throw new NullPointerException("databaseInterface can't be null");
        }
        return databaseInterface.getUsers();
    }

    @Override
    public void addUsers(List<UserModel> users) {
        if(databaseInterface == null) {
            throw new NullPointerException("databaseInterface can't be null");
        }
        databaseInterface.addUsers(users);
    }
}
