package com.example.tufinca2.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.example.tufinca2.Sqlite.Constants.Finca;
import com.example.tufinca2.Sqlite.Constants.Usuario;
import com.example.tufinca2.Sqlite.Constants.Vaca;

public class BDHelper extends SQLiteOpenHelper {
    private static final String NOMBRE_BASE_DATOS = "pedidos.db";

    private static final int VERSION_ACTUAL = 1;

    private Context contexto;

    interface Tablas{
        String USUARIO = "usuario";
        String VACA = "vaca";
        String FINCA = "finca";
    }

    public BDHelper(Context contexto) {
        super(contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = contexto;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL)",
                Tablas.USUARIO, BaseColumns._ID,
                Usuario.ID, Usuario.CORREO, Usuario.NOMBRE, Usuario.PASS));

        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL)",
                Tablas.VACA, BaseColumns._ID,
                Vaca.Id, Vaca.Nombre, Vaca.NumChip, Vaca.Genero, Vaca.Desarrollo, Vaca.Produce, Vaca.Peso, Vaca.FechaBirth, Vaca.Image));

        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL," +
                        "%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL)",
                Tablas.FINCA, BaseColumns._ID,
                Finca.Id, Finca.Nombre, Finca.Abreviatura, Finca.Proposito, Finca.Area1, Finca.Area2, Finca.Medida, Finca.MedidaL, Finca.MedidaP, Finca.Image));


        ContentValues valores = new ContentValues();
        String idUSUARIO = Usuario.generarIdNumero();
        valores.put(Usuario.ID, idUSUARIO);
        valores.put(Usuario.NOMBRE, "Default");
        valores.put(Usuario.CORREO, "Default");
        valores.put(Usuario.PASS, "Default");
        db.insertOrThrow(Tablas.USUARIO, null, valores);

        valores.clear();
        idUSUARIO = Finca.generarIdFinca();
        valores.put(Finca.Id, idUSUARIO);
        valores.put(Finca.Nombre, "Default");
        valores.put(Finca.Abreviatura, "Default");
        valores.put(Finca.Proposito, "Default");
        valores.put(Finca.Area1, "Default");
        valores.put(Finca.Area2, "Default");
        valores.put(Finca.Medida, "Default");
        valores.put(Finca.MedidaL, "Default");
        valores.put(Finca.MedidaP, "Default");
        valores.put(Finca.Image, "Default");
        db.insertOrThrow(Tablas.FINCA, null, valores);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Tablas.USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.VACA);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.FINCA);

        onCreate(db);
    }
}
