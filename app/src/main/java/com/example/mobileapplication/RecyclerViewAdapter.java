package com.example.mobileapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
/*
    private static final String TAG = "test.sliit.recyclerview.RecyclerViewAdapter";
*/

    private Context mContext;
    private ArrayList<Ticket> ticketList = new ArrayList<Ticket>();

    public RecyclerViewAdapter(ArrayList<Ticket> listOfTickets, Context mContext) {

        this.mContext = mContext;
        this.ticketList = listOfTickets;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.ticketText.setText(ticketList.get(position).Text);
        holder.ticketStatus.setText(ticketList.get(position).Status);
      /*  holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext,mImageNames.get(position),Toast.LENGTH_SHORT).show();
            }
        });*/
    }
    @Override
    public int getItemCount() {
        return ticketList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        Button ticketStatus;
        TextView ticketText;
        RelativeLayout parentLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ticketText = itemView.findViewById(R.id.info_text2);
            ticketStatus = itemView.findViewById(R.id.button_status);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
