package com.example.smartgarden.FBDB;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {
    FirebaseDatabase database;
    DatabaseReference myRef, mDatabase;

    public Firebase() {
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public Firebase(String message) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(message);
    }

    public DatabaseReference getMyRef() {
        return myRef;
    }

    public void setMyRef(DatabaseReference myRef) {
        this.myRef = myRef;
    }
}
