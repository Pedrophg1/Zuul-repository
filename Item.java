public class Item {
    private  String itemDescription;
    private String cor;
    private String tamanho;
    public Item(String itemDescription) {
        this.itemDescription = itemDescription;
        this.cor=cor;
        this.tamanho=tamanho;


    }
    public void mudarCor(){

    }
}
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
