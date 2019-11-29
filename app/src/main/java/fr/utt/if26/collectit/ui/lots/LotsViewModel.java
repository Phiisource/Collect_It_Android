package fr.utt.if26.collectit.ui.lots;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LotsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LotsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragment des lots");
    }

    public LiveData<String> getText() {
        return mText;
    }
}