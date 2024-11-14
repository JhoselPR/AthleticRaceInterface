package athleticraceinterface;
import javax.swing.JTextArea;

public class ThreadRunner extends Thread {
    private Runner runner;
    private JTextArea resultsArea;
    private static int i = 1;

    public ThreadRunner(Runner runner, JTextArea resultsArea) {
        this.runner = runner;
        this.resultsArea = resultsArea;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(runner.getSpeed() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (ThreadRunner.class) {
            int currentPos = i++;
            resultsArea.append(currentPos + " - " + runner.getName() + " - Tiempo: " + runner.getSpeed() + " segundos\n");
        }
    }
}
