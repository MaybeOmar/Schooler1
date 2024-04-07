
package com.example.schoolmanagementsystem;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.schoolmanagementsystem.Models.announcment_model;

import java.util.ArrayList;
import java.util.List;

public class announcment_adapter extends RecyclerView.Adapter<ViewHolder> {


    annoouncment_List anns_list;
    ArrayList<announcment_model> modelList;
    Context context;



    public announcment_adapter(annoouncment_List anns_list, List<announcment_model> modelList) {
        this.modelList = (ArrayList<announcment_model>) modelList;
        this.anns_list = anns_list;

    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemview = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.announcment_model_layout,viewGroup,false);

        ViewHolder holder =new ViewHolder(itemview);
        holder.setonclicklistener(new ViewHolder.clicklistener() {
            @Override
            public void onitemclick(View view, int position) {

                String title = modelList.get(position).getTitle();
                String description = modelList.get(position).getDescription();

            }

            @Override
            public void onitemlongclick(View view, int position) {

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder Holder, int position) {

        announcment_model model= modelList.get(position);
        Holder.title.setText(modelList.get(position).getTitle());
        Holder.description.setText(modelList.get(position).getDescription());

    }


   /* public class viewHolder extends RecyclerView.ViewHolder{

        private TextView title,description;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.rtitle);
            description=itemView.findViewById(R.id.rdescription);
        }
    }*/

    @Override
    public int getItemCount() {
        return modelList.size();
    }


}


