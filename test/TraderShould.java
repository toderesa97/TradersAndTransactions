import org.junit.Test;
import tradersTransaction.Trader;

import java.util.*;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by tdrs on 25/11/16.
 */
public class TraderShould {
    private final String DEFAULT_NAME = "Bob";
    private final String DEFAULT_CITY = "New York";
    private Trader trader = new Trader(DEFAULT_NAME,DEFAULT_CITY);
    @Test
    public void have_a_name_and_a_city (){

        assertEquals(trader.getCity(),DEFAULT_CITY);
        assertEquals(trader.getName(),DEFAULT_NAME);
    }

    @Test
    public void print_as_is_expected_a_String_representation (){
        assertEquals(String.format("Trader:%s in %s",DEFAULT_NAME,DEFAULT_CITY),trader.toString());
    }
}
