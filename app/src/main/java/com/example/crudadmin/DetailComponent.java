package com.example.crudadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DetailComponent extends AppCompatActivity {
 EditText nom,description,prix,stock;
 Button modifier,supprimer,b2;
 String id;
Helper h =new Helper(DetailComponent.this); //une instance de la classe Helper pour interagir avec la base de données
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_component);
        nom=findViewById(R.id.nom);
        description=findViewById(R.id.description);
        prix=findViewById(R.id.prix);
        stock=findViewById(R.id.stock);
        modifier=findViewById(R.id.modifier);
        supprimer=findViewById(R.id.supprimer);

        id=getIntent().getStringExtra("id");
        Component p = h.getOneComponent(Integer.parseInt(id)); //Utilise l'ID pour récupérer les détails du composant depuis la base de données
        nom.setText(p.getNom());
        description.setText(p.getDescription());           //Affiche les détails du composant dans les champs de texte
        prix.setText(String.valueOf(p.getPrix()));
        stock.setText(p.getStock());

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Component comp = new Component(Integer.parseInt(id), nom.getText().toString(), description.getText().toString(), Double.parseDouble(prix.getText().toString()), stock.getText().toString());
                h.updateComponent(comp);   //Met à jour le composant dans la base de données avec les valeur qui existe dans le new comp
                Intent i = new Intent(DetailComponent.this, ListeComponent.class);
                startActivity(i);

            }
        });
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                h.deleteComponent(Integer.parseInt(id));
                Intent i = new Intent(DetailComponent.this, ListeComponent.class);
                startActivity(i);
            }
        });
        b2= findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DetailComponent.this,ListeComponent.class);
                startActivity(i);
            }
        });






    }
}