package hw2;

import java.text.NumberFormat;

public class MyArrayDataException extends RuntimeException{

    String msg;

    MyArrayDataException (String msg){
        this.msg = msg;
    }

    void printmsg(){
        System.out.println(msg);
    }
}
