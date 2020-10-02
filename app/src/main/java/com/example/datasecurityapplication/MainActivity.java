package com.example.datasecurityapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.datasecurityapplication.DBAdapter.DBAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    /**
    private EditText mUserName;
    private EditText mUserPass;
    private Button mLogin1;
    private Button mSing1;
    private ArrayList<Users> data;
    private ArrayList<Users> data2 = new ArrayList<>();
    private DBAdapter dbAdapter;
    private int count = 0;
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUserName = findViewById(R.id.user_name);
        mUserPass = findViewById(R.id.user_pass);
        mLogin1 = findViewById(R.id.login_1);
        mSing1 = findViewById(R.id.sing_1);

        dbAdapter = new DBAdapter(this);
        data = new ArrayList<>();
        data2.addAll(dbAdapter.GET_ALL_USERS());

        mLogin1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name = mUserName.getText().toString().trim();
                final String password = mUserPass.getText().toString().trim();

                if (name.isEmpty() && password.isEmpty()) {

                    Toast.makeText(MainActivity.this,
                            "Input Data(name & password)",
                            Toast.LENGTH_SHORT)
                            .show();
                }  else {

                 if (name.equals("admin") && password.equals("admin")) {
                 startActivity(new Intent(MainActivity
                         .this, AllUserActivity.class));

                } else {

                     if (count >= 3) {
                         Toast.makeText(MainActivity.this,
                                 "The account has been suspended?",
                                 Toast.LENGTH_LONG).show();
                     return;
                     }

                    if (data2.size() != 0) {
                        if (data2.get(0).getName().equals(name) && data2.get(0)
                                .getPassword().equals(password)) {
                            data.addAll(dbAdapter.getAllByUser(name, password));

                            Intent intent = new Intent(MainActivity
                                    .this, ProfileUserActivity.class);
                            intent.putExtra("users", data);
                            startActivity(intent);

                        } else {
                            Toast.makeText(MainActivity.
                                            this,
                                    "Error In Password OR UserName",
                                    Toast.LENGTH_SHORT).show();
                            count++;

                        }
                    } else
                        Toast.makeText(MainActivity.this,
                                "DataBase Empty", Toast.LENGTH_SHORT)
                                .show();


                }

                }




            }
        });

        mSing1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.
                        this, SignUpActivity.class));

            }
        });
    }
}
