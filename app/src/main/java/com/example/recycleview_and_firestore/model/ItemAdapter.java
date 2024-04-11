package com.example.recycleview_and_firestore.model;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.recycleview_and_firestore.databinding.ItemBinding;
import com.example.recycleview_and_firestore.ui.ItemViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends Adapter<ItemViewHolder> {
    private List<Item> items = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemBinding binding = ItemBinding.inflate(inflater, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Item> items) {
        int itemCount = getItemCount();
        this.items = new ArrayList<>(items);
        notifyItemRangeChanged(0, Math.max(itemCount, getItemCount()));
    }
}
