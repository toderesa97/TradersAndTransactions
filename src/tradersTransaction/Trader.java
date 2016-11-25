package tradersTransaction;

/**
 * Created by tdrs on 25/11/16.
 */
public class Trader {
    private final String name;
    private final String city;

    public Trader(String n, String c){
        this.name = n;
        this.city = c;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Trader:"+name+" in "+city;
    }

    @Override
    public boolean equals(Object o) {
        return (city.equals(((Trader)o).city))&&(name.equals(((Trader)o).name));
    }
}
