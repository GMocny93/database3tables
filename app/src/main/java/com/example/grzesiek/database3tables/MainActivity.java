package com.example.grzesiek.database3tables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView newInformacje = (TextView) findViewById(R.id.informacje);
        TextView newWaga = (TextView) findViewById(R.id.waga);
        TextView newObwody = (TextView) findViewById(R.id.obwody);


        DatabaseHandler db = new DatabaseHandler(this);

        db.addInformation(new information("Grzesiek", 184, 120.5, 100.5, 25));
        db.addWeight(new weight(2018, 3, 8, 119.5));
        db.addCircuit(new circuit(2018, 4, 10, 116.0, 117.5));

        List<information> information = db.getAllInformation();
        for (information inf : information) {
            newInformacje.setText("Witaj " + inf.getName() + "! Przy swoim wzroscie roznwym " + inf.getHeight()
                    + " w wieku " + inf.getAge() + " ważysz " + inf.getWeight() + ". Jednak Twoim celem jest waga rowna "
                    + inf.getTargetWeight());
        }

        List<weight> weight = db.getAllWeight();
        for (weight wg : weight) {
            newWaga.setText("rok " + wg.getYear() + " miesiac " + wg.getMonth()
                    + " dzien " + wg.getDay() + " waga " + wg.getWeight());


            List<circuit> circuit = db.getAllCircuit();
            for (circuit ob : circuit) {
                newObwody.setText("rok " + ob.getYear() + " miesiac " + ob.getMonth()
                        + " dzien " + ob.getDay() + " klatka " + ob.getChest() + " pas " + ob.getWaist());

            }

        }
    }
}
