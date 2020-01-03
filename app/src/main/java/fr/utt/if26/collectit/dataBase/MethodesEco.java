package fr.utt.if26.collectit.dataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "methodes_eco")
public class MethodesEco {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long id;
    @ColumnInfo(name = "description")
    private String description;

    public MethodesEco(String description) {
        this.description = description;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }
}
