package com.example.bookrate.ui.search;

import android.widget.EditText;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookrate.R;

public class SearchViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SearchViewModel() {


        mText = new MutableLiveData<>();
        mText.setValue("Wprowadź fragment tytułu książki, autora lub fragment " +
                "tekstu aby odnaleźć szukaną książkę");
    }

    public LiveData<String> getText() {
        return mText;
    }


}