package fr.utt.if26.collectit;

import java.util.Observable;

public class ObservableBoolean extends Observable {
    private Boolean isbackOffice;

    public boolean getIsbackOffice() {
        return isbackOffice;
    }

    public void setIsbackOffice(Boolean isbackOffice) {
        this.isbackOffice = isbackOffice;
        this.setChanged();
        this.notifyObservers(isbackOffice);
    }

}
