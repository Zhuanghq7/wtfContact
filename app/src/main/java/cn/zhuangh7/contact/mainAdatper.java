package cn.zhuangh7.contact;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Using to provid a two kind of layout itemlist
 * <p>
 * Created by Zhuangh7 on 2017/5/19.HUAWEI_SB
 */

public class mainAdatper extends RecyclerView.Adapter<mainAdatper.MyViewHolder> {

    private List<contacter> data;
    private Context context;

    public  mainAdatper(List l, Context context) {
        this.data = l;
        this.context = context;
    }

    public void updatedata(List<contacter> list){
        this.data =   list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //TODO bind specific data
        holder.xing.setText(""+data.get(position).getName().charAt(0));
        holder.ming.setText(data.get(position).getName().substring(1));
        holder.addr.setText(data.get(position).getAddr());
        holder.tel.setText(data.get(position).getTel_m());
        final int temp = position;
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("Contacter",data.get(temp));
                intent.putExtra("position",temp);
                intent.setClass(context,DetailActivity.class);
                ((Activity)context).startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView xing;
        private TextView ming;
        private TextView addr;
        private TextView tel;
        private View v;

        private MyViewHolder(View view) {
            super(view);
            v = view;
            xing = (TextView) view.findViewById(R.id.textView3);
            ming = (TextView) view.findViewById(R.id.textView4);
            addr = (TextView) view.findViewById(R.id.textView5);
            tel = (TextView) view.findViewById(R.id.textView);
        }
    }
}
