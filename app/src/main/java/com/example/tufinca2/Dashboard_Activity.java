package com.example.tufinca2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tufinca2.view.AnimalFragment;
import com.example.tufinca2.view.FincaFragment;
import com.example.tufinca2.view.ProductFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Dashboard_Activity extends AppCompatActivity {
    private boolean viewIsAtHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottombar);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                switch (itemId){
                    case R.id.finca:
                        addFragment(new FincaFragment());
                        viewIsAtHome = true;
                        break;
                    case R.id.animal:
                        addFragment(new AnimalFragment());
                        viewIsAtHome = false;
                        break;
                    case R.id.statics:
                        addFragment(new ProductFragment());
                        viewIsAtHome = false;
                        break;
                    case R.id.Expediente:
                        Toast.makeText(Dashboard_Activity.this, "En proceso", Toast.LENGTH_SHORT).show();
                        //addFragment(new ExpeFragment());
                        viewIsAtHome = false;
                        break;
                }
                return true;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.finca);
    }

    public void DetallesFinca(View view) {
        Intent intent = new Intent(this, Detail_Activity.class);
        startActivity(intent);
    }

    public void NewFinca(View view) {
        Intent intent = new Intent(this, RFinca_Activity.class);
        intent.putExtra("PassTrought", "YES");
        startActivity(intent);
    }

    private void addFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (!viewIsAtHome) { //if the current view is not the News fragment
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottombar);
            bottomNavigationView.setSelectedItemId(R.id.home); //display the News fragment
        } else {
            //moveTaskToBack(true);  //If view is in News fragment, exit application
            final Intent i = new Intent(this, Login_Activity.class);
            startActivity(i);
        }
    }

    public void onClick(View view) {
        Intent intent = new Intent(this, NewAnimal_Activity.class);
        startActivity(intent);
    }

    public void onClick2(View view) {
        Intent intent = new Intent(this, Cargar_Activity.class);
        startActivity(intent);
    }
}
