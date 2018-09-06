package hw5;

public class Main{

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) {
        method1();
        method2();
    }
    // первый метод, выполняющий операцию над целым массивом
    static void method1(){
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) arr[i] = 1;
        long a = System.currentTimeMillis();
        opr(arr);
        System.out.println(System.currentTimeMillis() - a);
    }
    // второй метод, разделяющий массив на две части, выполняющий над ними операции в отдельных потоках и собирающий их
    static void method2(){
        final float[] arr = new float[size];
        final float[] a1 = new float[h];
        final float[] a2 = new float[h];
        for (int i = 0; i < arr.length; i++) arr[i] = 1;
        long a = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);
        // новые потоки созданы через отдельные классы
        class thread extends Thread {
            float[] thrdarr;
            thread(float[] thrdarr){
                this.thrdarr = thrdarr;
            }
            public void run() {
                opr(thrdarr);
            }
        }

        new Thread(new thread(a1)).start();
        new Thread(new thread(a2)).start();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println(System.currentTimeMillis() - a);
    }
    // метод операции над массивом
    static void opr(float[] arr){
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
    }
}
