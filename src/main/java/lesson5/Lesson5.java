package lesson5;

public class Lesson5 {
    public static void main(String[] args) {
        checkOneThread();
        checkTwoThreads();
    }

    private static void checkOneThread(){
        final int size = 10000000;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0F;
        }
        long start = System.currentTimeMillis();
        for (int  i = 0; i < arr.length; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время выполнения первого метода: " + (System.currentTimeMillis() - start) + " ms.");
    }

    private static void checkTwoThreads() {
        final int size = 10000000;
        final int halfSize = size / 2;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1;
        }
        long start = System.currentTimeMillis();
        float[] leftArray = new float[halfSize];
        float[] rightArray = new float[halfSize];
        float[] finalArray = new float[size];
        System.arraycopy(arr, 0, leftArray, 0, halfSize);
        System.arraycopy(arr, halfSize, rightArray, 0, halfSize);

        try {
            Thread threadLeft = new Thread(() -> {
                for (int i = 0; i < leftArray.length; i++) {
                    leftArray[i] = (float)(leftArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                            * Math.cos(0.4f + i / 2));
                }
            });
            Thread threadRight = new Thread(() -> {
                for (int i = 0; i < rightArray.length; i++) {
                    rightArray[i] = (float)(rightArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5)
                            * Math.cos(0.4f + i / 2));
                }
            });
            threadLeft.start();
            threadRight.start();
            threadLeft.join();
            threadRight.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.arraycopy(leftArray, 0, finalArray, 0, halfSize);
        System.arraycopy(rightArray, 0, finalArray, halfSize, halfSize);
        System.out.println("Время выполнения второго метода: " + (System.currentTimeMillis() - start) + " ms.");
    }
}
