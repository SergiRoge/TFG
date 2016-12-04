package Auxiliar;

/**
 * Created by Llango on 03/12/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import Classes.ChatViewList;
import tfg.lostandfound.R;

public class ChatAdapter extends ArrayAdapter<ChatViewList>
    {

        Context context;
        int layoutResourceId;
        ChatViewList data[]=null;

    public ChatAdapter(Context context,int layoutResourceId,ChatViewList[]data)
    {
        super(context,layoutResourceId,(ChatViewList[])data);
        this.layoutResourceId=layoutResourceId;
        this.context=context;
        this.data=data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View row=convertView;
        ItemHolder holder=null;

        if(row==null)
        {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        row=inflater.inflate(layoutResourceId,parent,false);

        holder=new ItemHolder();
        holder.imgIcon=(ImageView)row.findViewById(R.id.imgIcon);
        holder.txtTitle=(TextView)row.findViewById(R.id.txtTitle);

        row.setTag(holder);
        }
        else
        {
        holder=(ItemHolder)row.getTag();
        }

        ChatViewList itemViewList=data[position];

        holder.txtTitle.setText(itemViewList.title);

        return row;
        }

    static class ItemHolder {
        ImageView imgIcon;
        TextView txtTitle;
    }
}