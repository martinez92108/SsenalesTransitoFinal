package com.example.martinez.senalestransitofinal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.martinez.senalestransitofinal.ModelSenales.ModelFavorito;
import com.example.martinez.senalestransitofinal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinez on 30/11/17.
 */

public class AdapterFavorito extends RecyclerView.Adapter<AdapterFavorito.imagenesHoldel>{



    List<ModelFavorito> modelListCommentList = new ArrayList<>();
    Context context;

    public AdapterFavorito(List<ModelFavorito> modelListCommentList, Context context) {
        this.modelListCommentList = modelListCommentList;
        this.context = context;
    }

    @Override
    public imagenesHoldel onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemfavorito,parent,false);
        imagenesHoldel holdel = new imagenesHoldel(v);
        return holdel;


    }

    @Override
    public void onBindViewHolder(imagenesHoldel holder, int position) {

        final ModelFavorito model = modelListCommentList.get(position);
        holder.namefav.setText(model.getName());
        holder.descripfav.setText(model.getDescripcion());
        Picasso.with(context).load(model.getImg()).into(holder.imgfav);



        /// clic del comentar



    }

    @Override
    public int getItemCount() {
        return modelListCommentList.size();
    }

    public  static class imagenesHoldel extends RecyclerView.ViewHolder{
        TextView namefav,descripfav;
        ImageView imgfav;



        public imagenesHoldel(View itemView) {
            super(itemView);

            namefav=(TextView)itemView.findViewById(R.id.id_name_favorito);
            descripfav=(TextView)itemView.findViewById(R.id.id_descrip_favorito);
            imgfav=(ImageView)itemView.findViewById(R.id.img_favorito);

        }
    }
}
