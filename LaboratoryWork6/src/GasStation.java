import java.util.ArrayList;
import java.util.List;

public class GasStation extends Thread {
    private String name;

    private TimeCounter timeCounter;

    private CarsManager carsManager;

    private StatusGasStation statusGasStation;

    private int quota;

    private int residueCar;

    private int durationBreak;

    private int durationBeforeBreak;

    private int residueBreak;

    private int residueToBreak;

    private boolean gasWorking;


    private boolean threadWorking;

    private String currentNameCar;

    private int countdown;


    private List<String> servedCars;
    private List<Integer> countdownServedCars;

    {
        this.statusGasStation = StatusGasStation.FREE;
        this.quota = 6;
        this.residueCar = 0;
        this.durationBeforeBreak = 45;
        this.gasWorking = true;
        this.threadWorking = true;
        this.currentNameCar = "";
        this.countdown = 0;
        this.servedCars = new ArrayList<>();
        this.countdownServedCars = new ArrayList<>();
    }


    public GasStation(String name, TimeCounter timeCounter, CarsManager carsManager, int durationBreak) {
        this.name = name;

        this.timeCounter = timeCounter;

        this.carsManager = carsManager;

        this.durationBreak = durationBreak;
        this.residueToBreak = durationBeforeBreak;
    }


    public void run() {
        while(this.threadWorking){
            if (this.countdown != this.timeCounter.getCounter()){
                ++this.countdown;

                if (this.statusGasStation == StatusGasStation.BREAK
                        | this.statusGasStation == StatusGasStation.BREAKANDBUSY){
                    --this.residueBreak;
                }else if (this.statusGasStation == StatusGasStation.BUSY){
                    --this.residueCar;
                    --this.residueToBreak;
                }else if (this.statusGasStation == StatusGasStation.FREE){
                    --this.residueToBreak;
                }

                if (this.residueToBreak == 0){
                    this.residueBreak = this.durationBreak;
                    this.residueToBreak = this.durationBeforeBreak;
                    this.statusGasStation = residueCar == 0 ? StatusGasStation.BREAK : StatusGasStation.BREAKANDBUSY;
                }

                if (this.residueCar == 0 & this.residueBreak == 0){
                    this.statusGasStation = StatusGasStation.FREE;
                }else if (this.residueBreak == 0){
                    this.statusGasStation = StatusGasStation.BUSY;
                }

                if (this.statusGasStation == StatusGasStation.FREE){
                    this.currentNameCar = this.carsManager.getFirstName();
                    if (!this.currentNameCar.isEmpty()){
                        this.servedCars.add(this.currentNameCar);
                        this.countdownServedCars.add(this.countdown);
                        this.statusGasStation = StatusGasStation.BUSY;
                        this.quota = 6;
                        this.residueCar = this.quota;
                        this.gasWorking = true;
                    }else
                        this.gasWorking = false;
                }

            }
        }
    }

    public boolean getGasWorking() { return this.gasWorking; }

    public String getNameGasStation() { return this.name; }

    public void stopThread(){
        this.threadWorking = false;
    }

    public void printServedTheLastCars(int hours){
        for (int _car = 0; _car < this.servedCars.size(); _car++) {
            if (this.countdown - this.countdownServedCars.get(_car) <= hours * 60)
                System.out.print(" " + this.servedCars.get(_car));
        }
        System.out.println();
    }

    @Override
    public String toString() {
        String result = this.name + " Status:";
        if (this.statusGasStation == StatusGasStation.FREE) {
            result += "FREE" + " ResidueToBreak:" + this.residueToBreak;
        }else if (this.statusGasStation == StatusGasStation.BUSY) {
            result += "BUSY" + " Car:" + this.currentNameCar + " ResidueCar:" + this.residueCar +
                " ResidueToBreak:" + this.residueToBreak;
        }else if (this.statusGasStation == StatusGasStation.BREAK) {
            result += "BREAK ResidueBreak:" + this.residueBreak;
        }else{
            result += "BREAK&BUSY ResidueBreak:" + this.residueBreak + " Car:" + this.currentNameCar
                + " ResidueCar:" + this.residueCar;
        }
        return result;
    }

}
