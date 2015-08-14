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

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.BaseForeignCollection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;

import java.sql.SQLException;

public class QuickDaoImpl<T, ID> extends BaseDaoImpl<T, ID> {
    public QuickDaoImpl(Class<T> dataClass) throws SQLException {
        super(dataClass);
    }

    public QuickDaoImpl(ConnectionSource connectionSource, Class<T> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public QuickDaoImpl(ConnectionSource connectionSource, DatabaseTableConfig<T> tableConfig) throws SQLException {
        super(connectionSource, tableConfig);
    }

    public void refreshInstance(Object instance, Class instanceClass) {
        Dao dao = getDao(instanceClass);
        if (dao != null) {
            try {
                dao.refresh(instance);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateInstance(Object instance, Class instanceClass) {
        Dao dao = getDao(instanceClass);
        if (dao != null) {
            try {
                dao.update(instance);
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void refreshForeignCollection(BaseForeignCollection collection) {
        if(collection!=null) {
            try {
                collection.refreshCollection();
                collection.refreshAll();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Dao getDao(Class daoClass) {
        Dao dao = DaoManager.lookupDao(connectionSource, daoClass);
        try {
            if (dao == null) {
                dao = DaoManager.createDao(connectionSource, daoClass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dao;
    }
}