package Controller;

import android.util.Log;

import Classes.Item;
import Connection.ConnectionThread;

import static Auxiliar.Constants.*;

/**
 * Created by Llango on 18/10/2016.
 */

public class Controller {

    public Controller()
    {

    }


    public int checkIfUserExists(String pstrTxtEmail, String pstrTxtPassword)
    {

        return OK;
    }

    public int createUser(String pstrTxtEmail, String pstrUserName, String pstrTxtPassword)
    {


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
