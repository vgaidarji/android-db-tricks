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

package com.donvigo.realmdatabase.model;

import com.donvigo.databaseinterface.model.UserModel;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vgaidarji on 8/14/15.
 */
public class User extends RealmObject implements UserModel {
    @PrimaryKey
    private int id;
    private String name;
    private String address;
    private String ssn;
    private String email;
    private String homePhone;
    private String workPhone;

    public User() {
    }

    // The standard getters and setters your IDE generates are fine.
    // Realm will overload them and code inside them is ignored.
    // So if you prefer you can also just have empty abstract methods.

    public int getId() {
        return id;
    }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public String getSsn() { return ssn; }

    public String getEmail() { return email; }

    public String getHomePhone() { return homePhone; }

    public String getWorkPhone() { return workPhone; }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) { this.name = name; }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSsn(String ssn) { this.ssn = ssn; }

    public void setEmail(String email) { this.email = email;  }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    // Only getters and setters should be defined in model classes
    // It means that we can't add toString(), hashCode(), equals() methods
}
