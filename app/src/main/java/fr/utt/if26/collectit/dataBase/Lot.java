package fr.utt.if26.collectit.dataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lot")
public class Lot {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long id;
    @ColumnInfo(name = "intitule")
    private String intitule;
    @ColumnInfo(name = "cout")
    private int cout;
    @ColumnInfo(name = "date")
    private String date;

    public Lot(String intitule, int cout, String date) {
        this.intitule = intitule;
        this.cout = cout;
        this.date = date;
    }

    @NonNull
    public String getIntitule() {
        return intitule;
    }

    public int getCout() {
        return cout;
    }

    public String getDate() {
        return date;
    }

    public long getId() {
        return id;
    }

    public void setIntitule(@NonNull String intitule) {
        this.intitule = intitule;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
