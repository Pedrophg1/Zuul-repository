import java.util.ArrayList;
import java.util.List;

public class ItemCollection {
    private List<Item> items;

    public ItemCollection() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }


}
