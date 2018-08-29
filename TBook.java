package hw3;

import java.util.*;

public class TBook {

    public static void main(String[] args) {
        // задание 1
        String [] strarr = {"qw", "qe", "qw", "qr", "gf", "dfd", "vb", "vf", "vf", "gg", "gg", "gl", "gj", "wd",
                "qw", "qe", "qw", "qr", "gf", "ff"};

        wordArray(strarr);
        // задание 2
        TelephoneBook tb = new TelephoneBook();

        tb.add("Ivanov", "+7 123 456 78 90");
        tb.add("Petrov", "+7 321 654 78 09");
        tb.add("Sidorov", "+7 213 564 87 90");
        tb.add("Ivanov", "+7 123 987 65 40");

        System.out.println("Petrov " + tb.get("Petrov"));
        System.out.println("Sidorov " + tb.get("Sidorov"));
        System.out.println("Ivanov " + tb.get("Ivanov"));

    }
    // метод для первого пункта задания, на вход принимает массив строк
    static void wordArray (String[] arr) {

        Set <String> unicwords = new TreeSet<String>(); //коллекция с контролем уникальности для неповторяющихся элементов
        Map <String, Integer> wordsnum = new TreeMap<String, Integer>();//коллекция соответсивй элемент - кол-во равных
        int x; // счетчик одинаковых элементов
        // проходя по всему входящему массиву формируем коллекцию из неповторяющихся элементов
        for (String o : arr){
            unicwords.add(o);
        }
        // и на ее основе создаем записи в коллекцию соответствий и проходим по массиву в подсчете повторяющихся элементов
        for (String o: unicwords) {
            x = 0;
            for (String u: arr) {
                if (u.equals(o)) x++;
            }
            wordsnum.put(o, x);
        }
        // вывод в консоль коллекций, для Map 2 варианта
        for ( String o: unicwords) {
            System.out.println(o);
        }

        System.out.println(wordsnum.toString());


//        for (Map.Entry<String, Integer> o: wordsnum.entrySet()){
//            System.out.println(o.getKey() + " : " + o.getValue());
//        }
    }

}
