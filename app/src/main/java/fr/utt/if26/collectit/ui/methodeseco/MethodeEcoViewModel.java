package fr.utt.if26.collectit.ui.methodeseco;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MethodeEcoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MethodeEcoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragment méthodes éco");
    }

    public LiveData<String> getText() {
        return mText;
    }
}