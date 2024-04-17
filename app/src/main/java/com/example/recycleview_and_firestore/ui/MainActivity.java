package com.example.recycleview_and_firestore.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.recycleview_and_firestore.databinding.ActivityMainBinding;
import com.example.recycleview_and_firestore.domain.ItemRepository;
import com.example.recycleview_and_firestore.model.Item;
import com.example.recycleview_and_firestore.model.ItemAdapter;
import com.example.recycleview_and_firestore.domain.OnItemsLoaded;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ItemRepository.getItems(items -> {
//            Log.wtf("LOGG", items.toString());
            ItemAdapter adapter = new ItemAdapter();
            binding.recycler.setAdapter(adapter);
            adapter.setItems(items);
        });
    }
}

