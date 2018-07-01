package com.example.albertli.smartbutler.adapter;

import android.content.Context;
import android.test.suitebuilder.TestMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.albertli.smartbutler.R;
import com.example.albertli.smartbutler.entity.ChatListData;

import java.util.List;

/**
 * Project Name: SmartButler
 * Details: 对话
 * Created by albert.li on 2018/6/4.
 */

public class ChatListAdapter extends BaseAdapter {

    //左边
    public static final int VALUE_LEFT_TYPE = 1;
    //右边
    public static final int VALUE_RIGHT_TYPE = 2;

    //上下文
    private Context mContext;
    //实体
    private List<ChatListData> mList;
    //布局加载器
    private LayoutInflater inflater;
    //private ChatListData data;

    public ChatListAdapter(Context mContext, List<ChatListData> mList )
    {
        this.mContext = mContext;
        this.mList = mList;
        //获取系统服务
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderLeft viewHolderLeft = null;
        ViewHolderRight viewHolderRight = null;

        int type = getItemViewType(position);
        if(convertView == null)
        {
            switch (type){
                case VALUE_LEFT_TYPE:
                    viewHolderLeft = new ViewHolderLeft();
                    //使用inflater对象来将布局文件解析成一个View
                    convertView = inflater.inflate(R.layout.layout_left_item, null);
                    viewHolderLeft.tv_left_text = (TextView) convertView.findViewById(R.id.tv_left_text);
                    convertView.setTag(viewHolderLeft);
                    break;
                case VALUE_RIGHT_TYPE:
                    viewHolderRight = new ViewHolderRight();
                    convertView = inflater.inflate(R.layout.layout_right_item, null);
                    viewHolderRight.tv_right_text = (TextView) convertView.findViewById(R.id.tv_right_text);
                    convertView.setTag(viewHolderRight);
                    break;
            }
        }
        else
        {
            switch (type){
                case VALUE_LEFT_TYPE:
                    viewHolderLeft = (ViewHolderLeft) convertView.getTag();
                    break;
                case VALUE_RIGHT_TYPE:
                    viewHolderRight = (ViewHolderRight) convertView.getTag();
                    break;
            }

        }

        ChatListData data = mList.get(position);
        switch (type){
            case VALUE_LEFT_TYPE:
                viewHolderLeft.tv_left_text.setText(data.getText());
                break;
            case VALUE_RIGHT_TYPE:
                viewHolderRight.tv_right_text.setText(data.getText());
                break;
        }

        return convertView;
    }

    //根据数据源position 返回item的type
    @Override
    public int getItemViewType(int position)
    {
        ChatListData data = mList.get(position);
        return data.getType();
    }
    @Override
    public int getViewTypeCount()
    {
        return 3;
    }

    //左边的文本
    class ViewHolderLeft {
        private TextView tv_left_text;
    }

    //右边的文本
    class ViewHolderRight {
        private TextView tv_right_text;
    }
}
