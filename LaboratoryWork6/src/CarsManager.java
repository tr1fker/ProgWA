import java.util.ArrayList;
import java.util.List;

public class CarsManager {
    private List<String> nameCars;

    {
        nameCars = new ArrayList<>();
    }

    public synchronized String getFirstName() {
        String carName = this.nameCars.isEmpty() ? "" : this.nameCars.getFirst();
        if (!this.nameCars.isEmpty())
            this.nameCars.removeFirst();
        return carName;
    }

    public void addLastName(String lastName) { this.nameCars.add(lastName); }

    public int getLength(){ return this.nameCars.size(); }

}
