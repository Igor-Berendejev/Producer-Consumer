public class Ship {
    public static int shipCounter = 0;
    private int capacity;
    private String type;
    private String name;

    public Ship(int capacity, String type) {
        this.capacity = capacity;
        this.type = type;
        shipCounter++;
        this.name = type + capacity + "/" + shipCounter;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
}
