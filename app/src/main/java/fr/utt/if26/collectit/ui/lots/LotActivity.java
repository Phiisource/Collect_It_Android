package fr.utt.if26.collectit.ui.lots;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import fr.utt.if26.collectit.R;

public class LotActivity extends AppCompatActivity {

    public static Button btnObtenirModifierLot;

    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lot);
        btnObtenirModifierLot = findViewById(R.id.btn_obtenir_modifier_lot);
        btnObtenirModifierLot.setText(R.string.obtenir_lot);
    }
}
