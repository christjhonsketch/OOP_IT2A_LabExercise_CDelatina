import java.util.Arrays;
import java.util.List;

public class Exer1_Declarative {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Declarative style: describe the result, not the steps
        numbers.stream()
               .filter(n -> n > 5)
               .map(n -> n * n)
               .forEach(n -> System.out.println("Square: " + n));
    }
}
