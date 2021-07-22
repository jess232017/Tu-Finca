package com.example.tufinca2.view;


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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tufinca2.Modelos.Vaca;
import com.example.tufinca2.R;
import com.example.tufinca2.Sqlite.BDAdapter;
import com.example.tufinca2.adapter.Vacas_Adapter;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnimalFragment extends Fragment implements Vacas_Adapter.OnNumberListener{
    ArrayList<Vaca> vacas = new ArrayList<>();
    RecyclerView VacaRecycler;
    ImageView imageView;

    public AnimalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_animal, container, false);
        showToolbar(getResources().getString(R.string.vacas), view);

        imageView = view.findViewById(R.id.captus);
        VacaRecycler = view.findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        VacaRecycler.setLayoutManager(linearLayoutManager);
        Refrescar();
        return view;
    }

    private void Refrescar(){
        Vacas_Adapter rifa_adapter = new Vacas_Adapter(CargarVacas(),getActivity(), R.layout.item_vacas, this);
        if (rifa_adapter.getItemCount() == 0) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
            VacaRecycler.setAdapter(rifa_adapter);
        }
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
