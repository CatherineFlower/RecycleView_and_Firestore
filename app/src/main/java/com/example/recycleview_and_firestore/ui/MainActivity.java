package com.example.recycleview_and_firestore.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.recycleview_and_firestore.R;
import com.example.recycleview_and_firestore.databinding.ActivityMainBinding;
import com.example.recycleview_and_firestore.databinding.ItemBinding;
import com.example.recycleview_and_firestore.model.Item;
import com.example.recycleview_and_firestore.model.ItemAdapter;
import com.google.firebase.Firebase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

//    private List<Item> items = new

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("items")
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    List<Item> item = documentSnapshot.toObjects(Item.class);
                    Log.wtf("LOGG", item.toString());
                })
                .addOnFailureListener(Throwable::printStackTrace);
//        ItemAdapter adapter = new ItemAdapter();
//        binding.recycler.setAdapter(adapter);
//        adapter.setItems(items);
    }
}

