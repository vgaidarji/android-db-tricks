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

import java.util.List;

import io.realm.Realm;

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
        // TODO
        return null;
    }

    @Override
    public void addUsers(List<UserModel> userModels) {
        // TODO
    }
}
