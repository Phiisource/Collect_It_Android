package fr.utt.if26.collectit.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UtilisateurDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Utilisateur utilisateur);

    @Query("DELETE FROM utilisateur")
    void deleteAll();

    @Query("UPDATE utilisateur SET nom = :nom, prenom = :prenom, point = :point WHERE email = :email")
    void update(String nom, String prenom, int point, String email);

    @Query("SELECT * from utilisateur ORDER BY nom ASC")
    LiveData<List<Utilisateur>> getAlphabetizedUtilisateurs();
}
