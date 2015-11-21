package com.eribeiro.androidino;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;


public class ChoosingGsr extends ActionBarActivity {

    private SimpleCursorAdapter dados;
    private String chooseBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_gsr);

        ArrayList<String> aux = new ArrayList<>();
        BluetoothArduino obj = new BluetoothArduino();

        aux = (ArrayList<String>) obj.ListBluetoothAdapter();

        String[] vetor = new String[aux.size()];

        for (int i=0; i < aux.size(); i++) vetor[i] = aux.get(i);

        for (int i=0; i < vetor.length; i++) Log.e("VETOR", "vetor: " + vetor[i]);
/*        dados = new SimpleCursorAdapter(this,
                R.layout.activity_list_bluetooth,
                (android.database.Cursor) obj.ListBluetoothAdapter(),
                new String[]{"nome"},
                new int[]{R.id.nome}, 0);
        setListAdapter(dados);
        setContentView(R.layout.activity_list_bluetooth);*/

        final ListView listView1 = (ListView) findViewById(R.id.listView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, vetor);

        listView1.setAdapter(adapter);

        // ListView Item Click Listener
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                //int itemPosition     = position;

                // ListView Clicked item value
                //String  itemValue = (String) listView1.getItemAtPosition(position);

                chooseBluetooth = (String) listView1.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Você escolheu: " + chooseBluetooth, Toast.LENGTH_LONG)
                        .show();


                Intent intent = new Intent(ChoosingGsr.this, MainActivity.class);
                intent.putExtra("lastGsr",""+chooseBluetooth);
                startActivity(intent);
                finish();
            }

        });

    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("lastLamp",""+chooseBluetooth);

        startActivity(intent);
        finish();

    }

}
