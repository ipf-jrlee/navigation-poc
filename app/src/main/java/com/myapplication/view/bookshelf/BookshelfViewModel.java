package com.myapplication.view.bookshelf;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BookshelfViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BookshelfViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("내 서재");
    }

    public LiveData<String> getText() {
        return mText;
    }
}