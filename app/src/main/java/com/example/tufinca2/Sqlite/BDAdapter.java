package com.example.tufinca2.Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.tufinca2.Modelos.Finca;
import com.example.tufinca2.Modelos.Vaca;
import com.example.tufinca2.Sqlite.BDHelper.Tablas;
import com.example.tufinca2.Sqlite.Constants.Usuario;


public class BDAdapter {
    //-----------------
    private SQLiteDatabase db;
    private BDHelper helper;



    public BDAdapter(Context c) {
        helper=new BDHelper(c);
    }

    //Abrir Base de Datos
    public void openDB()
    {
        try
        {
            db=helper.getWritableDatabase();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    //Cerrar Base de Datos
    public void closeDB()
    {
        try
        {
            helper.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    //Obtener todas las Vaca
    public Cursor obtenerVaca()
    {
        String sql="SELECT * FROM " + Tablas.VACA + ";";
        return db.rawQuery(sql,null);
    }

    //Obtener FINCA
    public Cursor obtenerFinca()
    {
        String sql="SELECT * FROM " + Tablas.FINCA + ";";
        return db.rawQuery(sql,null);
    }

    //Agregar rifas
    public String insertarVaca(Vaca vacas) {
        ContentValues valores = new ContentValues();
        // Generar Pk
        String idProducto = Constants.Vaca.generarIdVaca();
        valores.put(Constants.Vaca.Id, idProducto);
        valores.put(Constants.Vaca.Nombre, vacas.getNombre());
        valores.put(Constants.Vaca.NumChip, vacas.getNumChip());
        valores.put(Constants.Vaca.Genero, vacas.getGenero());
        valores.put(Constants.Vaca.Desarrollo, vacas.getDesarrollo());
        valores.put(Constants.Vaca.Produce, vacas.getProduce());
        valores.put(Constants.Vaca.Peso, vacas.getPeso());
        valores.put(Constants.Vaca.FechaBirth, vacas.getFechaBirth());
        valores.put(Constants.Vaca.Image, vacas.getImage());

        db.insertOrThrow(Tablas.VACA, null, valores);

        return idProducto;
    }

    public boolean actualizarVaca(Vaca vacas) {
        ContentValues valores = new ContentValues();
        valores.put(Constants.Vaca.Nombre, vacas.getNombre());
        valores.put(Constants.Vaca.NumChip, vacas.getNumChip());
        valores.put(Constants.Vaca.Genero, vacas.getGenero());
        valores.put(Constants.Vaca.Desarrollo, vacas.getDesarrollo());
        valores.put(Constants.Vaca.Produce, vacas.getProduce());
        valores.put(Constants.Vaca.Peso, vacas.getPeso());
        valores.put(Constants.Vaca.FechaBirth, vacas.getFechaBirth());
        valores.put(Constants.Vaca.Image, vacas.getImage());

        String whereClause = String.format("%s=?", Constants.Vaca.Id);
        String[] whereArgs = {vacas.getId()};

        int resultado = db.update(Tablas.VACA, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean eliminarVaca(String idVaca) {
        String whereClause = String.format("%s=?", Constants.Vaca.Id);
        String[] whereArgs = {idVaca};

        int resultado = db.delete(Tablas.VACA, whereClause, whereArgs);

        return resultado > 0;
    }

    public Cursor obtenerUsuario() {
        String sql1="SELECT * FROM " + Tablas.USUARIO + " WHERE " + Usuario.ID + " LIKE '%" + "USER-1234" + "%'";
        return db.rawQuery(sql1,null);
    }

    public boolean actualizarFinca(Finca finca) {
        ContentValues valores = new ContentValues();
        valores.put(Constants.Finca.Nombre, finca.getNombre());
        valores.put(Constants.Finca.Abreviatura, finca.getAbreviatura());
        valores.put(Constants.Finca.Proposito, finca.getProposito());
        valores.put(Constants.Finca.Area1, finca.getArea1());
        valores.put(Constants.Finca.Area2, finca.getArea2());
        valores.put(Constants.Finca.Medida, finca.getMedida());
        valores.put(Constants.Finca.MedidaL, finca.getMedidaL());
        valores.put(Constants.Finca.MedidaP, finca.getMedidaP());
        valores.put(Constants.Finca.Image, finca.getImage());

        String whereClause = String.format("%s=?", Usuario.ID);
        String[] whereArgs = {"FINCA-1234"};


        int resultado = db.update(Tablas.FINCA, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean actualizarUsuario(String Name, String Mail, String Pass) {
        ContentValues valores = new ContentValues();
        valores.put(Usuario.NOMBRE, Name);
        valores.put(Usuario.CORREO, Mail);
        valores.put(Usuario.PASS, Pass);

        String whereClause = String.format("%s=?", Usuario.ID);
        String[] whereArgs = {"USER-1234"};

        int resultado = db.update(Tablas.USUARIO, valores, whereClause, whereArgs);

        return resultado > 0;
    }
}