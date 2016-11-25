package tradersTransaction;

/**
 * Created by tdrs on 25/11/16.
 */
public class Transaction {
    private final Trader trader;
    private final int year;
    private final int value;

    public Transaction(Trader trader, int year, int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getYear() {
        return year;
    }

    public Trader getTrader() {
        return trader;
    }

    public String toString(){
        return "{"+trader+",year:"+year+",value:"+value+"}";
    }

    @Override
    public boolean equals(Object o) {
        return (year == ((Transaction)o).year) &&
                (value == ((Transaction)o).value) &&
                (trader.equals(((Transaction)o).getTrader()));
    }
}
