package com.orkotkreditru.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.orkotkreditru.MainClass;
import com.orkotkreditru.R;
import com.orkotkreditru.activities.DetailsOfferActivity;
import com.orkotkreditru.models.get.Liste;

import java.util.List;

public class AdapterCloak extends RecyclerView.Adapter<AdapterCloak.ViewHolder> {

    Context context;

    public void setDataList(List<Liste> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    List<Liste> dataList;


    public AdapterCloak(Context context, List<Liste> dataList) {
        this.context = context;
        this.dataList = dataList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);

        if (MainClass.font > 1){
                view = inflater.inflate(R.layout.fragment_large, parent, false);
        } else if (MainClass.font >= 1.3){
                view = inflater.inflate(R.layout.fragment_exlarge, parent, false);
        } else {
                view = inflater.inflate(R.layout.fragment, parent, false);
        }
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final Liste liste = dataList.get(position);

        String firstCreditSum = liste.getAmount().getFrom().toString();
        String percentRate = liste.getPercent().getFrom().toString();

        //setting holders to textViews
        holder.firstCreditSum.setText(firstCreditSum + "₴");
        holder.percentRate.setText(percentRate + "%");
        //setting image holder with glide
        Glide.with(context)
                .load(dataList.get(position)
                        .getImg())
                .centerInside()
                .into(holder.imgCompany);

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, DetailsOfferActivity.class);
                myIntent.putExtra("position", position);
                context.startActivity(myIntent);

            }
        });

        holder.click_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, DetailsOfferActivity.class);
                myIntent.putExtra("position", position);
                context.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        //declaring items
        ConstraintLayout click_layout;
        TextView firstCreditSum, percentRate;
        ImageView imgCompany;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //initializing
            click_layout = itemView.findViewById(R.id.click_layout);
            button = itemView.findViewById(R.id.button);
            imgCompany = itemView.findViewById(R.id.imgCompany);
            firstCreditSum = itemView.findViewById(R.id.firstCreditSum);
            percentRate = itemView.findViewById(R.id.percentRate);

        }
    }

    @Override
    public int getItemViewType(int position) {

        if(dataList.get(position).getTop()){
            return 1;
        } else {
            return 2;
        }
    }

}
