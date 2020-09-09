// For week 2
// sestoft@itu.dk * 2015-10-29

import java.util.HashSet;

public class TestLocking2 {
    public static void main(String[] args) {
        int threadCount = 10;
        Thread[] ts = new Thread[threadCount];

        for (int i = 0; i < threadCount; i++) {
            ts[i] = new Thread(() -> {
                for(int k = 0; k < 1000; k++){
                    DoubleArrayListExtended db = new DoubleArrayListExtended();
                    for(int d = 0; d < 100; d++){
                        db.add(d);
                    }
                }
            });
        }

        for (int i = 0; i < threadCount; i++) {
            ts[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            try {
                ts[i].join();
            } catch (InterruptedException exn) {
                System.out.println("Some thread was interrupted");
            }
        }

        var mySize = DoubleArrayListExtended.allLists().size();

        System.out.println("Expected size of all lists is: 1000. Actual size: " + mySize);


        var totalSize = DoubleArrayListExtended.totalSize();

        System.out.println("Expected size of totalSize is: 1000000. Actual size: " + totalSize);



        // DoubleArrayListExtended dal1 = new DoubleArrayListExtended();
        // dal1.add(42.1);
        // dal1.add(7.2);
        // dal1.add(9.3);
        // dal1.add(13.4);
        // dal1.set(2, 11.3);
        // for (int i = 0; i < dal1.size(); i++)
        //     System.out.println(dal1.get(i));
        // DoubleArrayListExtended dal2 = new DoubleArrayListExtended();
        // dal2.add(90.1);
        // dal2.add(80.2);
        // dal2.add(70.3);
        // dal2.add(60.4);
        // dal2.add(50.5);
        // DoubleArrayListExtended dal3 = new DoubleArrayListExtended();
        // System.out.printf("Total size = %d%n", DoubleArrayListExtended.totalSize());
        // System.out.printf("All lists  = %s%n", DoubleArrayListExtended.allLists());
    }
}

class DoubleArrayListExtended {
    private volatile static int totalSize = 0;
    private volatile static HashSet<DoubleArrayListExtended> allLists = new HashSet<>();

    // Invariant: 0 <= size <= items.length
    private double[] items = new double[2];
    private int size = 0;

    public DoubleArrayListExtended() {
        synchronized(DoubleArrayListExtended.class){
            allLists.add(this);
        }
        
    }

    // Number of items in the double list
    public int size() {
        return size;
    }

    // Return item number i, if any
    public double get(int i) {
        if (0 <= i && i < size)
            return items[i];
        else
            throw new IndexOutOfBoundsException(String.valueOf(i));
    }

    // Add item x to end of list
    public boolean add(double x) {
        if (size == items.length) {
            double[] newItems = new double[items.length * 2];
            for (int i = 0; i < items.length; i++)
                newItems[i] = items[i];
            items = newItems;
        }
        items[size] = x;
        synchronized (TestLocking2.class) {
            size++;
            totalSize++;
        }

        return true;
    }

    // Replace item number i, if any, with x
    public double set(int i, double x) {
        if (0 <= i && i < size) {
            double old = items[i];
            items[i] = x;
            return old;
        } else
            throw new IndexOutOfBoundsException(String.valueOf(i));
    }

    // The double list formatted as eg "[3.2, 4.7]"
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++)
            sb.append(i > 0 ? ", " : "").append(items[i]);
        return sb.append("]").toString();
    }

    public static int totalSize() {
        return totalSize;
    }

    public static HashSet<DoubleArrayListExtended> allLists() {
        return allLists;
    }
}