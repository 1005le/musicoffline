package com.example.admin.musicoffline.SearchRecycleView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.musicoffline.R;


import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    Context context;
    List<Item> arrListItem;

    public ItemAdapter(Context context, List<Item> arrListItem) {
        this.context = context;
        this.arrListItem = arrListItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.custom_item,viewGroup,false );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.imgRecycle.setImageResource(arrListItem.get(i).getPhoto());
        viewHolder.tvName.setText(arrListItem.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "click on"+arrListItem.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }
public void getFilte(List<Item> listItem){
        arrListItem = new ArrayList<>();
        arrListItem.addAll(listItem);
        notifyDataSetChanged();
}
    @Override
    public int getItemCount() {
        return arrListItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRecycle;
        TextView tvName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRecycle = itemView.findViewById(R.id.imgDauPhu);
            tvName = itemView.findViewById(R.id.tvNameDauPhu);

        }
    }
}
