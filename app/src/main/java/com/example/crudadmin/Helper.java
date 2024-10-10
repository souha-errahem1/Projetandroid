package com.example.crudadmin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Helper extends SQLiteOpenHelper {
    public Helper(@Nullable Context context) {
        super(context, "Componentmanager", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE component(_id INTEGER PRIMARY KEY ,Nom TEXT ,Description TEXT,Prix REAL , Stock TEXT)");
        db.execSQL("CREATE TABLE user(_id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS component");
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }  //supprime les anciennes tables si elles existent et appelle onCreate pour recréer les tables

    public void insertComponent (Component p){
        SQLiteDatabase db= this.getWritableDatabase();


        ContentValues cv= new ContentValues();   //objet ContentValues pour stocker les valeurs à insérer
        cv.put("Nom",p.getNom());
        cv.put("Description",p.getDescription());
        cv.put("Prix",p.getPrix());
        cv.put("Stock",p.getStock());
        db.insert("component",null,cv) ;
        db.close();
    }
    public void updateComponent(Component p)
    {
        SQLiteDatabase db =this.getWritableDatabase(); //instance de la base pour avoir l'accés à l'ecriture
        ContentValues cv =new ContentValues();
        cv.put("Nom",p.getNom());
        cv.put("Description",p.getDescription());
        cv.put("Prix",p.getPrix());
        cv.put("Stock",p.getStock());
        db.update("component",cv,"_id=?",new String[]{String.valueOf(p.getId())});
        db.close();
    }
    public void deleteComponent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("component","_id=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public Cursor getAllComponent(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM component",null);  //requête SQL Elle sélectionne toutes les colonnes de la table component
        return c ;

    }
    public Component getOneComponent(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=db.query("component",new String[]{"_id","Nom","Description","Prix","Stock"},"_id=?",new String[]{String.valueOf(id)},null,null,null);
        //accéder aux données ligne par ligne
        c.moveToFirst();   //déplace le curseur à la première ligne du résultat
        Component p = new Component(c.getInt(0),c.getString(1),c.getString(2),c.getDouble(3),c.getString(4));
        return p;
    }
    public void insertUser (String email, String password){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("email", email);
        cv.put("password", password); // Assurez-vous de hacher le mot de passe avant de l'insérer.
        db.insert("user",null,cv);
        db.close();
    }


    public boolean checkUser(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("user", new String[]{"email", "password"}, "email=?", new String[]{email}, null, null, null);
        if(cursor != null && cursor.moveToFirst()){
            int passwordIndex = cursor.getColumnIndex("password");
            if (passwordIndex == -1) {
                // La colonne "password" n'existe pas dans les résultats de la requête
                cursor.close();
                return false;
            }
            String storedPassword = cursor.getString(passwordIndex);
            cursor.close();
            return storedPassword.equals(password); // Assurez-vous de comparer le mot de passe haché.
        }
        return false;
    }

}

