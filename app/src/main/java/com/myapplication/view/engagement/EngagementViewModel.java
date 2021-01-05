package com.myapplication.view.engagement;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EngagementViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EngagementViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("학습 내역");
    }

    public LiveData<String> getText() {
        return mText;
    }
}