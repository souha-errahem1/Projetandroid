package com.example.crudadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText nom,description,prix,stock;
    Button button;
Helper h=new Helper(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nom=findViewById(R.id.Nom);
        description=findViewById(R.id.Description);
        prix = findViewById(R.id.Prix);
        stock=findViewById(R.id.Stock);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Component p = new Component(nom.getText().toString(),description.getText().toString(),Double.parseDouble(prix.getText().toString()),stock.getText().toString());

               h.insertComponent(p);
                Intent i =new Intent(MainActivity.this,ListeComponent.class);
                startActivity(i);
            }
        });

    }
}