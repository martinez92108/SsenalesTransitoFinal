package com.example.martinez.senalestransitofinal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.martinez.senalestransitofinal.ModelSenales.ModelUserComet;
import com.example.martinez.senalestransitofinal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinez on 29/11/17.
 */

public class ListComentUser extends RecyclerView.Adapter<ListComentUser.imagenesHoldel> {

    List<ModelUserComet> modelListCommentList = new ArrayList<>();
    Context context;

    public ListComentUser(List<ModelUserComet> modelListCommentList, Context context) {
        this.modelListCommentList = modelListCommentList;
        this.context = context;
    }

    @Override
    public imagenesHoldel onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listcomentuser,parent,false);
        imagenesHoldel holdel = new imagenesHoldel(v);
        return holdel;


    }

    @Override
    public void onBindViewHolder(imagenesHoldel holder, int position) {

        final ModelUserComet model = modelListCommentList.get(position);
        holder.title.setText(model.getTitle());
        holder.descrip.setText(model.getDescripcion());
        Picasso.with(context).load(model.getImg()).into(holder.img);



        /// clic del comentar



    }

    @Override
    public int getItemCount() {
        return modelListCommentList.size();
    }

    public  static class imagenesHoldel extends RecyclerView.ViewHolder{
        TextView title,descrip;
        ImageView img;



        public imagenesHoldel(View itemView) {
            super(itemView);

            title=(TextView)itemView.findViewById(R.id.id_list_usert_title);
            descrip=(TextView)itemView.findViewById(R.id.id_list_des_user);
           img=(ImageView)itemView.findViewById(R.id.id_img_list_user);

        }
    }





}
