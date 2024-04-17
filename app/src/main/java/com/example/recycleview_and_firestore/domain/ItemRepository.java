package com.example.recycleview_and_firestore.domain;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.recycleview_and_firestore.model.Error;
import com.example.recycleview_and_firestore.model.Item;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ItemRepository {

    @SuppressLint("StaticFieldLeak")
    private static final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private static final String COLLECTION_ITEMS = "items";

        public static void getItems(OnItemsLoaded onItemsLoaded){
            firestore.collection(COLLECTION_ITEMS)
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (value == null) {
                                if (error != null) {
                                    error.printStackTrace();
                                } else {
                                    error.printStackTrace();
                                }
                            } else {
                                try {
                                    List<Item> items = value.toObjects(Item.class);
                                    onItemsLoaded.onItemsLoaded(items);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
        }

}
