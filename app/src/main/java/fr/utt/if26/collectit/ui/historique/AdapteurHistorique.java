package fr.utt.if26.collectit.ui.historique;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.HistoriquePoints;

public class AdapteurHistorique extends RecyclerView.Adapter<AdapteurHistorique.HistoriqueViewHolder> {

    class HistoriqueViewHolder extends RecyclerView.ViewHolder {
        private final TextView label;
        private final TextView point;
        private final TextView date;

        private HistoriqueViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.tv_label_historique);
            point = itemView.findViewById(R.id.tv_ajout_point);
            date = itemView.findViewById(R.id.tv_date);
        }
    }

    private final LayoutInflater inflater;
    //private final Context contextAdapteur;
    private List<HistoriquePoints> listeHistorique;

    public AdapteurHistorique(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HistoriqueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.historique, parent, false);
        return new HistoriqueViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull HistoriqueViewHolder holder, int position) {
        if (listeHistorique != null) {
            HistoriquePoints current = listeHistorique.get(position);
            holder.label.setText(current.getLabel());
            holder.point.setText("Ajout : + " + Integer.toString(current.getPointsAjoutes()) + " points");
            if (TextUtils.equals(current.getLabel(), "Ajout de points par un admin")) {
                holder.point.setText("Ajout : + " + Integer.toString(current.getPointsAjoutes()) + " points");
            } else {
                holder.point.setText("Lot obtenu : - " + Integer.toString(current.getPointsAjoutes()) + " points");
            }
            holder.date.setText(current.getDate());
        } else {
            holder.label.setText("No intitule");
            holder.point.setText("No cout");
            holder.date.setText("No date");
        }
    }

    public List<HistoriquePoints> getHistorique() {
        return listeHistorique;
    }

    public void setHistorique(List<HistoriquePoints> newListeLots) {
        listeHistorique = newListeLots;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(listeHistorique != null) {
            return listeHistorique.size();
        } else {
            return 0;
        }
    }
}
