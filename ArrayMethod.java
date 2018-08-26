package hw2;

import java.util.Scanner;

public class ArrayMethod {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = 1;  //индикатор текущего вводимого элемента
        int ii;     // размерности вводимого массива
        int jj;

        System.out.println("Введите размерность двумерного массива :");
        ii = sc.nextInt();
        System.out.println("Введите размерность двумерного массива :");
        jj = sc.nextInt();

        String[][] strarr = new String[ii][jj];

        System.out.println("Задайте элементы строкового массива");
        //заполенине массива
        for (int i = 0; i < strarr.length; i++){
            for (int j = 0; j < strarr[i].length; j++){
                System.out.println("Введите " + k + "й элемент массива :");
                strarr[i][j] = sc.next();
                k++;
            }
        }
        // перехват исключений и вывод сообщения
        try {
            System.out.println("Сумма элементов массива " + summ(strarr));
        }
        catch (MyArraySizeException e) {
            e.printmsg();
        }
        catch (MyArrayDataException e){
            e.printmsg();
        }

        System.out.println("The end");
    }

    private static int summ (String[][] arr) throws MyArraySizeException, MyArrayDataException{
        // проверка и проброс своего исключения если размерности массива не соответствуют 4
        if (arr.length != 4) throw new MyArraySizeException("Некорректный размер массива");
            else {
                for (int i = 0; i < 4; i++){
                    if (arr[i].length != 4) throw new MyArraySizeException("Некорректный размер массива");
                }
        }

        int sum = 0;
        String err = null; // переменная запоминающая индексы последней обработаной ячейки
        // проверка наличия исключения ошибки преобразования строки в число, его перехват и проброс своего исключения с
         try {                                                                              //номерами последней ячейки
             for (int i = 0; i < arr.length; i++) {
                 for (int j = 0; j < arr[i].length; j++) {
                     err = " " + i + " " + j;
                     sum += Integer.parseInt(arr[i][j]);
                 }
             }
         }
         catch (NumberFormatException e) {
            throw new MyArrayDataException("Неверный формат данных в ячейке" + err) ;
         }
        return sum;
    }

}
