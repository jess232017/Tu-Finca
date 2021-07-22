package com.example.tufinca2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.tufinca2.Modelos.Finca;
import com.example.tufinca2.Sqlite.BDAdapter;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

public class Detail_Activity extends AppCompatActivity {
    private Finca finca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setEnterTransition(new Fade());

        BDAdapter db = new BDAdapter(this);
        db.openDB();
        try
        {
            Cursor c = db.obtenerFinca();
            if(c.moveToNext()){
                finca = new Finca(c.getString(1),c.getString(2),c.getString(3),c.getString(4),
                        c.getString(5),c.getString(6),c.getString(7),c.getString(8),c.getString(9),
                        c.getString(10));

            }
            db.closeDB();

        } catch (Exception e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
        }

        showToolbar(finca.getNombre(), true);
        TextView txtAbreviatura = findViewById(R.id.txtAbreviatura);
        txtAbreviatura.setText(finca.getAbreviatura());
        TextView txtProposito = findViewById(R.id.txtProposito);
        txtProposito.setText(finca.getProposito());
        TextView txtDimension = findViewById(R.id.txtDimension);
        txtDimension.setText(finca.getArea1());
        TextView txtArea2 = findViewById(R.id.txtArea2);
        txtArea2.setText(finca.getArea2());
        TextView txtMedida = findViewById(R.id.txtMedida);
        txtMedida.setText(finca.getMedida());

        TextView txtMedidaL = findViewById(R.id.txtMedidaL);
        txtMedidaL.setText(finca.getMedidaL());

        TextView txtMedidaP = findViewById(R.id.txtMedidaP);
        txtMedidaP.setText(finca.getMedidaP());
    }

    public void showToolbar(String tittle, boolean upButton){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
        //CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
