package lesson2;

public class MyArraySizeException extends Exception {

    public MyArraySizeException() {
        System.err.println("Размер массива неверен");
    }
}
