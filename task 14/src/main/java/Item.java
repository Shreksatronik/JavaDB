public class Item {
    private ItemType type;
    private int timeToCreate;

    Item(ItemType type, int timeToCreate){
        this.type = type;
        this.timeToCreate = timeToCreate;
    }

    public int getTimeToCreate() {
        return timeToCreate;
    }

    public ItemType getType() {
        return type;
    }

    public void setTimeToCreate(int timeToCreate) {
        this.timeToCreate = timeToCreate;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
