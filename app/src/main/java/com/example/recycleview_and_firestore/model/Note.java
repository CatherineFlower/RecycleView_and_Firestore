package com.example.recycleview_and_firestore.model;

import androidx.annotation.NonNull;

public class Note {

    public String text;

    @NonNull
    @Override
    public String toString() {
        return "Note = {text=" + text + "}";
    }
}
