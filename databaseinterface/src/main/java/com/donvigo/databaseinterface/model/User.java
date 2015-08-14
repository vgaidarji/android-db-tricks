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

package com.donvigo.databaseinterface.model;

/**
 * Created by vgaidarji on 8/14/15.
 */
public class User {
    private String name;
    private String address;
    private String ssn;
    private String email;
    private String homePhone;
    private String workPhone;

    public User(String name, String address, String ssn,
                String email, String homePhone, String workPhone) {
        this.name = name;
        this.address = address;
        this.ssn = ssn;
        this.email = email;
        this.homePhone = homePhone;
        this.workPhone = workPhone;
    }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public String getSsn() { return ssn; }

    public String getEmail() { return email; }

    public String getHomePhone() { return homePhone; }

    public String getWorkPhone() { return workPhone; }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User person = (User) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (address != null ? !address.equals(person.address) : person.address != null)
            return false;
        if (ssn != null ? !ssn.equals(person.ssn) : person.ssn != null) return false;
        if (email != null ? !email.equals(person.email) : person.email != null) return false;
        if (homePhone != null ? !homePhone.equals(person.homePhone) : person.homePhone != null)
            return false;
        return !(workPhone != null ? !workPhone.equals(person.workPhone) : person.workPhone != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (ssn != null ? ssn.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", ssn='" + ssn + '\'' +
                ", email='" + email + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                '}';
    }
}
