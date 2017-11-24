package com.example.martinez.senalestransitofinal.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.martinez.senalestransitofinal.ModelSenales.ModelListComment;
import com.example.martinez.senalestransitofinal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martinez on 21/11/17.
 */

public class ListComment extends RecyclerView.Adapter<ListComment.imagenesHoldel> {

    List<ModelListComment> modelListCommentList = new ArrayList<>();
    Context context;

    public ListComment(List<ModelListComment> modelListCommentList, Context context) {
        this.modelListCommentList = modelListCommentList;
        this.context = context;
    }

    @Override
    public imagenesHoldel onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcomentario,parent,false);
        imagenesHoldel holdel = new imagenesHoldel(v);
        return holdel;


    }

    @Override
    public void onBindViewHolder(imagenesHoldel holder, int position) {

        final ModelListComment model = modelListCommentList.get(position);
        holder.textViewttle.setText(model.getTitle());
        holder.textViewcoment.setText(model.getDescripcion());
        Picasso.with(context).load(model.getImg()).into(holder.imgconet);



        /// clic del comentar



    }

    @Override
    public int getItemCount() {
        return modelListCommentList.size();
    }

    public  static class imagenesHoldel extends RecyclerView.ViewHolder{
        TextView textViewttle,textViewcoment;
        ImageView imgconet;



        public imagenesHoldel(View itemView) {
            super(itemView);

           textViewttle=(TextView)itemView.findViewById(R.id.id_title);
            textViewcoment=(TextView)itemView.findViewById(R.id.id_descrip);
           imgconet=(ImageView)itemView.findViewById(R.id.img_cardview);

        }
    }







}
