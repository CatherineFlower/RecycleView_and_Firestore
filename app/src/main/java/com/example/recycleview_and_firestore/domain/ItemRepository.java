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
//    private static final String DOCUMENT_USER_PHONE = "type";

//    public static void getItems(OnItemsLoaded onItemsLoaded){
//        firestore.collection(COLLECTION_ITEMS)
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot documentSnapshot) {
//                        List<Item> items = documentSnapshot.toObjects(Item.class);
//                        onItemsLoaded.onItemsLoaded(items);
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                                          @Override
//                                          public void onFailure(@NonNull Exception e) {
//                                              e.printStackTrace();
//                                          }
//                                      }
//                );
//                firestore.collection(COLLECTION_ITEMS)
//                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//                        if (value == null) {
//                            if (error != null) {
//                                new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                };
//                            } else {
//                                new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                };
//                            }
//                        } else {
//                            try {
//                                new OnSuccessListener<QuerySnapshot>() {
//                                    @Override
//                                    public void onSuccess(QuerySnapshot documentSnapshot) {
//                                        List<Item> items = documentSnapshot.toObjects(Item.class);
//                                        onItemsLoaded.onItemsLoaded(items);
//                                    }
//                                };
//                            } catch (Exception e) {
//                                new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                };
//                            }
//                        }
//                    }
//                });
//    }
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

    // Callback with error
//    public static void getItemById(String id, SuccessListener<Item> successListener, ErrorListener errorListener) {
//        getItemDocument(id).get()
//                .addOnSuccessListener(documentSnapshot -> {
//                    try {
//                        Item item = documentSnapshot.toObject(Item.class);
//                        assert item != null; // throw AssertionError if item not found
//                        successListener.onSuccess(item);
//                    } catch (ClassCastException exception) {
//                        errorListener.onError(Error.CAST);
//                    } catch (AssertionError exception) {
//                        errorListener.onError(Error.NOT_FOUND);
//                    }
//                })
//                .addOnFailureListener(exception -> {
//                    errorListener.onError(getError(exception));
//                });
//    }

//    public static Task<Void> updateUserPhone(String id, String phone) {
//        HashMap<String, Object> updateMap = new HashMap<>();
//        updateMap.put(DOCUMENT_USER_PHONE, phone);
//        return getItemDocument(id).update(updateMap);
//    }

    private static DocumentReference getItemDocument(String id) {
        return FirebaseFirestore.getInstance()
                .collection(COLLECTION_ITEMS)
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
