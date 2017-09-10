package com.example.nitishprasad.signin;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
    class classAdapter extends RecyclerView.Adapter<classAdapter.ViewHolder>{


    Context context;
    String []className;
    String []groupName;
    LayoutInflater inflater;

    classAdapter(Context context,String []className,String []groupName){

        this.context = context;
        this.className = className;
        this.groupName = groupName;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {

        CardView cv = (CardView)LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
        return new ViewHolder(cv);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

                CardView cv = holder.cardView;
        TextView classname = cv.findViewById(R.id.classId);
        TextView groupname = cv.findViewById(R.id.group_id);


        classname.setText(className[position]);
        groupname.setText(groupName[position]);
    }

    @Override
    public int getItemCount() {
        return className.length;
    }

    public  static  class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;

        public ViewHolder(CardView itemView) {
            super(itemView);
            cardView = itemView;
        }

    }


}

class Teacher{

    String id;
    String name;
    String

}
