package com.example.martinez.senalestransitofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.martinez.senalestransitofinal.Adapter.AdapterFavorito;
import com.example.martinez.senalestransitofinal.Adapter.ListComentUser;
import com.example.martinez.senalestransitofinal.ModelSenales.ModelFavorito;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoritoActivity extends AppCompatActivity {



    private RecyclerView recyclerView;
    private FirebaseAuth auth;





    List<ModelFavorito> models;

    AdapterFavorito adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorito);

        recyclerView=(RecyclerView)findViewById(R.id.id_rv_list_favorito);
        recyclerView.setLayoutManager(new LinearLayoutManager(this) );
        models= new ArrayList<>();





        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        DatabaseReference ms= ref.child("favorito");
        adapter = new AdapterFavorito(models,getApplicationContext());
        recyclerView.setAdapter(adapter);

        ms.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                models.remove(models);
                for (DataSnapshot snap :
                        dataSnapshot.getChildren() ) {

                    ModelFavorito imagenModel = snap.getValue(ModelFavorito.class);



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
