package com.example.martinez.senalestransitofinal;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.martinez.senalestransitofinal.Adapter.ListComment;
import com.example.martinez.senalestransitofinal.ModelSenales.ModelListComment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListarComentActivity extends AppCompatActivity {

    Toolbar toolbar;
    private RecyclerView recyclerView;
    private FirebaseAuth mauth;




    List<ModelListComment> models;

    ListComment adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_coment);

        toolbar=(Toolbar)findViewById(R.id.id_toolbar);
        shoeTollbar(getResources().getString(R.string.p4),true);


        recyclerView=(RecyclerView)findViewById(R.id.id_rv_list_coment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this) );

        models= new ArrayList<>();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        DatabaseReference ms= ref.child("comentarios");


        adapter = new ListComment(models,getApplicationContext());
        recyclerView.setAdapter(adapter);

       ms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                models.remove(models);
                for (DataSnapshot snap :
                        dataSnapshot.getChildren() ) {
                    ModelListComment imagenModel = snap.getValue(ModelListComment.class);
                    String commentKey = dataSnapshot.getKey();
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
