package com.example.martinez.senalestransitofinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MasInfoActivity extends AppCompatActivity {

    String imgmas;
    String info;

    private TextView masinfo;
    private ImageView imgmasinf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_info);
        masinfo=(TextView)findViewById(R.id.id_masinfo);
        imgmasinf=(ImageView)findViewById(R.id.img_masinfo);

        imgmas=getIntent().getExtras().getString("image");
        info=getIntent().getExtras().getString("masinfo");

        Picasso.with(this).load(imgmas).into(this.imgmasinf);
        masinfo.setText(info);


    }
}
