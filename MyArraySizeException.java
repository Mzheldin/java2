package hw2;

public class MyArraySizeException extends Exception {

    private String msg;

    MyArraySizeException(String msg){
        this.msg = msg;
    }

    void printmsg (){
        System.out.println(msg);
    }
}
