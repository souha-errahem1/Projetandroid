package com.example.crudadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListeComponent extends AppCompatActivity {
 ListView ls;
 Button b2;
 Helper h=new Helper(ListeComponent.this);  //une instance de la classe Helper pour interagir avec la base de données

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_component);
        ls=findViewById(R.id.lst);
        Cursor c = h.getAllComponent();      //: Récupère tous les composants de la base de données sous forme de Cursor
        SimpleCursorAdapter adapter =new SimpleCursorAdapter(ListeComponent.this,R.layout.item,c,
                new String[]{c.getColumnName(0),c.getColumnName(1),c.getColumnName(2),c.getColumnName(3),c.getColumnName(4)},new int []{R.id.id,R.id.nom,R.id.description,R.id.prix,R.id.stock},1);  //Crée un adaptateur pour remplir la ListView avec les données du Cursor
        ls.setAdapter(adapter);
ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {        // Définit un écouteur pour les clics sur les éléments de la liste.
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        TextView t= view.findViewById(R.id.id);
        Intent x = new Intent(ListeComponent.this,DetailComponent.class);
        x.putExtra("id",t.getText().toString());
        startActivity(x);
    }
});
        b2= findViewById(R.id.b2);
 b2.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {
         Intent i = new Intent(ListeComponent.this,MainActivity2.class);
         startActivity(i);
     }
 });
    }
}