package com.example.crudadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    EditText editTextAdresse, editTextMotDePasse;
    Button boutonInscrire, boutonConnecter;
    Helper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        editTextAdresse = findViewById(R.id.user);
        editTextMotDePasse = findViewById(R.id.mot);
        boutonInscrire = findViewById(R.id.button3);
        boutonConnecter = findViewById(R.id.button2);
        dbHelper = new Helper(this);

        boutonInscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextAdresse.getText().toString();
                String password = editTextMotDePasse.getText().toString();
                if (!email.isEmpty() && !password.isEmpty()) {
                    dbHelper.insertUser(email, password);
                    Toast.makeText(MainActivity3.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity3.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                }
            }
        });

        boutonConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextAdresse.getText().toString();
                String password = editTextMotDePasse.getText().toString();
                if (dbHelper.checkUser(email, password)) {
                    Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity3.this, "Email ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

