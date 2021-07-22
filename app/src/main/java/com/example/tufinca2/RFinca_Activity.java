package com.example.tufinca2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aminography.choosephotohelper.ChoosePhotoHelper;
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback;
import com.bumptech.glide.Glide;
import com.example.tufinca2.Modelos.Finca;
import com.example.tufinca2.Sqlite.BDAdapter;
import com.google.android.material.appbar.AppBarLayout;

import java.util.Objects;

public class RFinca_Activity extends AppCompatActivity {
    private TextView txtNombre, txtAbreviatura;
    private TextView txtArea1, txtArea2;
    String Id;
    String Nombre;
    String Abreviatura;
    String Proposito;
    String Area1;
    String Area2;
    String Medida;
    String MedidaL;
    String MedidaP;
    String Image;

    private String PassTrought = "";
    private ChoosePhotoHelper choosePhotoHelper;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        BDAdapter db = new BDAdapter(this);
        db.openDB();
        try
        {
            Cursor c = db.obtenerFinca();
            if(c.moveToNext()){
                Id = c.getString(1);
                Nombre = c.getString(2);
                Abreviatura = c.getString(3);
                Proposito = c.getString(4);
                Area1 = c.getString(5);
                Area2 = c.getString(6);
                Medida = c.getString(7);
                MedidaL = c.getString(8);
                MedidaP = c.getString(9);
                Image = c.getString(10);

            }
            db.closeDB();

        } catch (Exception e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
        }

        if(Nombre.equals("Default") && Abreviatura.equals("Default") && Proposito.equals("Default")
                && Area1.equals("Default") && Area2.equals("Default") && Medida.equals("Default")
                && MedidaL.equals("Default") && MedidaP.equals("Default")){

        }else{
            if(!getIntent().hasExtra("PassTrought")){
                Intent intent = new Intent(this, Login_Activity.class);
                startActivity(intent);
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rfinca);

        imageView = findViewById(R.id.CargarImagen);
        choosePhotoHelper = ChoosePhotoHelper.with(RFinca_Activity.this)
                .asFilePath()
                .build(new ChoosePhotoCallback<String>() {
                    @Override
                    public void onChoose(String photo) {
                        Glide.with(RFinca_Activity.this)
                                .load(photo)
                                .into(imageView);
                        Image = photo;
                    }
                });

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Actualizar Datos");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if (getIntent().hasExtra("PassTrought")){
            PassTrought = getIntent().getStringExtra("PassTrought");
            TextView textView = findViewById(R.id.information);
            TextView Button = findViewById(R.id.ingresar);
            AppBarLayout appBarLayout = findViewById(R.id.appBar);
            appBarLayout.setVisibility(View.VISIBLE);
            textView.setText("Edite su Finca");
            Button.setText("Actualizar Datos");

        }

        txtNombre= findViewById(R.id.txtNombre);
        txtAbreviatura = findViewById(R.id.txtAbreviatura);
        txtArea1 = findViewById(R.id.txtArea1);
        txtArea2 = findViewById(R.id.txtArea2);
        final Spinner spnType = findViewById(R.id.spnType1);
        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                Proposito = spnType.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        final Spinner spnType2 = findViewById(R.id.spnType2);
        spnType2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                Medida = spnType2.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        final Spinner spnType3 = findViewById(R.id.spnType3);
        spnType3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                MedidaL = spnType3.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        final Spinner spnType4 = findViewById(R.id.spnType4);
        spnType4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                MedidaP = spnType4.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        if(Nombre.equals("Default") && Abreviatura.equals("Default") && Proposito.equals("Default")
                && Area1.equals("Default") && Area2.equals("Default") && Medida.equals("Default")
                && MedidaL.equals("Default") && MedidaP.equals("Default")){

        }else{
            if(PassTrought.equals("")){
                Intent intent = new Intent(this, Login_Activity.class);
                startActivity(intent);
            }else{
                txtNombre.setText(Nombre);
                txtAbreviatura.setText(Abreviatura);
                txtArea1.setText(Area1);
                txtArea2.setText(Area2);
            }
        }

    }

    public void Guardar(View view) {
        Nombre = Objects.requireNonNull(txtNombre.getText()).toString().trim();
        Abreviatura = Objects.requireNonNull(txtAbreviatura.getText()).toString().trim();
        Area1 = Objects.requireNonNull(txtArea1.getText()).toString().trim();
        Area2 = Objects.requireNonNull(txtArea2.getText()).toString().trim();

        try
        {
            if (Nombre.isEmpty() || Abreviatura.isEmpty() || Area1.isEmpty() || Area2.isEmpty()) {
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            }
            else{
                BDAdapter db = new BDAdapter(this);
                db.openDB();
                db.actualizarFinca(new Finca(null,Nombre,Abreviatura,Proposito,Area1,Area2,Medida,MedidaL,
                        MedidaP,Image));
                db.closeDB();
                Toast.makeText(this, "Los datos han sido actualizados", Toast.LENGTH_SHORT).show();
                assert PassTrought != null;
                if(PassTrought.equals("")){
                    Intent intent = new Intent(this, Dashboard_Activity.class);
                    startActivity(intent);
                }else{
                    onSupportNavigateUp();
                }
            }
        }catch (Exception e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        choosePhotoHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        choosePhotoHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void showToolbar(String tittle, View view){
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        Objects.requireNonNull(this).setSupportActionBar(toolbar);
        Objects.requireNonNull(this.getSupportActionBar()).setTitle(tittle);
        Objects.requireNonNull(this.getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void cargar(View view) {
        choosePhotoHelper.showChooser();
    }
}
