package com.example.albertli.smartbutler.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.albertli.smartbutler.R;
import com.example.albertli.smartbutler.adapter.CourierAdapter;
import com.example.albertli.smartbutler.entity.CourierData;
import com.example.albertli.smartbutler.utils.L;
import com.example.albertli.smartbutler.utils.StaticClass;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Project Name: SmartButler
 * Details: TODO
 * Created by albert.li on 2018/6/3.
 */

public class CourierActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_name;
    private EditText et_number;
    private Button btn_get_courier;
    private ListView mListView;

    private List<CourierData> mList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courier);

        initView();
    }

    private void initView()
    {
        et_name = (EditText) findViewById(R.id.et_name);
        et_number = (EditText) findViewById(R.id.et_number);
        btn_get_courier = (Button) findViewById(R.id.btn_get_courier);
        mListView = (ListView) findViewById(R.id.mListView);
        btn_get_courier.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_get_courier:
                /*
                * 1.获取输入框
                * 2.判断是否空
                * 3.拿到数据请求
                * 4.解析json
                * 5.listView 适配器
                * 6.实体类item
                * 7.设置数据显示
                * */
                //1.获取输入框
                String name = et_name.getText().toString().trim();
                String number = et_number.getText().toString().trim();
                //2.判断是否空
                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(number))
                {
                    String url = "http://v.juhe.cn/exp/index?key="+ StaticClass.COURIER_ID+
                            "&com=" + name + "&no=" + number;
                    L.i("url:" + url);
                    //3.拿到数据请求
                    RxVolley.get(url, new HttpCallback() {
                        @Override
                        public void onSuccess(String t) {
                            //Toast.makeText(CourierActivity.this,t,Toast.LENGTH_SHORT).show();
                            L.i("Json:" + t);

                            //4.解析json
                            parsingJson(t);
                        }
                    });

                }
                else
                {
                    Toast.makeText(CourierActivity.this,"input cannot be null",
                            Toast.LENGTH_SHORT).show();
                }

                break;

        }
    }

    private void parsingJson(String t)
    {
        try {
            JSONObject jsonObject = new JSONObject(t);
            JSONObject jsonResult = jsonObject.getJSONObject("result");
            JSONArray jsonArray = jsonResult.getJSONArray("list");
            for (int i=0;i<jsonArray.length();i++)
            {
                JSONObject json = (JSONObject) jsonArray.get(i);

                CourierData data = new CourierData();
                data.setRemark(json.getString("remark"));
                data.setDatetime(json.getString("datetime"));
                data.setZone(json.getString("zone"));
                mList.add(data);
            }
            Collections.reverse(mList);
            CourierAdapter adapter = new CourierAdapter(this, mList);
            mListView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
