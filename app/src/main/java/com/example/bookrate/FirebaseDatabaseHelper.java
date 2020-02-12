package com.example.bookrate;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReference;
    private List<Book> books = new ArrayList<>();


    public interface DataStatus{
        void DataIsLoaded(List<Book> books, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        this.mDatabase = FirebaseDatabase.getInstance();
        this.mReference = mDatabase.getReference("books");
    }

    public void readBooks(final DataStatus dataStatus){
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                books.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Book book = keyNode.getValue(Book.class);
                    books.add(book);
                }
                dataStatus.DataIsLoaded(books, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}


/*        //TODO setValue at /books failed: DatabaseError: Permission denied
        DatabaseReference ref = database.getReference("books");
        DatabaseReference booksRef = ref.child("books");
        Map<String, Book> books = new HashMap<>();
        books.put("first", new Book("author", "title", "image", "id", "desc" , 3.5));
        booksRef.setValue(books);*/