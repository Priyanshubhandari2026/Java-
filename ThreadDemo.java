 class Counter {
    private int count = 0;

    // synchronized method to avoid race condition
    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

    class MyThread extends Thread {
        private Counter counter;

        MyThread(Counter counter) {
            this.counter = counter;
        }

        public void run() {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        }
    }

    public class ThreadDemo {
        public static void main(String[] args) throws InterruptedException {
            Counter counter = new Counter();

            MyThread t1 = new MyThread(counter);
            MyThread t2 = new MyThread(counter);

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            System.out.println("Final Count: " + counter.getCount());
        }
    }

/*Output:
Final Count: 2000
*/