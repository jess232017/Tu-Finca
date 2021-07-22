package com.example.tufinca2.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tufinca2.Modelos.Vaca;
import com.example.tufinca2.R;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;


public class Vacas_Adapter extends RecyclerView.Adapter<Vacas_Adapter.ContactoViewHolder> implements Filterable {

    //Declaracion de Variables
    private Activity activity;
    private OnNumberListener mOnNumberListener;
    private ArrayList<Vaca> filterList;
    ArrayList<Vaca> vacas;
   // private CustomFilter filter;
    private int resource;
    //---------------------------

    public Vacas_Adapter(ArrayList<Vaca> numeros, Activity activity, int resource, OnNumberListener mOnNumberListener){
        this.vacas = numeros;
        this.filterList = numeros;
        this.resource    = resource;
        this.mOnNumberListener = mOnNumberListener;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new ContactoViewHolder(view,mOnNumberListener);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Vaca vaca = vacas.get(position);
        holder.nombre.setText(vaca.getNombre());
        holder.propose.setText(vaca.getProduce());
        holder.Peso.setText(vaca.getPeso());
        holder.genero.setText(vaca.getGenero());
        holder.produce.setText(vaca.getDesarrollo());
    }

    @Override
    public int getItemCount() {
        return vacas.size();
    }

    @Override
    public Filter getFilter() {
        //if(filter==null)
            //filter=new CustomFilter(filterList,this);

        //return filter;
        return null;
    }

    @SuppressLint("DefaultLocale")
    class ContactoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private OnNumberListener mOnNumberListener;
        private TextView nombre;
        private TextView propose;
        private TextView Peso;
        private TextView genero;
        private TextView produce;


        ContactoViewHolder(View itemView, OnNumberListener onNumberListener) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);
            propose = itemView.findViewById(R.id.propose);
            Peso = itemView.findViewById(R.id.Peso);
            genero = itemView.findViewById(R.id.genero);
            produce = itemView.findViewById(R.id.produce);
            this.mOnNumberListener = onNumberListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            String pos = vacas.get(getAdapterPosition()).getId();
            mOnNumberListener.onNumberClick(pos);
        }
    }

    public interface OnNumberListener {
        void onNumberClick(String pos);
    }
}
