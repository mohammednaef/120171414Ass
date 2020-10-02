package com.example.datasecurityapplication.DBAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.datasecurityapplication.Users;
import java.util.ArrayList;

public class DBAdapter extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public DBAdapter(Context context) {
        super(context, "usersdb3", null, 5);
        db=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Users.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+Users.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //INSERT_WORKERS

    public boolean INSERT_USERS(String name, String identity, String password, String encryption, String decrypted, String gethash )
    {
        ContentValues cv = new ContentValues();
        cv.put(Users.U_NAME,name);
        cv.put(Users.U_IDENTITY,identity);
        cv.put(Users.U_PASSWORD,password);
        cv.put(Users.U_Encryption,encryption);
        cv.put(Users.U_Decrypted,decrypted);
        cv.put(Users.U_Hash,gethash);

        return db.insert(Users.TABLE_NAME,null,cv)>0;

    }

    //GET_ALL_WORKERS

    public ArrayList<Users> GET_ALL_USERS()
    {
        ArrayList<Users> list = new ArrayList<>();

        String sqlquery = "SELECT * FROM "+Users.TABLE_NAME+" ORDER BY ID DESC";
        Cursor cursor = db.rawQuery(sqlquery,null);

        if (cursor.moveToFirst())
        {
            do{

                Users users = new Users();

                users.setId(cursor.getInt(cursor.getColumnIndex(Users.U_ID)));
                users.setName(cursor.getString(cursor.getColumnIndex(Users.U_NAME)));
                users.setIdentity(cursor.getString(cursor.getColumnIndex(Users.U_IDENTITY)));
                users.setPassword(cursor.getString(cursor.getColumnIndex(Users.U_PASSWORD)));
                users.setEncryption(cursor.getString(cursor.getColumnIndex(Users.U_Encryption)));
                users.setDecrypted(cursor.getString(cursor.getColumnIndex(Users.U_Decrypted)));
                users.setGethash(cursor.getString(cursor.getColumnIndex(Users.U_Hash)));


                list.add(users);


            }while(cursor.moveToNext());
        }

        cursor.close();
        return list;

    }



    public ArrayList<Users> getAllByUser(String userName,String password  ) {

        ArrayList<Users> list = new ArrayList<>();
       // String sqlquery = "SELECT * FROM "+Users.TABLE_NAME+" Where NAME = '"+userName+"' AND PASSWORD = '"+password+"'" ;

        String sqlquery = "SELECT * FROM "+Users.TABLE_NAME+" Where NAME =? AND PASSWORD =?";

        String[] arr ={userName,password};
        Cursor cursor = db.rawQuery(sqlquery,arr);

        if (cursor.moveToFirst())
        {
            do{

                Users users = new Users();


                users.setId(cursor.getInt(cursor.getColumnIndex(Users.U_ID)));
                users.setName(cursor.getString(cursor.getColumnIndex(Users.U_NAME)));
                users.setIdentity(cursor.getString(cursor.getColumnIndex(Users.U_IDENTITY)));
                users.setPassword(cursor.getString(cursor.getColumnIndex(Users.U_PASSWORD)));
                users.setEncryption(cursor.getString(cursor.getColumnIndex(Users.U_Encryption)));
                users.setDecrypted(cursor.getString(cursor.getColumnIndex(Users.U_Decrypted)));
                users.setGethash(cursor.getString(cursor.getColumnIndex(Users.U_Hash)));

                list.add(users);


            }while(cursor.moveToNext());
        }

        cursor.close();
        return list;

    }

    public boolean DELETEUSER(int id){

        return db.delete(Users.TABLE_NAME," id = "+id,null)>0;

    }


}
