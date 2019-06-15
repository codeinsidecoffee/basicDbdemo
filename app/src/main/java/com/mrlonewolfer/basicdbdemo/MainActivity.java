package com.mrlonewolfer.basicdbdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,UserCustomListAdapter.OnUserClickListner {

    EditText edtId,edtFname,edtLname,edtMobile;
    Button btnSubmit;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    UserCustomListAdapter userListCustomAdapter;
    ListView userList;
    Context context;
    List<UserBeanRoot.User> listusers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context=this;
        edtId=findViewById(R.id.edtID);
        edtFname=findViewById(R.id.edtFName);
        edtLname=findViewById(R.id.edtLName);
        edtMobile=findViewById(R.id.edtMobile);


        recyclerView=findViewById(R.id.recyclerList);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


//        userList=findViewById(R.id.userList);

        btnSubmit=findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(this);
        selectUserData();


    }

    private void selectUserData() {
        MyRetrofitService myRetrofitService=MyRetrofitClient.getClient();
        Call<UserBeanRoot> call=myRetrofitService.selectUSer(Const.SELECT);
        call.enqueue(new Callback<UserBeanRoot>() {
            @Override
            public void onResponse(Call<UserBeanRoot> call, Response<UserBeanRoot> response) {

                UserBeanRoot root=response.body();
                listusers=root.getUsers();
                userListCustomAdapter = new UserCustomListAdapter(MainActivity.this,listusers);
                recyclerView.setAdapter(userListCustomAdapter);

//                ArrayAdapter<UserBeanRoot.User> arrayAdapter=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,listusers);
//            userList.setAdapter(arrayAdapter);

            }

            @Override
            public void onFailure(Call<UserBeanRoot> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }


        });

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnSubmit){

            if(btnSubmit.getText().equals("Submit")){

            MyRetrofitService myRetrofitService=MyRetrofitClient.getClient();
            Call<String> call=myRetrofitService.insertUser(Const.INSERT,
                                            edtFname.getText().toString(),
                                            edtLname.getText().toString(),
                                            edtMobile.getText().toString());
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String msg=response.body();
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    selectUserData();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
             //   Toast.makeText(this, "You r Clicking on Submit Button", Toast.LENGTH_SHORT).show();


            }
            if (btnSubmit.getText().equals("Update")){
                MyRetrofitService myRetrofitService=MyRetrofitClient.getClient();
                Call<String> call=myRetrofitService.updateUser(Const.UPDATE,
                                                edtId.getText().toString(),
                                                edtFname.getText().toString(),
                                                edtLname.getText().toString(),
                                                edtMobile.getText().toString());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String msg=response.body();
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        selectUserData();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            resetMyField();

        }
    }

    private void resetMyField() {
        edtId.setText("");
        edtId.setVisibility(View.GONE);
        edtFname.setText("");
        edtLname.setText("");
        edtMobile.setText("");
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onUserClick(UserBeanRoot.User users, String myAction) {
        if(myAction.equals("Delete")){

            MyRetrofitService myRetrofitService=MyRetrofitClient.getClient();
            Call<String> call=myRetrofitService.deleteUser(Const.DELETE,
                                                            users.getId());

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String msg=response.body();
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    selectUserData();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                }
            });

            Toast.makeText(context, "Data Succesfully Deleted", Toast.LENGTH_SHORT).show();
            selectUserData();
        }
        if(myAction.equals("Edit")){
            btnSubmit.setText("Update");
            edtId.setFocusable(false);
            edtId.setVisibility(View.VISIBLE);
            edtId.setBackgroundResource(R.color.colorAccent);
            edtId.setTextColor(Color.WHITE);
            edtId.setText(users.getId()+"");
            edtMobile.setText(users.getMobile()+"");
            edtFname.setText(users.getFname());
            edtLname.setText(users.getLname());
        }
    }
}
