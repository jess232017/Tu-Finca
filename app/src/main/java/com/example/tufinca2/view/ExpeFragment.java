package com.example.tufinca2.view;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tufinca2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpeFragment extends Fragment {


    public ExpeFragment() {
        // Required empty public constructor
    }


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_expe2, container, false);
        TextView textView = view.findViewById(R.id.text);
        textView.setText("En proceso");
        return view;
    }

}
