package Classes;

import android.graphics.drawable.Icon;
import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Llango on 21/10/2016.
 */

public class ItemViewList{
    public int icon;
    public String title;
    Item item;

    public ItemViewList(){
        super();
    }

    public ItemViewList(int icon, String title, Item pItem) {
        super();
        this.icon = icon;
        this.title = title;
        item = pItem;

    }
}
