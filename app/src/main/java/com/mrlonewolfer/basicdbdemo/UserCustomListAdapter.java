package com.mrlonewolfer.basicdbdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Callback;

public class UserCustomListAdapter extends RecyclerView.Adapter<UserCustomListAdapter.userViewHolder> {

    private  OnUserClickListner listner;
    private List<UserBeanRoot.User> userBeanArrayList;


    public UserCustomListAdapter(OnUserClickListner listner, List<UserBeanRoot.User> userBeanArrayList) {
        this.listner = listner;
        this.userBeanArrayList = userBeanArrayList;
    }



    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_row_item,viewGroup,false);
        userViewHolder viewHolder = new userViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        final UserBeanRoot.User users=userBeanArrayList.get(position);
        holder.txtId.setText(users.getId()+"");
        holder.txtFName.setText(users.getFname());
        holder.txtLName.setText(users.getLname());
        holder.txtMobile.setText(users.getMobile()+"");

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onUserClick(users,"Delete");
                userBeanArrayList.remove(users);
            }
        });
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.onUserClick(users,"Edit");
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return userBeanArrayList.size();
    }

    public interface OnUserClickListner {
        void onUserClick(UserBeanRoot.User users,String myAction);
    }

public class userViewHolder extends RecyclerView.ViewHolder{
    TextView txtId,txtFName,txtLName,txtMobile;
    ImageView imgDelete,imgEdit;
    public userViewHolder(@NonNull View itemView) {
        super(itemView);

        txtId=itemView.findViewById(R.id.txtId);
        txtFName=itemView.findViewById(R.id.txtFName);
        txtLName=itemView.findViewById(R.id.txtLName);
        txtMobile=itemView.findViewById(R.id.txtMobile);
        imgEdit=itemView.findViewById(R.id.imgEdit);
        imgDelete=itemView.findViewById(R.id.imgDelete);


    }
}

}
