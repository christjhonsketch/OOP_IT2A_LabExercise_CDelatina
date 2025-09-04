import java.util.Arrays;
import java.util.List;

public class Exer1_Functional {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Functional style: using streams and lambda
        int sumEven = numbers.stream()
                             .filter(n -> n % 2 == 0)   // keep even numbers
                             .mapToInt(Integer::intValue)
                             .sum();

        System.out.println("Sum of even numbers (Functional): " + sumEven);
    }
}
