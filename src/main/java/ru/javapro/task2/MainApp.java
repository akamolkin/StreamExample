package ru.javapro.task2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainApp {
    public static void main(String[] args) {
        // Все задачи должны быть выполнены в одну строку.

        // Реализуйте удаление из листа всех дубликатов
        List<Integer> listWithDub = Arrays.asList(10, 8, 6, 2, 4, 8, 10, 1, 2, 4, 5, 9);
        List<Integer> listWithoutDup = listWithDub.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println(listWithoutDup);

        // Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        List<Integer> listInt = Arrays.asList(5, 2, 10, 9, 4, 3, 10, 1, 13);
        Integer maxThird = listInt.stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .limit(1)
                .mapToInt(x -> x).sum();
        System.out.println(maxThird);

        // Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9, в отличие от прошлой задачи здесь разные 10 считает за одно число)
        Integer maxThirdUnic = listInt.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .limit(1)
                .mapToInt(x -> x).sum();
        System.out.println(maxThirdUnic);

        // Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
        List<Employee> employees = new ArrayList<>(Arrays.asList(
               new Employee("John", 25, Position.ENGINEER),
               new Employee("Bob", 41, Position.DIRECTOR),
               new Employee("Ann", 22, Position.MANAGER),
               new Employee("Alex", 56, Position.ENGINEER),
               new Employee("Tom", 33, Position.MANAGER),
               new Employee("Bred", 60, Position.ENGINEER),
               new Employee("Luke", 46, Position.ENGINEER),
               new Employee("Jimmy", 28, Position.ENGINEER)
        ));
        List<String> threeOlderEngineer = employees.stream()
                .filter(employee -> employee.getPosition() == Position.ENGINEER)
                .sorted(Comparator.comparingInt(Employee::getAge).reversed())
                .limit(3)
                .map(employee -> employee.getName())
                .toList();
        System.out.println(threeOlderEngineer);

        // Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»
        double averageAge = employees.stream()
                .filter(employee -> employee.getPosition() == Position.ENGINEER)
                .collect(Collectors.averagingDouble(Employee::getAge));
        System.out.println(averageAge);

        // Найдите в списке слов самое длинное
        List<String> words = Arrays.asList("123456", "12", "1234", "123", "12345", "12");
        String longestWord = words.stream().max(Comparator.comparingInt(String::length)).get();
        System.out.println(longestWord);

        // Имеется строка с набором слов в нижнем регистре, разделенных пробелом. Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
        String str = "cat dog hamster parrot parrot dog dog cat";
        Map<String, Long> map = Arrays.asList(str.split(" ")).stream()
                .collect(Collectors.groupingBy(list -> (String) list, Collectors.counting()));
        System.out.println(map);

        // Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
        List<String> stringList = Arrays.asList("wolf", "tiger", "cat", "hamster", "owl", "parrot", "fox", "dog", "bat", "frog");
        stringList.stream()
                .sorted(Comparator.naturalOrder())
                .sorted(Comparator.comparingInt(String::length))
                .forEach(System.out::println);

        // Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом, найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
        String[] stringArrWithFiveWords = {"five words in each string", "there is five string too", "some other set of words", "one two three four five"};
        String longestArrWord1 = Arrays.stream(stringArrWithFiveWords)
                .toList().stream()
                .flatMap(wrd -> Stream.of(wrd.split(" ")))
                .collect(Collectors.toList()).stream()
                .max(Comparator.comparingInt(String::length)).get();
        System.out.println(longestArrWord1);
    }
}
