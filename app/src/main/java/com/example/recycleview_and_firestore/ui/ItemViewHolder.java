package com.example.recycleview_and_firestore.ui;

import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.recycleview_and_firestore.databinding.ItemBinding;
import com.example.recycleview_and_firestore.model.Item;

public class ItemViewHolder extends ViewHolder {

    private ItemBinding binding;

    public ItemViewHolder(ItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Item item) {
        binding.id.setText(item.getId());
        binding.name.setText(item.getName());
        binding.description.setText(item.getDescription());
        binding.type.setText(item.getType());
    }
}
