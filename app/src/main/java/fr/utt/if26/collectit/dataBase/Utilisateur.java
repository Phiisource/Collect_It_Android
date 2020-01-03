package fr.utt.if26.collectit.dataBase;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "utilisateur")
public class Utilisateur {
    @PrimaryKey
    @NonNull
    private String email;
    @ColumnInfo(name = "nom")
    private String nom;
    @ColumnInfo(name = "prenom")
    private String prenom;
    @ColumnInfo(name = "point")
    private int point;
    @ColumnInfo(name = "admin")
    private boolean admin;


    public Utilisateur(String email, String nom, String prenom, int point, boolean admin) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.point = point;
        this.admin = admin;
    }

    public Utilisateur() {

    }

    @NonNull
    public String getEmail() {
        return email;
    }

    public int getPoint() {
        return point;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

}
