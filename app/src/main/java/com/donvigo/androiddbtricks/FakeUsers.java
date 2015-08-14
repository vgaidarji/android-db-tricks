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

package com.donvigo.androiddbtricks;

import com.donvigo.databaseinterface.model.UserModel;
import com.donvigo.sqlitedatabase.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vgaidarji on 8/14/15.
 */
public class FakeUsers {
    public static List<UserModel> getUsers() {
        List<UserModel> users = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            users.add(new User(
                    i,
                    "name " + i,
                    "address " + i,
                    "ssn " + i,
                    "email " + i,
                    "000000" + i,
                    "111111" + i
            ));
        }
        return users;
    }
}
