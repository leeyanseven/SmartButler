package com.example.albertli.smartbutler.fragment;

/**
 * Project Name: SmartButler
 * Details: 个人中心
 * Created by albert.li on 2018/5/20.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.print.PrintHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.albertli.smartbutler.R;
import com.example.albertli.smartbutler.entity.MyUser;
import com.example.albertli.smartbutler.ui.LoginActivity;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class UserFragment extends Fragment implements View.OnClickListener{

    private Button btn_exit_user;
    private TextView edit_user;
    private EditText et_username;
    private EditText et_sex;
    private EditText et_age;
    private EditText et_desc;
    private Button btn_update_ok;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        findView(view);
        return view;
    }

    //初始化view
    public void findView(View view)
    {
        btn_exit_user = (Button) view.findViewById(R.id.btn_exit_user);
        btn_exit_user.setOnClickListener(this);
        edit_user = (TextView) view.findViewById(R.id.edit_user);
        edit_user.setOnClickListener(this);

        et_username = (EditText) view.findViewById(R.id.et_username);
        et_sex = (EditText) view.findViewById(R.id.et_sex);
        et_age = (EditText) view.findViewById(R.id.et_user_age);
        et_desc = (EditText) view.findViewById(R.id.et_user_desc);
        btn_update_ok = (Button) view.findViewById(R.id.btn_update_ok);
        btn_update_ok.setOnClickListener(this);

        //默认不可编辑
        setEnabled(false);

        //设置具体值
        MyUser userInfo = BmobUser.getCurrentUser(MyUser.class);
        et_username.setText(userInfo.getUsername());
        et_sex.setText(userInfo.getSex()?"man":"woman");
        et_age.setText(String.valueOf(userInfo.getAge()));
        et_desc.setText(userInfo.getDesc());

    }

    private void setEnabled(Boolean enabled)
    {
        if(enabled)
        {
            et_username.setEnabled(true);
            et_sex.setEnabled(true);
            et_age.setEnabled(true);
            et_desc.setEnabled(true);
        }
        else
        {
            et_username.setEnabled(false);
            et_sex.setEnabled(false);
            et_age.setEnabled(false);
            et_desc.setEnabled(false);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //logout
            case R.id.btn_exit_user:
                MyUser.logOut();   //清除缓存用户对象
                BmobUser currentUser = MyUser.getCurrentUser(); // 现在的currentUser是null了
                Toast.makeText(getActivity(),"logout success",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
                break;
            //edit profile
            case R.id.edit_user:
                setEnabled(true);
                btn_update_ok.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_update_ok:
                //1.获取资料
                String name = et_username.getText().toString().trim();
                String age = et_age.getText().toString().trim();
                String sex = et_sex.getText().toString().trim();
                String desc = et_desc.getText().toString().trim();

                //2.判断是否空
                if(!(TextUtils.isEmpty(name)) & !(TextUtils.isEmpty(age)) & !(TextUtils.isEmpty(sex)))
                {
                    //3.更新用户资料
                    MyUser newUser = new MyUser();
                    newUser.setUsername(name);
                    newUser.setAge(Integer.parseInt(age));
                    if(sex.equals("man"))
                    {
                        newUser.setSex(true);
                    }
                    else
                    {
                        newUser.setSex(false);
                    }
                    if(TextUtils.isEmpty(desc))
                    {
                        newUser.setDesc("nothing left");
                    }
                    else
                    {
                        newUser.setDesc(desc);
                    }

                    BmobUser bmobUser = BmobUser.getCurrentUser();
                    newUser.update(bmobUser.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if(e==null){
                                setEnabled(false);
                                btn_update_ok.setVisibility(View.GONE);
                                Toast.makeText(getActivity(),"update profile success",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(),"update profile fail",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else
                {
                    Toast.makeText(getActivity(),"input null",Toast.LENGTH_SHORT).show();
                }
                break;
            
        }
    }
}
