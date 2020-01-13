package fr.utt.if26.collectit.dataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "historique_points")
public class HistoriquePoints {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long id;
    @ColumnInfo(name = "label")
    private String label;
    @ColumnInfo(name = "points_ajoutes")
    private int pointsAjoutes;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "id_utilisateur")
    private String idUtilisateur;

    public HistoriquePoints(String label, int pointsAjoutes, String date, String idUtilisateur) {
        this.label = label;
        this.pointsAjoutes = pointsAjoutes;
        this.date = date;
        this.idUtilisateur = idUtilisateur;
    }

    @NonNull
    public String getLabel() {
        return label;
    }

    public int getPointsAjoutes() {
        return pointsAjoutes;
    }

    public String getDate() {
        return date;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public long getId() {
        return id;
    }

    public void setLabel(@NonNull String label) {
        this.label = label;
    }

    public void setPointsAjoutes(int pointsAjoutes) {
        this.pointsAjoutes = pointsAjoutes;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }
}
