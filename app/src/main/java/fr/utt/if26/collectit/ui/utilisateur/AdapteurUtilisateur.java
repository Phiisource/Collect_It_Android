package fr.utt.if26.collectit.ui.utilisateur;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.Utilisateur;

public class AdapteurUtilisateur extends RecyclerView.Adapter<AdapteurUtilisateur.UtilisateurViewHolder> {

    class UtilisateurViewHolder extends RecyclerView.ViewHolder {
        private final TextView nom;
        private final TextView prenom;
        private final TextView points;

        private UtilisateurViewHolder(View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.nom);
            prenom = itemView.findViewById(R.id.prenom);
            points = itemView.findViewById(R.id.points);
        }
    }

    private final LayoutInflater inflater;
    //private final Context contextAdapteur;
    private List<Utilisateur> listeUtilisateurs;

    AdapteurUtilisateur(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UtilisateurViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.utilisateur, parent, false);
        return new UtilisateurViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull UtilisateurViewHolder holder, int position) {
        if (listeUtilisateurs != null) {
            Utilisateur current = listeUtilisateurs.get(position);
            holder.nom.setText("Nom : " + current.getNom());
            holder.prenom.setText("Prénom : " + current.getPrenom());
            holder.points.setText(Integer.toString(current.getPoint()) + " points");
        } else {
            holder.nom.setText("No intitule");
            holder.prenom.setText("No cout");
            holder.points.setText("No date");
        }
    }

    public List<Utilisateur> getUtilisateurs() {
        return listeUtilisateurs ;
    }

    public void setUtilisateurs(List<Utilisateur> newListeUtilisateurs) {
        listeUtilisateurs = newListeUtilisateurs;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listeUtilisateurs != null) {
            return listeUtilisateurs.size();
        } else {
            return 0;
        }
    }
}
