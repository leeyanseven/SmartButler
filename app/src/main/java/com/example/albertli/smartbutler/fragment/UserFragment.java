package com.example.albertli.smartbutler.fragment;

/**
 * Project Name: SmartButler
 * Details: 个人中心
 * Created by albert.li on 2018/5/20.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.albertli.smartbutler.R;

public class UserFragment extends Fragment {
    public View OnCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        return view;
    }
}
