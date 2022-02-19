package lesson2;

public class Main {
    public static void main(String[] args) {
        String[][] inArray = {{"str", "2", "3", "4"}
                , {"9", "8", "7", "error", "5"}
                , {"10", "13", "15", "17"}
                , {"27", "31", "107", "98"}};

        convertArray(inArray);
    }

    private static void convertArray(String[][] inArray) {
         try {
            if (inArray.length != 4) {
                throw new MyArraySizeException();
            }
            for (String[] item : inArray) {
                if (item.length != 4) {
                    throw new MyArraySizeException();
                }
            }
         } catch (MyArraySizeException e) {
             e.printStackTrace();
         }

         int sum = 0;
         for (int i = 0; i < inArray.length; i++) {
             for (int j = 0; j < inArray[i].length; j++) {
                 try {
                     boolean check = inArray[i][j].matches("\\D*");
                     if (check) {
                         throw new MyArrayDataException(i, j);
                     }
                     int result = Integer.parseInt(inArray[i][j]);
                     sum += result;
                 } catch (MyArrayDataException e) {
                     e.printStackTrace();
                 }
             }
         }
    }
}
