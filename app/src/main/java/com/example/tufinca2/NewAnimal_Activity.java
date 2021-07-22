package com.example.tufinca2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aminography.choosephotohelper.ChoosePhotoHelper;
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback;
import com.bumptech.glide.Glide;
import com.example.tufinca2.Modelos.Vaca;
import com.example.tufinca2.Sqlite.BDAdapter;

import java.util.Objects;

public class NewAnimal_Activity extends AppCompatActivity {
    private TextView nombre, chip, peso, fechabirth;
    private String name, number, weight, datebirth, gender, tipo,produce, Image = "";
    private ChoosePhotoHelper choosePhotoHelper;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_animal);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Agregar Animal");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        imageView = findViewById(R.id.CargarImagen);
        choosePhotoHelper = ChoosePhotoHelper.with(NewAnimal_Activity.this)
                .asFilePath()
                .build(new ChoosePhotoCallback<String>() {
                    @Override
                    public void onChoose(String photo) {
                        Glide.with(NewAnimal_Activity.this)
                                .load(photo)
                                .into(imageView);
                        Image = photo;
                    }
                });


        nombre = findViewById(R.id.nombre);
        chip = findViewById(R.id.chip);
        peso = findViewById(R.id.peso);
        fechabirth = findViewById(R.id.fechabirth);

        final Spinner spnType = findViewById(R.id.spnType1);
        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                gender = spnType.getSelectedItem().toString();
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
                tipo = spnType2.getSelectedItem().toString();
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
                produce = spnType3.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    public void Register(View view) {
        BDAdapter BD = new BDAdapter(this);
        BD.openDB();
        if(GetText()){
            BD.insertarVaca(new Vaca(null, name, number, gender, tipo, produce, weight, datebirth, Image));
            Toast.makeText(NewAnimal_Activity.this, "Animal Agregado", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }

        BD.closeDB();
    }

    private boolean GetText(){
        try {
            name = nombre.getText().toString().trim();
            number = chip.getText().toString().trim();
            weight = peso.getText().toString().trim();
            datebirth = fechabirth.getText().toString().trim();
            return true;
        }catch (Exception e){
            Toast.makeText(NewAnimal_Activity.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void cargar(View view) {
        choosePhotoHelper.showChooser();
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
