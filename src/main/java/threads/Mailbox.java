package threads;

/**
 * Postman delivery with producer consumer pattern via a shared mailbox with wait() and notify()
 *
 * @author ndreddy
 */
public class Mailbox {
    private boolean redFlag;
    private String mail;

    public synchronized void put(String newMail) {
        // has a mail
        while (redFlag) {
            System.out.println("Waiting to put");
            try {
                wait();
            } catch (InterruptedException e) {
                // set interrupt flag in execution thread
                Thread.currentThread().interrupt();
            }
        }
        redFlag = true;
        this.mail = newMail;
        notifyAll();
    }

    public synchronized String take() {
        while (!redFlag) { // empty box
            System.out.println("Waiting to take");
            try {
                wait();
            } catch (InterruptedException e) {
                // set interrupt flag in execution thread
                Thread.currentThread().interrupt();
            }
        }
        redFlag = false;
        notifyAll();
        return mail;
    }

    public static void main(String[] args) {
        final Mailbox mb = new Mailbox();


        // Producer thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mb.put("Hello World");
                    System.out.println("Put: Hello World");
                }
            }
        }).start();

        // Consumer thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mb.take();
                    System.out.println("Take: Hello World");
                }
            }
        }).start();


//        Thread p = new Thread(new Postman(mb));
//        Thread c = new Thread(new Comnman(mb));
//        p.start();
//        c.start();
    }
}

//class Postman implements Runnable {
//    private Mailbox myMailbox;
//
//    Postman(Mailbox box){
//        this.myMailbox = box;
//    }
//
//    public void run(){
//        while (true){
//            myMailbox.put("Hello");
//            System.out.println("Postman : Delivered Hello mail");
//        }
//    }
//}
//
//class Comnman extends Thread{
//    private Mailbox myMailbox;
//
//    Comnman(Mailbox box){
//        this.myMailbox = box;
//    }
//
//    public void run(){
//        while (true){
//            System.out.println("Comnman: Received "+ myMailbox.take());
//        }
//    }
//}