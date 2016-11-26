import org.junit.Test;
import tradersTransaction.Trader;
import tradersTransaction.Transaction;

import java.util.*;
import java.util.concurrent.SynchronousQueue;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by tdrs on 25/11/16.
 */
public class TransactionShould {
    private Trader mario = new Trader("Mario","Milan");
    private Trader alan = new Trader("Alan","Cambridge");
    private Trader brian = new Trader("Brian","Cambridge");
    private Trader raoul = new Trader("Raoul", "Cambridge");

    private List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );

    /*
    TODO
        OK 1. Find all transactions in the year 2011 and sort them by value (small to high).
        OK 2. What are all the unique cities where the traders work?
        OK 3. Find all traders from Cambridge and sort them by name.
        OK 4. Return a string of all traders’ names sorted alphabetically.
        OK 5. Are any traders based in Milan?
        OK 6. Print all transactions’ values from the traders living in Cambridge.
        OK 7. What’s the highest value of all the transactions?
        OK 8. Find the transaction with the smallest value.
     */

    @Test
    public void find_all_transactions_by_year_and_sort_them_by_value (){
        Predicate<Transaction> conditionOfYear = year -> year.getYear()==2011;
        Comparator<Transaction> sortByValue = (Transaction transaction, Transaction t1)->
                                                    transaction.getValue()-t1.getValue();
        List<Transaction> obtained = transactions.stream()
                                                 .filter(conditionOfYear)
                                                 .sorted(sortByValue)
                                                 .collect(Collectors.toList());
        List<Transaction> expected = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2011, 400)
        );

        assertEquals(expected, obtained);
    }


    @Test
    public void retrieve_all_the_unique_cities_where_traders_work (){
        List<String> uniqueCities = transactions.stream()
                                                .map(p->p.getTrader()
                                                .getCity())
                                                .distinct()
                                                .collect(Collectors.toList());
        List<String> expected = Arrays.asList("Cambridge","Milan");

        assertEquals(expected, uniqueCities);

    }

    @Test
    public void retrieve_all_traders_from_a_city_and_sort_em_by_name (){
        Comparator<String> sortByName = (String t1,String t2) -> t1.compareTo(t2);
        List<String> obtained = transactions.stream()
                                            .filter(city->city.getTrader().getCity().equals("Cambridge"))
                                            .map(p->p.getTrader().getName())
                                            .sorted(sortByName)
                                            .collect(Collectors.toList());
        List<String> expected = Arrays.asList("Alan","Brian","Raoul","Raoul");

        assertEquals(expected, obtained);
    }

    @Test
    public void retrieve_a_String_representation_of_all_traders_name_sorted_alphabetically (){
        String obtained = transactions.stream()
                          .map(Transaction::getTrader).map(Trader::getName)
                          .sorted(String::compareTo)
                          .collect(Collectors.joining(","));

        String expected = "Alan,Brian,Mario,Mario,Raoul,Raoul";

        assertEquals(expected, obtained);
    }

    @Test
    public void retrieve_if_any_trader_is_based_in_a_city (){
        Boolean foundATraderInMilan = transactions.stream().
                filter(transaction -> transaction.getTrader().getCity().equals("Milan")).count() > 0;
        assertTrue(foundATraderInMilan);

    }

    @Test
    public void print_transactions_traders_values_from_a_city (){
        String obtained = transactions.stream()
                            .filter(city->city.getTrader().getCity().equals("Cambridge"))
                            .map(Transaction::getValue)
                            .map(String::valueOf)
                            .collect(Collectors.joining(","));
        String expected ="300,1000,400,950";
        assertEquals(expected,obtained);
    }

    @Test
    public void retrieve_the_highest_value_of_all_transactions (){
        IntSummaryStatistics statistics = transactions.stream()
                .mapToInt(Transaction::getValue).summaryStatistics();

        assertEquals(statistics.getMax(), 1000);
    }

    @Test
    public void retrieve_the_transaction_with_the_smallest_value (){
        IntSummaryStatistics statistics = transactions.stream()
                .mapToInt(Transaction::getValue).summaryStatistics();

        Transaction transactionWithSmallestValue = transactions.stream().
                filter(p->p.getValue()==statistics.getMin()).findAny().get();
        Transaction expected = new Transaction(brian, 2011, 300);

        assertEquals(expected, transactionWithSmallestValue);

        /** ANOTHER WAY: SOURCE: JAVA 8 IN ACTION */
        Transaction smallestTransaction = transactions.stream().reduce(
                (t1, t2)->t1.getValue() < t2.getValue() ? t1:t2
        ).get();

        double a = DoubleStream.iterate(1,i->i+1).limit(1000).sum();



        assertEquals(expected,smallestTransaction);
    }

}
