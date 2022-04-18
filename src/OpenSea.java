import java.util.Random;

public class OpenSea implements Runnable {

    private Tunnel tunnel;

    public OpenSea(Tunnel tunnel) {
        this.tunnel = tunnel;
    }

    private Ship makeShip() {
        Random random = new Random();
        int capacity = switch (random.nextInt(3)) {
            case 0 -> 20;
            case 1 -> 50;
            case 2 -> 70;
            default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(3));
        };

        String type = switch (random.nextInt(4)) {
            case 0 -> "Bananas";
            case 1 -> "Coal";
            case 2 -> "Oil";
            case 3 -> "Coffee";
            default -> throw new IllegalStateException("Unexpected value: " + random.nextInt(3));
        };
        return new Ship(capacity, type);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Ship ship = makeShip();
            System.out.println("New ship: " + ship.getName() + " approaching the tunnel");
            tunnel.addShip(ship);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tunnel.setLastShipEntered(true);
    }
}
