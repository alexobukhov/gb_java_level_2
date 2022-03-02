package lesson3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String testString = "создать массив с набором слов среди которых должны встречаться" +
                " повторяющиеся найти и вывести список уникальных слов из которых состоит массив дубликаты не" +
                " считаем посчитать сколько раз встречается каждое слово создать";

        String[] testArray = testString.split(" ");
        Collections.sort(Arrays.asList(testArray));
        Set<String> testSet = new HashSet<>();
        for (int i = 0; i < testArray.length; i++) {
            testSet.add(testArray[i]);
        }
        Iterator<String> iterator = testSet.iterator();
        while (iterator.hasNext()) {
            String data = iterator.next();
            int count = 0;
            for (int i = 0; i < testArray.length; i++) {
                if (data.equals(testArray[i])) {
                    count++;
                }
            }
            System.out.println(iterator.next() + " - " + count);
        }

        Map<String, ArrayList<String>> map = new TreeMap<>();
        add(map, new PhoneBook("Тимофей", "1234567"));
        add(map, new PhoneBook("Тимофей", "1234568"));
        add(map, new PhoneBook("Сергей", "1234567"));
        add(map, new PhoneBook("Анастасия", "1234567"));
        add(map, new PhoneBook("Сергей", "1234537"));

        get(map,"Тимофей");
    }

    private static Map<String, ArrayList<String>> add(Map<String, ArrayList<String>> map, PhoneBook phoneBook) {
        Boolean keyExists = false;
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            if (key.equals(phoneBook.getSurname())) {
                map.get(key).add(phoneBook.getPhoneNumber());
                keyExists = true;
            }
        }
        if (keyExists == false) {
            ArrayList<String> phones = new ArrayList<>();
            phones.add(phoneBook.getPhoneNumber());
            map.put(phoneBook.getSurname(), phones);
        }
        return map;
    }

    private static void get(Map<String, ArrayList<String>> map, String surname) {
        Map<String, String> surnameList = new TreeMap<>();
        for (Map.Entry<String, ArrayList<String>> entry : map.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            if (key.equals(surname)) {
                System.out.print(key + " ");
                for (String item : value) {
                    System.out.print(value + ", ");;
                }
            }
        }
    }
}
