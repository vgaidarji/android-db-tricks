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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.donvigo.databaseinterface.DatabaseInterface;
import com.donvigo.databaseinterface.DatabaseManager;
import com.donvigo.databaseinterface.model.UserModel;
import com.donvigo.ormlitedatabase.OrmLiteDatabase;
import com.donvigo.realmdatabase.RealmDatabase;
import com.donvigo.sqlitedatabase.SQLiteDatabaseImpl;
import com.donvigo.sqlitedatabase.SQLiteDatabaseWithChiper;

import net.sqlcipher.database.SQLiteDatabase;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    @InjectView(R.id.textViewDBName)
    TextView textViewDBName;
    @InjectView(R.id.recyclerViewItems)
    RecyclerView rv;
    @InjectView(R.id.editTextDBPassword)
    EditText editTextDBPassword;
    private RecyclerView.Adapter rvAdapter;
    private RecyclerView.LayoutManager rvLayoutManager;

    List<UserModel> users;
    DatabaseInterface database;

    enum DatabaseName {
        SQLite, OrmLite, Realm, SQLiteWithChiper
    }

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sqlite:
                changeDatabase(DatabaseName.SQLite);
                return true;
            case R.id.action_ormlite:
                changeDatabase(DatabaseName.OrmLite);
                return true;
            case R.id.action_realm:
                changeDatabase(DatabaseName.Realm);
                return true;
            case R.id.action_sqlite_with_chiper:
                changeDatabase(DatabaseName.SQLiteWithChiper);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        setupRecyclerView();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        changeDatabase(DatabaseName.SQLite);
    }

    /**
     * Changes database to specified one. Default: RealmDatabase.
     * @param dbName
     */
    private void changeDatabase(DatabaseName dbName) {
        closeDatabase();
        switch (dbName) {
            case SQLite:
                database = new SQLiteDatabaseImpl(getActivity());
                break;
            case OrmLite:
                database = new OrmLiteDatabase(getActivity());
                break;
            case Realm:
                database = new RealmDatabase();
                break;
            case SQLiteWithChiper:
                // This must be called before any call the SQLCipher classes (only one call)
                SQLiteDatabase.loadLibs(getActivity());
                database = new SQLiteDatabaseWithChiper(getActivity());
                database.setDatabasePassword(editTextDBPassword.getText().toString());
                break;
            default:
                database = new SQLiteDatabaseImpl(getActivity());
        }
        setupDatabase();
        updateUI();
    }

    private synchronized void closeDatabase() {
        if(database != null) {
            DatabaseManager.getInstance().close();
            database.close();
            database = null;
        }
    }

    private void fillRecyclerView(final List<UserModel> users) {
        rv.setAdapter(null);
        rvAdapter = new RecyclerViewAdapter(users);
        rv.setAdapter(rvAdapter);
    }

    private void setupRecyclerView() {
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        rv.setHasFixedSize(true);

        rvLayoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(rvLayoutManager);
    }

    private void updateUI() {
        updateText();
        fillRecyclerView(users);
    }

    private void setupDatabase() {
        createAndOpenDatabase();
        fillUsersTable();
        getUsersFromDB();
    }

    private void updateText() {
        textViewDBName.setText(String.format("%s, items in DB: %d",
                database.getClass().getSimpleName(), users.size()));
    }

    private void createAndOpenDatabase() {
        DatabaseManager.init(getActivity(), database);
    }

    private void fillUsersTable() {
        DatabaseManager.getInstance().addUsers(FakeUsers.getUsers(database.getClass().getSimpleName()));
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
