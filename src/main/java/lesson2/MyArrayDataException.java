package lesson2;

public class MyArrayDataException extends Exception {

    public MyArrayDataException(int i, int j) {
        System.err.println(String.format("В ячейке %d, %d не верные данные", i, j));
    }
}
