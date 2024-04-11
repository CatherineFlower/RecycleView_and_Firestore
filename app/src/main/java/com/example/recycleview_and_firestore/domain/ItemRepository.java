package com.example.recycleview_and_firestore.domain;

import com.example.recycleview_and_firestore.model.Error;
import com.example.recycleview_and_firestore.model.Item;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class ItemRepository {

    private static final String COLLECTION_USERS = "users";
    private static final String DOCUMENT_USER_PHONE = "type";

    public static void getItems(){

    }

    // Callback with error
    public static void getItemById(String id, SuccessListener<Item> successListener, ErrorListener errorListener) {
        getItemDocument(id).get()
                .addOnSuccessListener(documentSnapshot -> {
                    try {
                        Item item = documentSnapshot.toObject(Item.class);
                        assert item != null; // throw AssertionError if item not found
                        successListener.onSuccess(item);
                    } catch (ClassCastException exception) {
                        errorListener.onError(Error.CAST);
                    } catch (AssertionError exception) {
                        errorListener.onError(Error.NOT_FOUND);
                    }
                })
                .addOnFailureListener(exception -> {
                    errorListener.onError(getError(exception));
                });
    }

//    public static Task<Void> updateUserPhone(String id, String phone) {
//        HashMap<String, Object> updateMap = new HashMap<>();
//        updateMap.put(DOCUMENT_USER_PHONE, phone);
//        return getItemDocument(id).update(updateMap);
//    }

    private static DocumentReference getItemDocument(String id) {
        return FirebaseFirestore.getInstance()
                .collection(COLLECTION_USERS)
                .document(id);
    }

    private static Error getError(Exception exception) {
        if (exception instanceof FirebaseNetworkException) {
            return Error.NETWORK;
        } else {
            return Error.OTHER;
        }
    }
}
