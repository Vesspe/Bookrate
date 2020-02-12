package com.example.bookrate.ui.browse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookrate.Book;
import com.example.bookrate.FirebaseDatabaseHelper;
import com.example.bookrate.R;
import com.example.bookrate.RecyclerView_Config;

import java.util.List;

public class BrowseFragment extends Fragment {

    private BrowseViewModel browseViewModel;
    private RecyclerView mRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        browseViewModel =
                new ViewModelProvider(this).get(BrowseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_browse, container, false);
        mRecyclerView = root.findViewById(R.id.recyclerView_books);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        new FirebaseDatabaseHelper().readBooks(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Book> books, List<String> keys) {
                new RecyclerView_Config().setConfig(mRecyclerView, getActivity(), books, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

            }
        });
        return root;
    }
}


        /*final TextView textView = root.findViewById(R.id.text_home);
        browseViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/