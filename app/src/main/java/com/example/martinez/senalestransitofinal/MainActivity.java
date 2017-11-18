package com.example.martinez.senalestransitofinal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.martinez.senalestransitofinal.Adapter.AdapterSenales;
import com.example.martinez.senalestransitofinal.ModelSenales.ImagenModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private FirebaseAuth auth;
    private TextView textViewusuario;
    private ImageView imageViewusu;
    private Button buttonsalir;
    private ProgressDialog progressDialog;
    private FirebaseAuth.AuthStateListener authStateListener;
    private int CAMERA_REQUEST_CODE = 0;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private DatabaseReference Reference;

    List<ImagenModel> models;

    AdapterSenales adapter;


    protected void onStart() {
        super.onStart();

        if (auth.getCurrentUser() == null)
            return;

        auth.addAuthStateListener(authStateListener);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//////////////////////////////////base de datos firebases
        recyclerView=(RecyclerView)findViewById(R.id.id_rv_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this) );

        models= new ArrayList<>();

        FirebaseDatabase refdatabase = FirebaseDatabase.getInstance();

        adapter = new AdapterSenales(models,getApplicationContext());
        recyclerView.setAdapter(adapter);

        refdatabase.getReference().child("imagen").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                models.remove(models);
                for (DataSnapshot snap :
                       dataSnapshot.getChildren() ) {
                    ImagenModel imagenModel = snap.getValue(ImagenModel.class);
                    models.add(imagenModel);
                    
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

///////////////////////////////////////////////////////////////////////////////////////7


        buttonsalir = (Button) findViewById(R.id.id_btn_login);
        textViewusuario = (TextView) findViewById(R.id.id_log_usuario);
        imageViewusu = (ImageView) findViewById(R.id.id_img_login);

        imageViewusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(Intent.createChooser(intent, "seleccione una imagen para su perfil"), CAMERA_REQUEST_CODE);
                }
            }
        });

        buttonsalir = (Button) findViewById(R.id.id_btn_login);
        buttonsalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (auth.getCurrentUser() != null) {
                    auth.signOut();
                }

            }
        });
        progressDialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        authStateListener = (new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    storageReference = FirebaseStorage.getInstance().getReference();
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("user");
                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            textViewusuario.setText(String.valueOf(dataSnapshot.child("name").getValue()));
                            String imageUrl = String.valueOf(dataSnapshot.child("image").getValue());

                            if (URLUtil.isValidUrl(imageUrl)) {
                                Picasso.with(MainActivity.this).load(Uri.parse(imageUrl)).into(imageViewusu);
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                } else {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }

            }
        });
    }


   public String getRandomString(){
        SecureRandom random= new SecureRandom();
        return  new BigInteger(130,random).toString(32);

    }

    protected void onActivityResult(int requesCode,int resultCode,Intent data){




        super.onActivityResult(requesCode,resultCode,data);

        if (requesCode == UCrop.RESULT_ERROR) {
            Toast.makeText(this, "uCrop error", Toast.LENGTH_SHORT).show();
            return;
        }

        if (requesCode == UCrop.REQUEST_CROP) {
            final Uri imgUri = UCrop.getOutput(data);
            Toast.makeText(this, imgUri.getPath(), Toast.LENGTH_SHORT).show();
            uploadImage(imgUri);
            return;
        }
        if (requesCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {

            final Uri sourceUri = data.getData();
            if (sourceUri == null) {
                progressDialog.dismiss();
                return;
            } else {
                File tempCropped = new File(getCacheDir(), "tempImgCropped.png");
                Uri destinationUri = Uri.fromFile(tempCropped);
                UCrop.of(sourceUri, destinationUri).start(this);
                //.withAspectRatio(3, 2)
                //.withMaxResultSize(MAX_WIDTH, MAX_HEIGHT)

            }
        }






    }


    public void uploadImage(final Uri fileUri){


        if (auth.getCurrentUser() == null)
            return;

        if (storageReference == null)
            storageReference = FirebaseStorage.getInstance().getReference();
        if (databaseReference == null)
            databaseReference = FirebaseDatabase.getInstance().getReference().child("user");


        final StorageReference filepath = storageReference.child("Photos").child(getRandomString());

        final DatabaseReference currentUserDB = databaseReference.child(auth.getCurrentUser().getUid());

        progressDialog.setMessage("Uploading image...");
        progressDialog.show();

        currentUserDB.child("image").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String image = dataSnapshot.getValue().toString();

                if (!image.equals("default") && !image.isEmpty()) {
                    Task<Void> task = FirebaseStorage.getInstance().getReferenceFromUrl(image).delete();
                    task.addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                                Toast.makeText(MainActivity.this, "Deleted image succesfully", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(MainActivity.this, "Deleted image failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                currentUserDB.child("image").removeEventListener(this);

                filepath.putFile(fileUri).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Uri downloadUri = taskSnapshot.getDownloadUrl();
                        Toast.makeText(MainActivity.this, "Finished", Toast.LENGTH_SHORT).show();
                        Picasso.with(MainActivity.this).load(fileUri).fit().centerCrop().into(imageViewusu);
                        DatabaseReference currentUserDB = databaseReference.child(auth.getCurrentUser().getUid());
                        currentUserDB.child("image").setValue(downloadUri.toString());
                    }
                }).addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }


    public  void comentarios(View view){
        Intent intent = new Intent(MainActivity.this, CometarioActivity.class);
        startActivity(intent);
    }

}
