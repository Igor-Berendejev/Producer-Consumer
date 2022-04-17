import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Tunnel tunnel = new Tunnel();

        ExecutorService service = Executors.newFixedThreadPool(5);
        service.execute(new OpenSea(tunnel));
        service.execute(new Pier("Bananas", tunnel));
        service.execute(new Pier("Coal", tunnel));
        service.execute(new Pier("Oil", tunnel));
        service.execute(new Pier("Coffee", tunnel));

        service.shutdown();
    }
}
