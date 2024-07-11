class PrintDemo {
    private String demoName;

    PrintDemo(String name) {
        this.demoName = name;
    }

    public void printCount() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Counter " + demoName + " --- " + i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted.");
        }
    }
}

class ThreadDemo extends Thread {
    private Thread t;
    private String threadName;
    private PrintDemo PD;

    ThreadDemo(String name, PrintDemo pd) {
        threadName = name;
        PD = pd;
    }

    public void run() {
        synchronized (PD) {
            PD.printCount();
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
        }
    }
}

public class SynchronousThread {

    public static void main(String args[]) {
        PrintDemo PD1 = new PrintDemo("Demo 1");
        PrintDemo PD2 = new PrintDemo("Demo 2");

        ThreadDemo T1 = new ThreadDemo("Thread - 1 ", PD1);
        ThreadDemo T2 = new ThreadDemo("Thread - 2 ", PD2);

        T1.start();
        T2.start();

        try {
            T1.join();
            T2.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted");
        }
    }
}

// Kode diatas menunjukkan loop sederhana menggunakan synchronous thread dalam
// Java.
// PrintDemo memiliki metode printCount() yang mencetak angka dari 5 hingga 1
// dengan jeda satu detik.
// ThreadDemo adalah subclass dari Thread yang menerima objek PrintDemo.
// Metode run() di ThreadDemo menggunakan blok synchronized untuk memastikan
// hanya satu thread yang dapat menjalankan printCount() pada satu waktu.
// ThreadApp membuat dua instance PrintDemo dan dua thread ThreadDemo, lalu
// memulai dan menunggu kedua thread selesai dengan join().