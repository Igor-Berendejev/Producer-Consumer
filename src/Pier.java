public class Pier implements Runnable {
    private String type;
    private Tunnel tunnel;

    public Pier(String type, Tunnel tunnel) {
        this.tunnel = tunnel;
        this.type = type;
    }

    @Override
    public void run() {
        while (true) {
            Ship ship = tunnel.getShip(type);
            if (ship != null) {
                int cargoAmount = ship.getCapacity();
                System.out.println(type + " pier starts loading " + ship.getName());
                try {
                    Thread.sleep(cargoAmount * 100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(type + " pier loaded " + ship.getName());
            }
        }
    }
}
