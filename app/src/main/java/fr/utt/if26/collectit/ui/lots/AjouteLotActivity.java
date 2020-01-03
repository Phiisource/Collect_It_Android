package fr.utt.if26.collectit.ui.lots;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import fr.utt.if26.collectit.R;
import fr.utt.if26.collectit.dataBase.Lot;

public class AjouteLotActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText label;
    private EditText cout;
    private EditText date;
    private LotsViewModel lotsViewModel;
    private Button btnAjoute;


    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajoute_lot);

        label = findViewById(R.id.et_label);
        cout = findViewById(R.id.et_cout);
        date = findViewById(R.id.et_date_validite);
        btnAjoute = findViewById(R.id.btn_ajoute_lot);
        btnAjoute.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_ajoute_lot){
            if(! TextUtils.isEmpty(label.getText().toString()) && ! TextUtils.isEmpty(date.getText().toString()) && ! TextUtils.isEmpty(date.getText().toString())) {
                try {
                    lotsViewModel = new ViewModelProvider(this).get(LotsViewModel.class);
                    String intitule = label.getText().toString();
                    Integer coutLot = Integer.parseInt(cout.getText().toString());
                    String dateLot = date.getText().toString();
                    Lot lot = new Lot(intitule, coutLot, dateLot);
                    lotsViewModel.insert(lot);
                    Toast.makeText(this, "L'insertion a réussi", Toast.LENGTH_SHORT).show();
                } catch(NumberFormatException e) {
                    Toast.makeText(this, "Le champ coût doit être un entier", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Veuillez entrer tous les champs (Le champ coût doit être un entier)", Toast.LENGTH_SHORT).show();
            }
        }
    }
}