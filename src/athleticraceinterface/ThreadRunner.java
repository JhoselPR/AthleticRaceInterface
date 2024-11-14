package athleticraceinterface;
import javax.swing.JTextArea;

public class ThreadRunner extends Thread {
    private Runner runner;
    private JTextArea resultsArea;

    public ThreadRunner(Runner runner, JTextArea resultsArea) {
        this.runner = runner;
        this.resultsArea = resultsArea;
    }
    
    @Override
    public void run() {
        try {
            Thread.sleep(runner.getSpeed() * 1000); // Convierte la velocidad en milisegundos
            synchronized (resultsArea) {
                resultsArea.append(runner.getName() + " - Tiempo: " + runner.getSpeed() + " segundos\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
