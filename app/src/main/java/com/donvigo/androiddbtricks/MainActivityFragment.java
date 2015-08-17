/**
 *  Copyright 2015 Veaceslav Gaidarji
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.donvigo.androiddbtricks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.donvigo.databaseinterface.DatabaseInterface;
import com.donvigo.databaseinterface.DatabaseManager;
import com.donvigo.databaseinterface.model.UserModel;
import com.donvigo.realmdatabase.RealmDatabase;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @InjectView(R.id.textViewDBName)
    TextView textViewDBName;
    List<UserModel> users;
    DatabaseInterface database;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createAndOpenDatabase();
        fillUsersTable();
        getUsersFromDB();
        updateText();
    }

    private void updateText() {
        textViewDBName.setText(String.format("%s, items in DB: %d",
                database.getClass().getSimpleName(), users.size()));
    }

    private void createAndOpenDatabase() {
//        database = new SQLiteDatabaseImpl(getActivity());
//        database = new OrmLiteDatabase(getActivity());
        database = new RealmDatabase();
        DatabaseManager.init(getActivity(), database);
    }

    private void fillUsersTable() {
        DatabaseManager.getInstance().addUsers(FakeUsers.getUsers());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        DatabaseManager.getInstance().close();
    }

    private void getUsersFromDB() {
        users = DatabaseManager.getInstance().getUsers();
    }
}
