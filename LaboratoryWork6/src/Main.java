import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner;

    private static final TimeCounter timeCounter;

    private static final CarsManager carsManager;

    private static final List<GasStation> gasStations;

    static{
        scanner = new Scanner(System.in);

        timeCounter = new TimeCounter();

        carsManager = new CarsManager();

        gasStations = new ArrayList<GasStation>();
        gasStations.add(new GasStation("#1-GasStation", timeCounter, carsManager, 10));
        gasStations.add(new GasStation("#2-GasStation", timeCounter, carsManager, 15));
        gasStations.add(new GasStation("#3-GasStation", timeCounter, carsManager, 5));
        gasStations.add(new GasStation("#4-GasStation", timeCounter, carsManager, 13));
    }

    public static void main(String[] args) {

        String line = "{Toyota, Ford, Nissan, Lamborghini, Mercedes, Rhino, Opel, " +
                "BMW, Audi, Chevrolet, Honda, Hyundai, Kia, Mazda, Mitsubishi, " +
                "Suzuki, Jeep, Dodge, Chrysler, Cadillac, Lincoln, Buick, " +
                "GMC, Tesla, Volvo, Peugeot, Citroen, Renault, Skoda, " +
                "SEAT, AlfaRomeo, Fiat, Lada, Geely, Chery, Haval, " +
                "GreatWall, BYD, ZAZ, Daewoo, Proton, SsangYong, Dacia, " +
                "Infiniti, Acura, Genesis, Bugatti, Bentley, RollsRoyce, " +
                "Ferrari, McLaren, Pagani, Koenigsegg, Maybach, Lucid, Rivian, " +
                "Polestar, Scion, Smart, Saab, Mini, Rover, RAM, " +
                "Isuzu, TATA, Mahindra, BAIC, Brilliance, Zhongxing, Nio, " +
                "XPeng, Fisker, Ariel, Pininfarina, DeLorean, Vector, " +
                "Spyker, TVR, Morgan, Noble, Wiesmann, Rezvani, Rimac, " +
                "Aptera, FaradayFuture, VinFast, Canoo, ElectraMeccanica, Bollinger}";//scanner.nextLine();
        for (String carName : line.substring(1, line.length() -  1).split(", "))
            carsManager.addLastName(carName);

        for (GasStation gasStation : gasStations) { gasStation.start(); }

        boolean end = true;

        while(end){
            end = false;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            timeCounter.increment();
            System.out.print(timeCounter);
            for (GasStation gasStation : gasStations) {
                System.out.println(" " + gasStation);
                if (gasStation.getGasWorking())
                    end = true;
            }

        }

        for (GasStation gasStation : gasStations){
            System.out.println("Serviced cars in the last 4 hours on " + gasStation.getNameGasStation() + ":");
            gasStation.printServedTheLastCars(4);
            gasStation.stopThread();
        }

        System.out.println("All serviced cars:");
        for (GasStation gasStation : gasStations) {
            gasStation.printServedTheLastCars(4);
        }

    }
}