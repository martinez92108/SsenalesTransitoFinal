package com.example.martinez.senalestransitofinal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class CometarioActivity extends AppCompatActivity {

    SharedPreferences preferencesUno;









    private EditText title;
    private EditText decription;
    private Button guardar;
    private FirebaseAuth mauth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private ProgressDialog progressDialog;
    String imagencont;
    ImageView imgcoment;
    String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cometario);



        preferencesUno = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);


        title = (EditText)findViewById(R.id.id_title_coment);
        decription= (EditText)findViewById(R.id.id_decrip);
        guardar=(Button)findViewById(R.id.id_guardar_comen);
        imgcoment=(ImageView) findViewById(R.id.img_coment);

        mauth = FirebaseAuth.getInstance();

       imagencont= getIntent().getExtras().getString("image");


        Picasso.with(this).load(imagencont).into(this.imgcoment);



    }

    public void start(View view) {

        final String titlecomen = title.getText().toString().trim();
        final String descrip = decription.getText().toString().trim();

        if (TextUtils.isEmpty(titlecomen)){
            Toast.makeText(this, "El campo de Titulo esta vacio", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(descrip)){
            Toast.makeText(this, "El campo de la Descripcion esta vacio", Toast.LENGTH_SHORT).show();

        }else{



            DatabaseReference database = FirebaseDatabase.getInstance().getReference("comentarios");
           // DatabaseReference DB= database.child(mauth.getCurrentUser().getUid());
            DatabaseReference currentUserDB= database.push();
            //DatabaseReference coment = currentUserDB.child("coment").push();
           currentUserDB.child("title").setValue(titlecomen);
           currentUserDB.child("descripcion").setValue(descrip);
            currentUserDB.child("img").setValue(imagencont);
            currentUserDB.child("User key").setValue(mauth.getCurrentUser().getUid());
            currentUserDB.child("user_name").setValue( preferencesUno.getString("user", null));







            DatabaseReference database1 = FirebaseDatabase.getInstance().getReference("coment_user");
            //DatabaseReference currentUserDB1= database1.push();
             DatabaseReference DB= database1.child(mauth.getCurrentUser().getUid());
            DatabaseReference currentUserDB1= DB.push();
            //DatabaseReference coment = currentUserDB.child("coment").push();
           currentUserDB1.child("title").setValue(titlecomen);
           currentUserDB1.child("descripcion").setValue(descrip);
            currentUserDB1.child("img").setValue(imagencont);
           // DB.child("id").setValue(mauth.getCurrentUser().getUid());

















            Toast.makeText( CometarioActivity.this,"Comentario Registrado",Toast.LENGTH_SHORT).show();

            title.setText("");
            decription.setText("");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }


    }

    public void showTitle(View view){
        String nameuser = preferencesUno.getString("user", null);

    }



}
