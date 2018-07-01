package com.example.albertli.smartbutler.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.albertli.smartbutler.R;
import com.example.albertli.smartbutler.adapter.ChatListAdapter;
import com.example.albertli.smartbutler.entity.ChatListData;
import com.example.albertli.smartbutler.entity.CourierData;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: SmartButler
 * Details: TODO
 * Created by albert.li on 2018/5/20.
 */

public class ButlerFragment extends Fragment implements View.OnClickListener {

    private ListView mChatListView;
    private Button btn_left, btn_right;
    private ChatListAdapter adapter;

    private List<ChatListData> mList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ){
        View view = inflater.inflate(R.layout.fragment_butler,null);
        findView(view);
        return view;
    }

    private void findView(View v)
    {
        mChatListView = (ListView) v.findViewById(R.id.mChatListView);
        btn_left = (Button) v.findViewById(R.id.btn_left);
        btn_left.setOnClickListener(this);
        btn_right = (Button) v.findViewById(R.id.btn_right);
        btn_right.setOnClickListener(this);

        adapter = new ChatListAdapter(getActivity(), mList);
        mChatListView.setAdapter(adapter);

        addLeftItem("Hello, I am yan");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_left:
                addLeftItem("左边");
                break;
            case R.id.btn_right:
                addRightItem("右边");
                break;
        }
    }

    public void addLeftItem(String text)
    {
        ChatListData data = new ChatListData();
        data.setType(ChatListAdapter.VALUE_LEFT_TYPE);
        data.setText(text);
        mList.add(data);
        //通知adapter刷新
        adapter.notifyDataSetChanged();
        //滚动到底部
        mChatListView.setSelection(mChatListView.getBottom());
    }

    public void addRightItem(String text)
    {
        ChatListData data = new ChatListData();
        data.setType(ChatListAdapter.VALUE_RIGHT_TYPE);
        data.setText(text);
        mList.add(data);
        //通知adapter刷新
        adapter.notifyDataSetChanged();
        //滚动到底部
        mChatListView.setSelection(mChatListView.getBottom());
    }
}