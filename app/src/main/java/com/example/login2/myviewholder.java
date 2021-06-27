package com.example.login2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myviewholder  extends RecyclerView.ViewHolder {

    public ImageView Rimage;
    public TextView Rlocation,Rleaguename;
    public View v;

    public myviewholder(@NonNull View itemView) {
        super(itemView);
        Rimage=itemView.findViewById(R.id.my_image);
        Rlocation=itemView.findViewById(R.id.location_name);
        Rleaguename=itemView.findViewById(R.id.league_name);
        v=itemView;
    }
}