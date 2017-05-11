package HW2.Task2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FunctionUtils {

    public static <T> List<T> allMatches(List<T> candidates, Predicate<T> matchFunction)
    {
        List<T> result = new ArrayList<>();

        for(T possibleMatch: candidates) {
            if (matchFunction.test(possibleMatch)) {
                result.add(possibleMatch);
            }
        }
        return result;
    }

    public static <T> T firstMatch(List<T> candidates, Predicate<T> matchFunction) {
        for(T possibleMatch: candidates) {
            if(matchFunction.test(possibleMatch)) {
                return(possibleMatch);
            }
        }
        return(null);
    }
}
