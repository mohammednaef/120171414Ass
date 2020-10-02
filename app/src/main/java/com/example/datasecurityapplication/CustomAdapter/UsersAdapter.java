package com.example.datasecurityapplication.CustomAdapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.datasecurityapplication.DBAdapter.DBAdapter;
import com.example.datasecurityapplication.R;
import com.example.datasecurityapplication.Users;
import java.util.ArrayList;

import static com.example.datasecurityapplication.AllUserActivity.data;
import static com.example.datasecurityapplication.AllUserActivity.mListUser;
import static com.example.datasecurityapplication.AllUserActivity.usersAdapter;

public class UsersAdapter extends BaseAdapter {


    Context context;
    ArrayList<Users> list;
    LayoutInflater inflater;

    public UsersAdapter(Context context, ArrayList<Users> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View root = view;
        if (view == null)
            root = inflater.inflate(R.layout.row_user,null);

        final int id = list.get(i).getId();

        final TextView name_user = root.findViewById(R.id.name_user);
        final TextView tx_encryption = root.findViewById(R.id.tx_encryption);
        final TextView tx_decrypted = root.findViewById(R.id.tx_decrypted);
        final TextView tx_identity = root.findViewById(R.id.tx_identity);
        final TextView tx_Hash = root.findViewById(R.id.tx_Hash);
        final Button btn_delete = root.findViewById(R.id.btn_delete);

        name_user.setText(list.get(i).getName());
        tx_encryption.setText(list.get(i).getEncryption());
        tx_decrypted.setText(list.get(i).getDecrypted());
        tx_identity.setText(list.get(i).getIdentity());
        tx_Hash.setText(list.get(i).getGethash());

        final DBAdapter dbAdapter = new DBAdapter(context);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dbAdapter.DELETEUSER(id);

                usersAdapter.notifyDataSetChanged();
                data.remove(list.get(i));

                // updateAdapter(list);



            }
        });


        return root;

    }
//    public void updateAdapter(ArrayList<Users> arrylst) {
//        this.list= arrylst;
//
//        //and call notifyDataSetChanged
//        notifyDataSetChanged();
//    }
}
