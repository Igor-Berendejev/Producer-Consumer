# Producer-Consumer

An example of producer-consumer pattern.

OpenSea generates ships with different cargo types and capacities.

Piers take the ships of matching cargo type and unload them.

Before reaching the pier a ship must sail through the Tunnel, but only 5 ships may be in the tunnel at a time.

The Tunnel accepts ships from the OpenSea. If the Tunnel is full (5 ships are inside the Tunnel) OpenSea waits till there is free space in the Tunnel.

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

Piers try to get a Ship from the Tunnel. The Pier waits if the Tunnel is empty of there is no ship of matching cargo type.

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
    
