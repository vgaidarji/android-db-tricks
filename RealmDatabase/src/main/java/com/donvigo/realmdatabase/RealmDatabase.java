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

package com.donvigo.realmdatabase;

import android.content.Context;

import com.donvigo.databaseinterface.DatabaseInterface;
import com.donvigo.databaseinterface.model.UserModel;
import com.donvigo.realmdatabase.model.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by vgaidarji on 8/14/15.
 */
public class RealmDatabase implements DatabaseInterface {
    private Realm realm;

    @Override
    public void open(Context context) {
        realm = Realm.getInstance(context);
    }

    @Override
    public void close() {
        realm.close();
    }

    @Override
    public List<UserModel> getUsers() {
        List<UserModel> users = new ArrayList<>();
        RealmResults<User> dbUsers = realm.allObjects(User.class);
        for (User u :
                dbUsers) {
            users.add(u);
        }
        return users;
    }

    @Override
    public void addUsers(List<UserModel> userModels) {
        // All writes must be wrapped in a transaction to facilitate safe multi threading
        realm.beginTransaction();
        for (UserModel u :
                userModels) {
            User user = realm.createObject(User.class);
            user.setId(u.getId());
            user.setName(u.getName());
            user.setAddress(u.getAddress());
            user.setSsn(u.getSsn());
            user.setEmail(u.getEmail());
            user.setHomePhone(u.getHomePhone());
            user.setWorkPhone(u.getWorkPhone());
        }
        // When the write transaction is committed, all changes a synced to disk.
        realm.commitTransaction();
    }
}
