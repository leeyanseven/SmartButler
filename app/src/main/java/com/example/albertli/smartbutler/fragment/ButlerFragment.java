package com.example.albertli.smartbutler.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.albertli.smartbutler.R;

/**
 * Project Name: SmartButler
 * Details: TODO
 * Created by albert.li on 2018/5/20.
 */

public class ButlerFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        View view = inflater.inflate(R.layout.fragment_butler,null);
        return view;
    }
}