import java.util.Arrays;
import java.util.List;

public class Exer1_Imperative {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int sumEven = 0;
        for (int n : numbers) {
            if (n % 2 == 0) {
                sumEven += n;
            }
        }

        System.out.println("Sum of even numbers (Imperative): " + sumEven);
    }
}