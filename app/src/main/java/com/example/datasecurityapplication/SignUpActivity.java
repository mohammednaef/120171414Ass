package com.example.datasecurityapplication;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.datasecurityapplication.DBAdapter.DBAdapter;
import com.example.datasecurityapplication.Encryption.Encryptor;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

private static final pattern password_PATTERN= pattern.compile ("^" +"( ?=.*[0-9])"
        +"( ?=.*[a-z])"+"( ?=.*[A-Z])"
        +"( ?=.*[@#$%^&+=])"
        +"( ?=\\S+$)"+"{6,24}"
        +"$");

public class SignUpActivity extends AppCompatActivity {

    private EditText mUserName;
    private EditText mUserPass;
    private Button mLogin2;
    DBAdapter dbAdapter;
    private EditText mUserIdentity;
    byte[] cipherText ;
    byte[] decryptedCipherText;
    String encryption;
    String decrypted;
    String get_Hash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mUserName = findViewById(R.id.user_name);
        mUserPass = findViewById(R.id.user_pass);
        mLogin2 = findViewById(R.id.login_2);
        mUserIdentity = findViewById(R.id.user_identity);


        //GetHash
        // one way
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                get_Hash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.e("MY KEY HASH:", get_Hash);

            }
        } catch (PackageManager.NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e) {
        }




        //Database
        dbAdapter = new DBAdapter(this);
        mLogin2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                final String name = mUserName.getText().toString().trim();
                final String password = mUserPass.getText().toString().trim();
                final String identity = mUserIdentity.getText().toString().trim();



                // Encryption
                //two way
                byte[] encryptionKey = "MZygpewJsCpRrfOr".getBytes(StandardCharsets.UTF_8);
                byte[] plainText = password.getBytes(StandardCharsets.UTF_8);

                Encryptor advancedEncryptionStandard = new Encryptor(encryptionKey);


                try {
                    cipherText = advancedEncryptionStandard.encrypt(plainText);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                 //decrypt

                try {
                    decryptedCipherText = advancedEncryptionStandard.decrypt(cipherText);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //
                try {
                    encryption = new String(cipherText, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                try {
                    decrypted = new String(decryptedCipherText, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                ///


                if (name.isEmpty() && password.isEmpty() && identity.isEmpty() ) {
                    Toast.makeText(SignUpActivity.this, "Please fill in all the details!", Toast.LENGTH_SHORT).show();

                } else {


                    if (password.length() >= 8 && password.length() <=24){

                    if (identity.length() != 9) {
                        Toast.makeText(SignUpActivity.this, "Error Number Identity", Toast.LENGTH_SHORT).show();

                    } else {

                        boolean insert = dbAdapter.INSERT_USERS(name, identity, password, encryption, decrypted,get_Hash);

                        if (insert) {

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            finish();

                            Toast.makeText(SignUpActivity.this, "Save In DataBace", Toast.LENGTH_SHORT).show();

                        } else
                            Toast.makeText(SignUpActivity.this, "null add", Toast.LENGTH_SHORT).show();

                    }

                }else
                        Toast.makeText(SignUpActivity.this, "Less Password", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    public void GetHash(){

    }
}
