package com.myapplication.view.additional;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdditionalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AdditionalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("더보기");
    }

    public LiveData<String> getText() {
        return mText;
    }
}