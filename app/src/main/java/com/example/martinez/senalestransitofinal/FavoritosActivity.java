package com.example.martinez.senalestransitofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FavoritosActivity extends AppCompatActivity {
    private TextView textViewnom;
    private TextView textViewdes;
    private ImageView imageViewfavorito;

    String name,desc,image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        textViewnom=(TextView)findViewById(R.id.id_favorito);
        textViewdes=(TextView)findViewById(R.id.id_des_favorito);
        imageViewfavorito=(ImageView)findViewById(R.id.img_favorito);


        image= getIntent().getExtras().getString("image");
        desc=getIntent().getExtras().getString("des");
      name=getIntent().getExtras().getString("name");


      textViewnom.setText(name);
      textViewdes.setText(desc);
        Picasso.with(this).load(image).into(this.imageViewfavorito);


    }
}
