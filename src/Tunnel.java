import java.util.ArrayList;

public class Tunnel {
    private final ArrayList<Ship> shipsInside;

    public Tunnel() {
        shipsInside = new ArrayList<>();
    }

    public synchronized void addShip(Ship ship) {

        if (shipsInside.size() < 5) {
            notifyAll();
            shipsInside.add(ship);
            System.out.println("Ship " + ship.getName() + " entered the tunnel");

        } else {
            try {
                System.out.println("The tunnel is full, waiting for pier availability...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized Ship getShip(String type) {
        Ship ship;
        if (shipsInside.size() > 0) {
            for (Ship s : shipsInside) {
                if (s.getType().equals(type)) {
                    ship = s;
                    shipsInside.remove(s);
                    System.out.println("Ship " + ship.getName() + " left the tunnel");
                    notifyAll();
                    return ship;
                }
            }
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
