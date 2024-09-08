public class Main {

    public static void main(String[] args) throws InterruptedException {

        int customersNumber = 50;
        Thread[] threads = new Thread[customersNumber];

        for (int i = 0; i < customersNumber; i++) {
            if (i % 2 == 0) {
                threads[i] = new Thread(new OnlyOneOrder(i));
            } else {
                threads[i] = new Thread(new BulkOrder(i));
            }
            threads[i].start();
            Thread.sleep(300);
        }

        for (int i = 0; i < customersNumber; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        OrderTemplate.closeDataSource();
    }
}
