package com.example.budgettrackerwithbottommenu.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This project is a result of joint work of  Eray Dertsiz, Eren Kara and Ruken Boyacı");
    }

    public LiveData<String> getText() {
        return mText;
    }
}