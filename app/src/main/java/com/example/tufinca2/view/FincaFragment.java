package com.example.tufinca2.view;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tufinca2.Detail_Activity;
import com.example.tufinca2.Login_Activity;
import com.example.tufinca2.Modelos.Finca;
import com.example.tufinca2.Modelos.Vaca;
import com.example.tufinca2.R;
import com.example.tufinca2.Registro_Activity;
import com.example.tufinca2.Sqlite.BDAdapter;
import com.example.tufinca2.adapter.Vacas_Adapter;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class FincaFragment extends Fragment implements Vacas_Adapter.OnNumberListener {
    private Finca finca;
    private ArrayList<Vaca> vacas = new ArrayList<>();
    private RecyclerView VacaRecycler;
    ImageView imageView;
    //Cambiar por las imagenes correspondiente
    private int[] sampleImages = {R.drawable.ic_android, R.drawable.ic_cow, R.drawable.ic_home, R.drawable.ic_mail_outline, R.drawable.ic_cow_with_plus_white};

    public FincaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_finca, container, false);
        showToolbar(getResources().getString(R.string.nombre_finca), view);

        CarouselView carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        setHasOptionsMenu(true);
        carouselView.setImageListener(imageListener);

        VacaRecycler = view.findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        VacaRecycler.setLayoutManager(linearLayoutManager);
        Refrescar();

        BDAdapter db = new BDAdapter(getContext());
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

        TextView txtNombre = view.findViewById(R.id.txtNombre);
        txtNombre.setText(finca.getNombre());
        TextView txtAbreviatura = view.findViewById(R.id.txtAbreviatura);
        txtAbreviatura.setText(finca.getAbreviatura());
        TextView txtProposito = view.findViewById(R.id.txtProposito);
        txtProposito.setText(finca.getProposito());
        TextView txtDimension = view.findViewById(R.id.txtDimension);
        txtDimension.setText(finca.getArea1());
        TextView txtArea2 = view.findViewById(R.id.txtArea2);
        txtArea2.setText(finca.getArea2());

        return view;
    }

    private void Refrescar(){
        Vacas_Adapter rifa_adapter = new Vacas_Adapter(CargarVacas(),getActivity(), R.layout.item_vacas, this);
        VacaRecycler.setAdapter(rifa_adapter);
    }

    private ArrayList<Vaca> CargarVacas() {
        vacas.clear();
        BDAdapter db = new BDAdapter(getActivity());
        db.openDB();
        try
        {
            Vaca p;

            DatabaseUtils.dumpCursor(db.obtenerVaca());
            Cursor c = db.obtenerVaca();
            while (c.moveToNext()) {
                p= new Vaca(c.getString(1),c.getString(2),c.getString(3),
                        c.getString(4),c.getString(5),c.getString(6),c.getString(7),
                        c.getString(8),c.getString(9));
                vacas.add(p);
            }
            db.closeDB();
            return vacas;

        } catch (Exception e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
            return vacas;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.AddMenu) {
            Intent intent = new Intent(getContext(), Registro_Activity.class);
            intent.putExtra("PassTrought", "YES");
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.Logout) {
            Intent intent = new Intent(getContext(), Login_Activity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };

    private void showToolbar(String tittle, View view){
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) Objects.requireNonNull(getActivity())).setSupportActionBar(toolbar);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle(tittle);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public void onNumberClick(String pos) {

    }
}
