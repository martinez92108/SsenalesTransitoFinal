package com.example.martinez.senalestransitofinal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.martinez.senalestransitofinal.Adapter.ListComentUser;
import com.example.martinez.senalestransitofinal.Adapter.ListComment;
import com.example.martinez.senalestransitofinal.ModelSenales.ModelUserComet;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListComentUserActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private FirebaseAuth auth;





    List<ModelUserComet> models;

    ListComentUser adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_coment_user);

        toolbar=(Toolbar)findViewById(R.id.id_toolbar);
        shoeTollbar(getResources().getString(R.string.p4),true);




        recyclerView=(RecyclerView)findViewById(R.id.id_rv_list_coment_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this) );
        models= new ArrayList<>();





        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        DatabaseReference ms= ref.child("coment_user");
        adapter = new ListComentUser(models,getApplicationContext());
        recyclerView.setAdapter(adapter);

       ms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                models.remove(models);
                for (DataSnapshot snap :
                        dataSnapshot.getChildren() ) {

                   ModelUserComet imagenModel = snap.getValue(ModelUserComet.class);



                    models.add(imagenModel);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return super.onCreateOptionsMenu(menu);
    }



    //crear boolean para la flecha
    public  void  shoeTollbar(String title,boolean upbutton){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        //flecha
        getSupportActionBar().setDisplayHomeAsUpEnabled(upbutton);

    }
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent1 = new Intent(this, MainActivity.class);

        startActivity(intent1);
        return super.onOptionsItemSelected(item);
    }



}

