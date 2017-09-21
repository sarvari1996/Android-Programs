package com.example.matec.szakdoga_v1;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Szdoga extends AppCompatActivity {

    EditText editSuly, editNem, editMagassag, editKor;
    Spinner spinneraerob, spinnersulyzo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPref = getSharedPreferences("adatok", Context.MODE_PRIVATE);

        String suly = sharedPref.getString("kilo", "");
        String nem = sharedPref.getString("nem", "");
        String sulyzo = sharedPref.getString("sulyzo", "");
        String aerob = sharedPref.getString("aerob", "");
        String magassag = sharedPref.getString("magassag", "");
        String kor = sharedPref.getString("kor", "");

        if(suly.isEmpty() || nem.isEmpty() || sulyzo.isEmpty() || aerob.isEmpty() || magassag.isEmpty() || kor.isEmpty()){
            Toast.makeText(getBaseContext(), "Adja meg az adatait", Toast.LENGTH_LONG).show();
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_szdoga);
            Intent i = new Intent(Szdoga.this,adatok.class);
            startActivity(i);
        }
        else{

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_szdoga);

            AlertDialog.Builder builder = new AlertDialog.Builder(Szdoga.this);
                builder.setTitle("Szeretne végezni bemelegítést a gyakorlatok előtt?");

            builder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    Intent i = new Intent(Szdoga.this,Bemelegit.class);
                    startActivity(i);
                }
            });
            builder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    // User cancelled the dialog
                }
            });

            builder.create();
            builder.show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button freestyle = (Button)findViewById(R.id.btnfreestyle);
        Button ero = (Button) findViewById(R.id.btnero);
        Button adatok = (Button) findViewById(R.id.btnadatok);
        Button kilep = (Button) findViewById(R.id.btnkilep);


        freestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Szdoga.this,Freestyle_1.class);
                startActivity(i);
            }
        });

        adatok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Szdoga.this, com.example.matec.szakdoga_v1.adatok.class);
                startActivity(i);
            }
        });
        /*
        opciok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "SCORE!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
        kilep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "SCORE!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });*/
    }

    public void Exit(View v) {
        // TODO Auto-generated method stub
        finish();
        System.exit(0);
    }


}