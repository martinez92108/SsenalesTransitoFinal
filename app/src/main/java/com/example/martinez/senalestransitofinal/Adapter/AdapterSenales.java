package com.example.martinez.senalestransitofinal.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.martinez.senalestransitofinal.CometarioActivity;
import com.example.martinez.senalestransitofinal.FavoritosActivity;
import com.example.martinez.senalestransitofinal.MasInfoActivity;
import com.example.martinez.senalestransitofinal.ModelSenales.ImagenModel;
import com.example.martinez.senalestransitofinal.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by martinez on 16/11/17.
 */

public class AdapterSenales extends RecyclerView.Adapter<AdapterSenales.imagenesHoldel>{

    List<ImagenModel>imagenModelList = new ArrayList<>();
    Context context;

    public AdapterSenales(List<ImagenModel> imagenModelList, Context context) {
        this.imagenModelList = imagenModelList;
        this.context = context;
    }

    @Override
    public imagenesHoldel onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemsenales,parent,false);
        imagenesHoldel holdel = new imagenesHoldel(v);
        return holdel;


    }

    @Override
    public void onBindViewHolder(imagenesHoldel holder, int position) {
        final ImagenModel model = imagenModelList.get(position);

        holder.textViewnameimg.setText(model.getNameimg());
        holder.textViewdes.setText(model.getDes());

        Picasso.with(context).load(model.getImg()).into(holder.imageViewsenales);

        holder.buttoncomentario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CometarioActivity.class);

                intent.putExtra("image",model.getImg() );

                v.getContext().startActivity(intent);

            }
        });


        holder.masinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MasInfoActivity.class);

                intent.putExtra("image",model.getImg() );
                intent.putExtra("masinfo",model.getMasinfo());

                v.getContext().startActivity(intent);
            }
        });

        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,FavoritosActivity.class);

                intent.putExtra("image",model.getImg() );
                intent.putExtra("des",model.getDes());
                intent.putExtra("name",model.getNameimg());

                v.getContext().startActivity(intent);
            }
        });




        /// clic del comentar



    }

    @Override
    public int getItemCount() {
        return imagenModelList.size();
    }

    public  static class imagenesHoldel extends RecyclerView.ViewHolder{
        TextView textViewnameimg,textViewdes;
        ImageView imageViewsenales;
        ImageView imgcontario;
        Button buttoncomentario,masinfo , like;


        public imagenesHoldel(View itemView) {
            super(itemView);
            textViewnameimg=(TextView)itemView.findViewById(R.id.id_img_nombre);
            textViewdes=(TextView)itemView.findViewById(R.id.id_descripcion);


            imageViewsenales=(ImageView)itemView.findViewById(R.id.img_item_cardview);
            buttoncomentario=(Button)itemView.findViewById(R.id.btn_comet);

            like=(Button)itemView.findViewById(R.id.btn_like);

            masinfo=(Button)itemView.findViewById(R.id.btn_masinfo);

            imgcontario=(ImageView)itemView.findViewById(R.id.img_coment);
        }
    }





}
