package com.example.recycleview_and_firestore.domain;

import com.example.recycleview_and_firestore.model.Item;

import java.util.List;

public interface OnItemsLoaded {
    void onItemsLoaded(List<Item> items);
}
