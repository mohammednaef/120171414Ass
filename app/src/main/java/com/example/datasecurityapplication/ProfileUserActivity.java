package com.example.datasecurityapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProfileUserActivity extends AppCompatActivity {

    private TextView mTxName;
    private TextView mTxIdentity;
    private TextView mTxPass,tx_hash,tx_encrept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);


        mTxName = findViewById(R.id.tx_name);
        mTxIdentity = findViewById(R.id.tx_identity);
        mTxPass = findViewById(R.id.tx_pass);
        tx_hash = findViewById(R.id.tx_hash);
        tx_encrept = findViewById(R.id.tx_encrept);

        ArrayList<Users> users = (ArrayList<Users>) getIntent().getSerializableExtra("users");


        if (users != null) {

            String name = users.get(0).getName();
            String identity = users.get(0).getIdentity();
            String password = users.get(0).getPassword();
            String hash = users.get(0).getGethash();
            String encrypt = users.get(0).getEncryption();
            Log.e("name", name + "");

            mTxName.setText(name);
            mTxIdentity.setText(identity);
            mTxPass.setText(password);
            tx_hash.setText(hash);
            tx_encrept.setText(encrypt);

        } else {
            Toast.makeText(this, "null data", Toast.LENGTH_SHORT).show();
        }


    }
}
