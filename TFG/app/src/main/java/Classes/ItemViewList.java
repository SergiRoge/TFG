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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
