package athleticraceinterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AthleticRaceInterface extends JFrame {
    private JTextField nameField;
    private JTextArea runnersArea;
    private JTextArea resultsArea;
    private JButton registerBtn, startBtn, resetBtn, endBtn;
    private JLabel registerLabel, runnersLabel, resultsLabel;
    private Runner[] runners = new Runner[5];
    private int runnersNumber = 0;

    public AthleticRaceInterface() {
        setTitle("Carrera atlética");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 1. Registro de corredores
        JPanel registerPanel = new JPanel(new BorderLayout());
        nameField = new JTextField(25);
        registerBtn = new JButton("Registrar");
        registerLabel = new JLabel("Registar corredor");
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(nameField);
        inputPanel.add(registerBtn);
        registerPanel.add(registerLabel, BorderLayout.NORTH);
        registerPanel.add(inputPanel, BorderLayout.CENTER);

        // 2. Corredores registrados
        JPanel runnersPanel = new JPanel(new BorderLayout());
        runnersLabel = new JLabel("Corredores registrados");
        runnersArea = new JTextArea(5, 30);
        runnersArea.setEditable(false);
        runnersPanel.add(runnersLabel, BorderLayout.NORTH);
        runnersPanel.add(new JScrollPane(runnersArea), BorderLayout.CENTER);

        // 3. Resultados y botones de control
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsLabel = new JLabel("Resultados");
        resultsArea = new JTextArea(5, 30);
        resultsArea.setEditable(false);
        JPanel btnPanel = new JPanel(new GridLayout(3, 1));
        startBtn = new JButton("Iniciar");
        resetBtn = new JButton("Reiniciar");
        endBtn = new JButton("Terminar");
        btnPanel.add(startBtn);
        btnPanel.add(resetBtn);
        btnPanel.add(endBtn);
        resultsPanel.add(resultsLabel, BorderLayout.NORTH);
        resultsPanel.add(resultsArea, BorderLayout.CENTER);
        resultsPanel.add(btnPanel, BorderLayout.EAST);

        // Secciones del JFrame
        add(registerPanel, BorderLayout.NORTH);
        add(runnersPanel, BorderLayout.CENTER);
        add(resultsPanel, BorderLayout.SOUTH);

        // Botón registrar
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerRunner();
            }
        });

        // Botón iniciar
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startRace();
            }
        });

        // Botón reiniciar
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetRace();
            }
        });

        // Botón terminar
        endBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // Registrar corredores
    private void registerRunner() {
        if (runnersNumber < 5) {
            String name = nameField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un nombre por favor.");
                return;
            }
            runners[runnersNumber] = new Runner(name);
            runnersArea.append((runnersNumber + 1) + " - " + name + "\n");
            runnersNumber++;
            nameField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "El límite es 5 corredores.");
        }
    }

    // Empezar la carrera
    private void startRace() {
        if (runnersNumber < 5) {
            JOptionPane.showMessageDialog(this, "Debe haber 5 corredores registrados.");
            return;
        }
        resultsArea.setText("");
        for (Runner runner : runners) {
            new ThreadRunner(runner, resultsArea).start();
        }
    }

    // Reiniciar la carrera
    private void resetRace() {
        runnersNumber = 0;
        runnersArea.setText("");
        resultsArea.setText("");
        runners = new Runner[5];
        nameField.setText("");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AthleticRaceInterface raceInterface = new AthleticRaceInterface();
            raceInterface.setVisible(true);
        });
    }
    
}
