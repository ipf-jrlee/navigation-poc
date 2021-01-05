package com.myapplication.view.additional.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WordViewModel extends ViewModel {

    private MutableLiveData<Integer> count = new MutableLiveData<>();

    public WordViewModel() {
        count.setValue(1);
    }

    public MutableLiveData<Integer> getCount() {
        return count;
    }

    public void increment() {
        int current = count.getValue();

        count.setValue(++current);
    }

}
