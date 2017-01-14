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


/**
 * Auxiliar class that contains the data to show in the ChatsActivity.
 * By default, you can show information with ease by only using a ListView and filling it with data.
 * But using this way, it's defined a layout, and this layout is shown in every row of the ListView.
 * That's why its needed this adaptor.
 *
 */
public class ChatAdapter extends ArrayAdapter<ChatViewList>
    {

        Context context;
        int layoutResourceId;
        ChatViewList data[]=null;


        /**
         * Constructor with parameters
         *
         * @param context
         * @param layoutResourceId
         * @param data
         */
    public ChatAdapter(Context context,int layoutResourceId,ChatViewList[]data)
    {
        super(context,layoutResourceId,(ChatViewList[])data);
        this.layoutResourceId=layoutResourceId;
        this.context=context;
        this.data=data;
    }
    /**
     * Method that returns the data from an element of the listview
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
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