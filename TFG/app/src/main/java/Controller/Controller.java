package Controller;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import Classes.Item;
import Classes.ItemViewList;
import Classes.User;
import Connection.Connection;
import Connection.ConnectionThread;
import tfg.lostandfound.R;

import static Auxiliar.Auxiliar.*;
import static Auxiliar.Auxiliar.createRandomItemViewListItem;
import static Auxiliar.Constants.*;

/**
 * Created by Llango on 18/10/2016.
 */

public class Controller {

    Connection connection;

    public Controller()
    {

    }


    public ItemViewList[] getListItems()
    {

        //Metodo que llamara a servidor y recibirá en JSon los datos de los items

        ItemViewList item_data[] = new ItemViewList[10];
        Item item;
        for(int iterator = 0; iterator < 10; iterator++)
        {
            item = createRandomItem(iterator);

            int intIcon;
            if(item.getStrItemBrand().equals("Lost"))
            {
                intIcon = R.drawable.icon_lost;
            }
            else
            {
                intIcon = R.drawable.icon_found;
            }

            item_data[iterator] = new ItemViewList(intIcon, createRandomItemViewListItem(iterator), item);

        }
        return item_data;

    }



    public int checkIfUserExists(String pstrTxtEmail, String pstrTxtPassword)
    {

        return OK;
    }

    public int createUser(String pstrTxtEmail, String pstrUserName, String pstrTxtPassword) throws IOException {
        User user = new User(pstrTxtEmail, pstrUserName, pstrTxtPassword);
        //user.save();
        return OK;
    }

    public int saveItem(Item pItem)
    {

        Log.d("ItemStatus ", "---------" + pItem.getStrStatus());
        return OK;
    }


    /**
     *  Method that creates and return an item
     *
     * @param pstrItemType      String Item type
     * @param pstrItemColor     String Item color
     * @param pstrItemBrand     String Item brand
     * @param pstrItemMaterial  String Item material
     * @param pintWhen          Integer When the item was lost
     * @return (Item) item      The item created
     */
    public Item createItem(String pstrItemType, String pstrItemColor, String pstrItemBrand, String pstrItemMaterial, int pintWhen, String pstrStatus)
    {
        Item item = new Item(pstrItemType, pstrItemColor, pstrItemBrand, pstrItemMaterial, pintWhen, pstrStatus);
        return item;
    }


}
