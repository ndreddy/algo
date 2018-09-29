package threads;

/**
 * Postman delivery with producer consumer pattern via a shared mailbox with wait() and notify()
 *
 * @author ndreddy
 */
public class Mailbox {
    private boolean flag;
    private String mail;

    public synchronized void put(String newMail) {
        // has a mail
        while (flag) {
            System.out.println("Waiting to put");
            try {
                wait();
            } catch (InterruptedException e) {
                // set interrupt flag in execution thread
                Thread.currentThread().interrupt();
            }
        }
        flag = true;
        this.mail = newMail;
        notifyAll();
    }

    public synchronized String take() {
        while (!flag) { // empty box
            System.out.println("Waiting to take");
            try {
                wait();
            } catch (InterruptedException e) {
                // set interrupt flag in execution thread
                Thread.currentThread().interrupt();
            }
        }
        flag = false;
        notifyAll();
        return mail;
    }

    public static void main(String[] args) {
        final Mailbox mb = new Mailbox();

        // Producer thread
        new Thread(() -> {
            while (true) {
                mb.put("Hello World");
                System.out.println("Put: Hello World");
            }
        }).start();

        // Consumer thread
        new Thread(() -> {
            while (true) {
                mb.take();
                System.out.println("Take: Hello World");
            }
        }).start();

    }
}

