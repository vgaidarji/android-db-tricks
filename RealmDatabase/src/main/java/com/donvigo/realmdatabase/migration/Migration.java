/*
 * Copyright 2014 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.donvigo.realmdatabase.migration;

import com.donvigo.realmdatabase.model.User;

import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.internal.Table;

public class Migration implements RealmMigration {
    @Override
    public long execute(Realm realm, long version) {

        /*
            // Version 0
            class User
                private int id;
                private String name;
                private String address;
                private String ssn;
                private String email;
                private String homePhone;
                private String workPhone;

            // Version 1
            class User
                @PrimaryKey
                private int id;
                private String name;
                private String address;
                private String ssn;
                private String email;
                private String homePhone;
                private String workPhone;
        */

        // Migrate from version 0 to version 1
        if (version == 0) {
            Table userTable = realm.getTable(User.class);
            // do migration here
            version++;
        }
        return version;
    }
}
