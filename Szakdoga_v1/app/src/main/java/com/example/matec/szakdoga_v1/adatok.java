package com.example.matec.szakdoga_v1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class adatok extends AppCompatActivity {
    Spinner aerob; //Dropdown menü=Spinner
    Spinner sulyzo;
    Spinner nem;
    ArrayAdapter<CharSequence> aerobArray; //A lenyíló menüt kezeli
    ArrayAdapter<CharSequence> sulyzoArray;
    ArrayAdapter<CharSequence> nemArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adatok);

        //(EditText) findViewById(R.id.editSuly) = "asd";

        //Azt keresi meg hogy hova kell az adatokat betölteni
        aerob = (Spinner) findViewById(R.id.spinnerAerob);
        sulyzo = (Spinner) findViewById(R.id.spinnerSulyzo);
        nem = (Spinner) findViewById(R.id.spinnerNem);

        //Lekéri a tömbből az adatokat és megadja a dropdownmenü elemeit.
        aerobArray = ArrayAdapter.createFromResource(this, R.array.select_aerob ,android.R.layout.simple_spinner_item);
        aerobArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aerob.setAdapter(aerobArray);

        sulyzoArray = ArrayAdapter.createFromResource(this, R.array.select_sulyzo ,android.R.layout.simple_spinner_item);
        sulyzoArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sulyzo.setAdapter(sulyzoArray);

        nemArray = ArrayAdapter.createFromResource(this, R.array.select_nem ,android.R.layout.simple_spinner_item);
        nemArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nem.setAdapter(nemArray);

    }

    EditText editSuly, editMagassag, editKor;
    Spinner  spinnerAerob, spinnerSulyzo, spinnerNem;
    int editMagassage, editSulye, editKore;

     public void saveData(View v){

         editSuly = (EditText) findViewById(R.id.editSuly);
         editKor = (EditText) findViewById(R.id.editKor);
         spinnerNem = (Spinner) findViewById(R.id.spinnerNem);
         spinnerSulyzo = (Spinner) findViewById(R.id.spinnerSulyzo);
         spinnerAerob = (Spinner) findViewById(R.id.spinnerAerob);
         editMagassag = (EditText) findViewById(R.id.editMagassag);

        //Ha üresek az inputok, akkor hibát dob ki
         if(editKor.toString().isEmpty() || editSuly.toString().isEmpty() || spinnerNem.toString().isEmpty() || spinnerSulyzo.toString().isEmpty() || spinnerAerob.toString().isEmpty() || editMagassag.toString().isEmpty()){
             Toast.makeText(getBaseContext(), "Töltsd ki a mezőket", Toast.LENGTH_LONG).show();
         }
         else{
             try{
                 editMagassage = Integer.parseInt(editMagassag.getText().toString());
                 editSulye = Integer.parseInt(editSuly.getText().toString());
                 editKore = Integer.parseInt(editKor.getText().toString());

                 SharedPreferences sharedPref = getSharedPreferences("adatok", Context.MODE_PRIVATE);
                 SharedPreferences.Editor editor = sharedPref.edit();
                 editor.putString("kilo", editSuly.getText().toString());
                 editor.putString("nem", spinnerNem.getSelectedItem().toString());
                 editor.putString("sulyzo", spinnerSulyzo.getSelectedItem().toString());
                 editor.putString("aerob", spinnerAerob.getSelectedItem().toString());
                 editor.putString("magassag", editMagassag.getText().toString());
                 editor.putString("kor", editKor.getText().toString());

                 editor.apply();

                 // Ha minden helyes, akkor elmenti az adatokat
                 Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_LONG).show();

                 Intent i = new Intent(adatok.this,Szdoga.class);
                 startActivity(i);

                 AlertDialog.Builder builder = new AlertDialog.Builder(adatok.this);
                 builder.setTitle("Szeretne végezni bemelegítést a gyakorlatok előtt?");

                 builder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int id) {
                         Intent i = new Intent(adatok.this,Bemelegit.class);
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
             //Ellenőrzi hog számot adott e meg, ha nem akkor kiírja hogy azt adjon meg
             catch(NumberFormatException e){
                 Toast.makeText(getBaseContext(), "Számot adj meg", Toast.LENGTH_LONG).show();
             }
         }
     }

}
