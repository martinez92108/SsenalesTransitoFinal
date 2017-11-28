package com.example.martinez.senalestransitofinal;

import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
    private RecyclerView recyclerView;
    private FirebaseAuth mauth;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();





    List<ModelListComment> models;

    ListComment adapter;
    private DatabaseReference mDatabase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_coment);



        recyclerView=(RecyclerView)findViewById(R.id.id_rv_list_coment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this) );

        models= new ArrayList<>();






        //FirebaseDatabase refdatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        final DatabaseReference ms= ref.child("comentarios");

       // Query ref = ref1.child("comentarios").child("coment");



       // DatabaseReference db= ms.child("coment");








        adapter = new ListComment(models,getApplicationContext());

        recyclerView.setAdapter(adapter);





       ms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                models.remove(models);
                for (DataSnapshot snap :
                        dataSnapshot.getChildren() ) {



                    ModelListComment imagenModel = snap.getValue(ModelListComment.class);
                    models.add(imagenModel);

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }



  }
