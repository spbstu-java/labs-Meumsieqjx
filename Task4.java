import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.io.IOException;
import java.util.*;


public class Task4 {
    public static void main(String[] args)  {
        // 1
        List<Integer> numbers_list = Arrays.asList(1, 2, 3, 4, 4, 4, 2, 1);
        System.err.println(getAverage(numbers_list));

        // 2
        List<String> strings_list = Arrays.asList("pRIvet", "voy", "aura");
        System.out.println(changeString(strings_list));
        
        // 3
        System.out.println(squareNumbers(numbers_list));  

        // 4
        Collection<String> collection = List.of("1th", "2th", "3th");

        try
        {
            System.out.println(getLastElement(collection));
        }
        catch (NoSuchElementException e)
        {
            System.err.println(e.getMessage());
        }

        // 5
        int [] arr = new int[] {1, 3, 5, 2, 2, 4};
        System.out.println(getSumEvenNumbered(arr));   
        
        // 6
        List<String> strings = List.of("voice", "haor", "jorjo");
        Map<Character, String> map = convertListToMap(strings);
        System.out.println(map);
    }

    // метод, возвращающий среднее значение списка целых чисел
    private static double getAverage(List<Integer> numbers_list) {
        return (double) numbers_list.stream()
                        .mapToDouble(Integer::doubleValue)
                        .average()
                        .orElse(0);
    }

    // метод, приводящий все строки в списке в верхний регистр и добавляющий к ним префикс «_new_»
    private static List<String> changeString(List<String> strings_list) {
        return strings_list.stream()
                        .map(n -> n.toUpperCase())
                        .map(n -> n + " _new_ ")
                        .collect(Collectors.toList());
    }

    // метод, возвращающий список квадратов всех встречающихся только один раз элементов списка
    private static <T extends Number> List<Double> squareNumbers (List<T> number_list_squire) {
        Map<T, Long> map_group = number_list_squire.stream()
                        .collect(Collectors.groupingBy(n -> n, Collectors.counting()));

        return number_list_squire.stream()
                        .filter(n -> map_group.get(n) == 1)
                        .map(n -> Math.pow(n.doubleValue(), 2))
                        .collect(Collectors.toList());
    }

    // Метод для получения последнего элемента
    private static <T> T getLastElement(Collection<T> collection) {
        return collection.stream()
                        .reduce((n1, n2) -> n2)  
                        .orElseThrow(() -> new NoSuchElementException("Collection is empty"));
    }

    // метод, возвращающий сумму чётных чисел или 0
    private static int getSumEvenNumbered(int[] arr) {
        return Arrays.stream(arr)
                        .filter(n -> n % 2 == 0)
                        .sum();
    }
    
    // метод, преобразовывающий все строки в списке в Map, где первый символ – ключ, оставшиеся – значение
    public static Map<Character, String> convertListToMap(List<String> list) {
        return list.stream()
                        .filter(s -> s.length() > 1)
                        .collect(Collectors.toMap(s -> s.charAt(0), s -> s.substring(1)));
    }
}



// Output:
// 2.625
// [PRIVET _new_ , VOY _new_ , AURA _new_ ]
// [9.0]
// 3th
// 8
// {v=oice, h=aor, j=orjo}