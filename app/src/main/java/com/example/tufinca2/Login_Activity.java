package com.example.tufinca2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tufinca2.Sqlite.BDAdapter;

import java.util.Objects;

public class Login_Activity extends AppCompatActivity {
    private String Mail, Pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        BDAdapter db = new BDAdapter(this);
        db.openDB();
        try
        {
            Cursor c = db.obtenerUsuario();
            if(c.moveToNext()){
                Mail = c.getString(3);
                Pass = c.getString(4);
            }
            db.closeDB();

        } catch (Exception e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
        }
    }

    public void GOgo(View view) {
        TextView txtUser = findViewById(R.id.correo);
        TextView txtPass = findViewById(R.id.password);
        String strUsuario = Objects.requireNonNull(txtUser.getText()).toString().trim();
        String strPassword = Objects.requireNonNull(txtPass.getText()).toString().trim();
        try
        {
            if (strUsuario.isEmpty() || strPassword.isEmpty()) {
                Toast.makeText(this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
            }
            else if(strUsuario.equals(Mail) && strPassword.equals(Pass)){
                Intent intent = new Intent(this, Dashboard_Activity.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Credenciales Incorrectas", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);  //exit application
    }
}
