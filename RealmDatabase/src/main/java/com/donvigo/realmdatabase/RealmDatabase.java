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
import com.donvigo.realmdatabase.migration.Migration;
import com.donvigo.realmdatabase.model.User;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmException;

/**
 * Created by vgaidarji on 8/14/15.
 */
public class RealmDatabase implements DatabaseInterface {
    private Realm realm;
    private String dbPassword = null;
    private boolean isEncrypted = false;

    @Override
    public void open(Context context) {
        realm = Realm.getInstance(getRealmConfiguration(context));
    }

    private RealmConfiguration getRealmConfiguration(Context context) {
        RealmConfiguration configuration;

        if(isEncrypted) {
            byte[] key = new byte[64];
            byte[] passwordBytes = dbPassword.getBytes();
            int keyLength = dbPassword.length() <= 64 ? dbPassword.length() : 64;
            for(int i = 0; i < keyLength; i++) {
                key[i] = passwordBytes[i];
            }

            configuration = new RealmConfiguration.Builder(context)
                    .name("realmDB_encrypted")
                    .schemaVersion(0)
                    .encryptionKey(key)
                    .deleteRealmIfMigrationNeeded()
                    .migration(new Migration())
                    .build();
        }else {
            configuration = new RealmConfiguration.Builder(context)
                    .name("realmDB")
                    .schemaVersion(0)
                    .deleteRealmIfMigrationNeeded()
                    .migration(new Migration())
                    .build();
        }

        return configuration;
    }

    @Override
    public void close() {
        if(realm == null) {
            return;
        }
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
    public void addUsers(final List<UserModel> userModels) {
        realm.executeTransaction(new Transaction() {
            @Override
            public void execute(Realm realm) {
                for (UserModel u :
                        userModels) {
                    try {
                        User user = new User();
                        user.setId(u.getId());
                        user.setName(u.getName());
                        user.setAddress(u.getAddress());
                        user.setSsn(u.getSsn());
                        user.setEmail(u.getEmail());
                        user.setHomePhone(u.getHomePhone());
                        user.setWorkPhone(u.getWorkPhone());
                        realm.copyToRealmOrUpdate(user);
                    } catch(RealmException re) {
                        re.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public boolean isEncrypted() {
        return isEncrypted;
    }

    @Override
    public void setDatabasePassword(String dbPassword) {
        this.dbPassword = dbPassword;
        isEncrypted = dbPassword != null;
    }
}
