package com.example.tufinca2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aminography.choosephotohelper.ChoosePhotoHelper;
import com.aminography.choosephotohelper.callback.ChoosePhotoCallback;
import com.bumptech.glide.Glide;
import com.example.tufinca2.Sqlite.BDAdapter;
import com.google.android.material.appbar.AppBarLayout;

import java.util.Objects;

public class Registro_Activity extends AppCompatActivity {
    private String PassTrought = "";
    private TextView txtName, txtUser, txtPass;
    private String Name, Mail, Pass;
    private ChoosePhotoHelper choosePhotoHelper;
    private ImageView imageView;
    String Image;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

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
            textView.setText("Actualizar Registro");
            Button.setText("Actualizar");

        }

        imageView = findViewById(R.id.CargarImagen);
        choosePhotoHelper = ChoosePhotoHelper.with(Registro_Activity.this)
                .asFilePath()
                .build(new ChoosePhotoCallback<String>() {
                    @Override
                    public void onChoose(String photo) {
                        Glide.with(Registro_Activity.this)
                                .load(photo)
                                .into(imageView);
                        Image = photo;
                    }
                });

        BDAdapter db = new BDAdapter(this);
        db.openDB();
        try
        {
            Cursor c = db.obtenerUsuario();
            if(c.moveToNext()){
                Name = c.getString(2);
                Mail = c.getString(3);
                Pass = c.getString(4);
            }
            db.closeDB();

        } catch (Exception e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
        }

        txtName = findViewById(R.id.Name);
        txtUser = findViewById(R.id.User);
        txtPass = findViewById(R.id.password);

        if((Name.equals("Default") && Mail.equals("Default") && Pass.equals("Default"))){

        }else{
            if(PassTrought.equals("")){
                //Intent intent = new Intent(this, Login_Activity.class);
                Intent intent = new Intent(this, RFinca_Activity.class);
                startActivity(intent);
            }else{
                txtName.setText(Name);
                txtUser.setText(Mail);
                txtPass.setText(Pass);
            }
        }
    }

    public void Guardar(View view) {
        String strUser = Objects.requireNonNull(txtName.getText()).toString().trim();
        String strMail = Objects.requireNonNull(txtUser.getText()).toString().trim();
        String strPassword = Objects.requireNonNull(txtPass.getText()).toString().trim();
        try
        {
            if (strUser.isEmpty() || strMail.isEmpty() || strPassword.isEmpty()) {
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            }
            else{
                BDAdapter db = new BDAdapter(this);
                db.openDB();
                db.actualizarUsuario(strMail,strUser,strPassword);
                db.closeDB();
                Toast.makeText(this, "Los datos han sido actualizados", Toast.LENGTH_SHORT).show();
                assert PassTrought != null;
                if(PassTrought.equals("")){
                    Intent intent = new Intent(this, RFinca_Activity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(this, Dashboard_Activity.class);
                    startActivity(intent);
                }
            }
        }catch (Exception e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
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
}
