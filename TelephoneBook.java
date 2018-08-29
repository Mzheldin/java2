package hw3;

import java.util.Map;
import java.util.TreeMap;

public class TelephoneBook {

    // непосредственно телефонный справочник, строка-ключ для фамилии и строка-значение для номера
    private Map <String, String> tbook = new TreeMap<String, String>();
    // метод записи в справочник, приниает строку для фамилии и строку для номера
    public void add (String name, String number) {
        boolean flag = false; // индикатор уникальности записываемой фамилии
        for (Map.Entry<String, String> o : tbook.entrySet()) { // проверяем справочник на наличии такой же фамилии
            if (o.getKey().equals(name)){ // если такая запись уже есть
                if (!o.getValue().equals(number)) o.setValue(o.getValue() + "; " + number); // и номер отличен от
                // вносимого то записываем за ним вносимый номер (работает только один раз, далее при новой попытке
                // внести такой же номер "защита" уже не сработает
                flag = true; // отмечаем, что вносимая фамилия повторяется
            }
        }
        if (!flag) tbook.put(name, number); // если вносимая фамилия не повторяется, создаем новую запись
    }
    // просто геттер номера по фамилии
    public String get (String name) {
        return tbook.get(name);
    }

}
