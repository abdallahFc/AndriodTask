package com.example.myapplication.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;
import com.example.myapplication.model.Model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private static final int TYBE_ITEM = 0;
    private static final int TYBE_TitLE = 2;
    ArrayList<Model> list;
    OnItemClick itemClick;

    public Adapter(OnItemClick itemClick) {
        this.itemClick = itemClick;
    }

    public void setAdapter(ArrayList<Model> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0)
            return TYBE_TitLE;
        else if (position != 0) {
            return TYBE_ITEM;
        }
        return -1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == TYBE_ITEM) {
            view = layoutInflater.inflate(R.layout.item_row, parent, false);
            return new ViewHolder(view, TYBE_ITEM);
        } else if (viewType == TYBE_TitLE) {
            view = layoutInflater.inflate(R.layout.text_item, parent, false);
            return new ViewHolder(view, TYBE_TitLE);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") int position) {
        if (viewHolder.view_type == TYBE_ITEM) {
            Picasso.get().load(list.get(position - 1).getThumbnail())
                    .into(viewHolder.imageView);
            viewHolder.tx_title.setText(list.get(position - 1).getTitle());
            viewHolder.tx_catagory.setText(list.get(position - 1).getCategory());
            viewHolder.tx_price.setText("$" + list.get(position - 1).getPrice());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClick.onClick(list.get(position - 1));
                }
            });
        } else if (viewHolder.view_type == TYBE_TitLE) {
            viewHolder.textView2.setText(list.size() + " Results");
        }

    }
    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        int view_type;
        ImageView imageView;
        TextView tx_title, tx_catagory, tx_price;
        TextView textView, textView2;

        public ViewHolder(@NonNull View itemView, int viewType) {
            super(itemView);
            if (viewType == TYBE_ITEM) {
                imageView = itemView.findViewById(R.id.img_product);
                tx_title = itemView.findViewById(R.id.tx_title);
                tx_catagory = itemView.findViewById(R.id.tx_catagory);
                tx_price = itemView.findViewById(R.id.tx_price);
                view_type = 0;
            } else {
                textView = itemView.findViewById(R.id.textView);
                textView2 = itemView.findViewById(R.id.tx_result);
                view_type = 2;
            }
        }
    }
}
