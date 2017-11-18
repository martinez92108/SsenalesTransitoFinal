package com.example.martinez.senalestransitofinal;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.martinez.senalestransitofinal.ModelSenales.ComentariosModel;
import com.example.martinez.senalestransitofinal.Referencias.Referencias;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CometarioActivity extends AppCompatActivity {

    private EditText title;
    private EditText decription;
    private Button guardar;
    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener authStateListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cometario);
        title = (EditText)findViewById(R.id.id_title_coment);
        decription= (EditText)findViewById(R.id.id_decrip);
        guardar=(Button)findViewById(R.id.id_guardar_comen);
        mauth = FirebaseAuth.getInstance();



    }

    /*
    public  void  insert(){
        final String titlecoment = title.getText().toString();
        final String des = decription.getText().toString();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference currentUserDB= database.child(mauth.getCurrentUser().getUid());

       final  DatabaseReference db=database.getReference(Referencias.REFERENCES);
        final DatabaseReference DB= db.child(mauth.getCurrentUser().getUid());


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DB.child(Referencias.COMEN).push().setValue(titlecoment);


                //ComentariosModel coment = new ComentariosModel(titlecoment,des );


                //DB.child(Referencias.COMEN).push().setValue(coment);


            }
        });
    }

*/
























    public void start(View view) {

        final String titlecomen = title.getText().toString().trim();
        final String descrip = decription.getText().toString().trim();




        //String user_id=mauth.getCurrentUser().getUid();
        // Toast.makeText(RegisterUserActivity.this,user_id,Toast.LENGTH_SHORT).show();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("comentarios");

        DatabaseReference currentUserDB= database.child(mauth.getCurrentUser().getUid());
        DatabaseReference coment = currentUserDB.child("coment").push();
        //currentUserDB.child("id").setValue(mauth.getCurrentUser().getUid());
       coment.child("title").setValue(titlecomen);
       coment.child("descricion").setValue(descrip);


        Toast.makeText( CometarioActivity.this,"Usuario Creado",Toast.LENGTH_SHORT).show();






    }



}
