
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

class m extends Thread {

    
    private int m;
    
    
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                int randomNumber = (int) (Math.random() * 10 + 1);
                long pid = ProcessHandle.current().pid();
                long tid = Thread.currentThread().getId();
                System.out.println("pid: " + pid + " random number: " + randomNumber + " tid: " + tid);
                Thread.sleep(randomNumber * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(m.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

}

public class Example {

    public static void main(String[] args) throws InterruptedException {

        ArrayList<Thread> threadList = new ArrayList<>();
        threadList.add(new m());
        threadList.add(new m());

        for (Thread thread : threadList) {
            thread.start();
        }
        for (Thread thread : threadList) {
            thread.join();
        }

    }

}
