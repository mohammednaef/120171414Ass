package com.example.datasecurityapplication;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datasecurityapplication.CustomAdapter.UsersAdapter;
import com.example.datasecurityapplication.DBAdapter.DBAdapter;

import java.util.ArrayList;

public class AllUserActivity extends AppCompatActivity {


    DBAdapter dbAdapter;
    public static ArrayList<Users> data;
    public static ListView mListUser;
    public static UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user);

        mListUser = findViewById(R.id.list_user);

        dbAdapter = new DBAdapter(this);
        data = new ArrayList<>();
        data.addAll(dbAdapter.GET_ALL_USERS());

        usersAdapter = new UsersAdapter(this, data);
        mListUser.setAdapter(usersAdapter);

        if (data.size() == 0) {
            Toast.makeText(getApplicationContext(), "No Data Here !", Toast.LENGTH_SHORT).show();
        }


    }
}
