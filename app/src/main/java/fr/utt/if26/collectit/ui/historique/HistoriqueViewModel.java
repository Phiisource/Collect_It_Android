package fr.utt.if26.collectit.ui.historique;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoriqueViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HistoriqueViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragment historique");
    }

    public LiveData<String> getText() {
        return mText;
    }
}