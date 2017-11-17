package com.example.martinez.senalestransitofinal.Adapter;

import android.app.ProgressDialog;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

/**
 * Created by martinez on 16/11/17.
 */

public class AdapterSenales {

    public AdapterSenales() {
         DatabaseReference bdReference;
         bdReference= FirebaseDatabase.getInstance().getReference("imagen");
    }




}
