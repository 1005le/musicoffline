package com.example.admin.musicoffline.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.musicoffline.R;
import com.example.admin.musicoffline.model.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 20/1/2019.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{

      Context context;
      //List<Song> songList;
     ArrayList<Song> songList;

     public SongAdapter(Context context, ArrayList<Song> songList) {
          this.context = context;
          this.songList = songList;
     }

     @Override
     public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

          LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
          View view = layoutInflater.inflate(R.layout.item_song,parent, false );

          return new ViewHolder(view);
     }

     @Override
     public void onBindViewHolder(ViewHolder holder, int position) {
          Song song = songList.get(position);
          Log.d("size",songList.size()+"");
          holder.tvnameSong.setText(songList.get(position).getName());
          holder.tvnameArtist.setText(songList.get(position).getNameArtist());
     }

     @Override
     public int getItemCount() {
          return songList.size();
     }

     public class ViewHolder extends RecyclerView.ViewHolder {
          TextView tvnameSong;
          TextView tvnameArtist;

          public ViewHolder(View itemView) {
               super(itemView);

               tvnameSong = itemView.findViewById(R.id.tvName);
               tvnameArtist = itemView.findViewById(R.id.tvNameArtist);

          }
     }
}
