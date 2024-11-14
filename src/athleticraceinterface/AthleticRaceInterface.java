package athleticraceinterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Módulo: Tópicos Avanzados de programación.
 * Reto 5. Programación concurrente e hilos.
 * Nombre: Félix Jhosel Peñaloza Ruiz.
 * Matrícula: 23020865.
 * Asesor: Andrés Espinal Jiménez.
 */
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

        // Sección 1: Registro de corredor
        JPanel registerPanel = new JPanel(new FlowLayout());
        nameField = new JTextField(15);
        registerBtn = new JButton("Registrar");
        //registerLabel = new JLabel("Registar corredor");
        //registerPanel.add(registerLabel);
        registerPanel.add(new JLabel("Ingresa nombre..."));
        registerPanel.add(nameField);
        registerPanel.add(registerBtn);

        // Sección 2: Corredores registrados
        runnersArea = new JTextArea(5, 30);
        runnersArea.setEditable(false);

        // Sección 3: Resultados y botones de control
        JPanel resultsPanel = new JPanel(new BorderLayout());
        resultsArea = new JTextArea(5, 30);
        resultsArea.setEditable(false);
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        startBtn = new JButton("Iniciar");
        resetBtn = new JButton("Reiniciar");
        endBtn = new JButton("Terminar");
        buttonPanel.add(startBtn);
        buttonPanel.add(resetBtn);
        buttonPanel.add(endBtn);
        resultsPanel.add(new JScrollPane(resultsArea), BorderLayout.CENTER);
        resultsPanel.add(buttonPanel, BorderLayout.EAST);

        // Agregar las secciones al JFrame
        add(registerPanel, BorderLayout.NORTH);
        add(new JScrollPane(runnersArea), BorderLayout.CENTER);
        add(resultsPanel, BorderLayout.SOUTH);

        // Acción para el botón Registrar
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerRunner();
            }
        });

        // Acción para el botón Iniciar
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startRace();
            }
        });

        // Acción para el botón Reiniciar
        resetBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetRace();
            }
        });

        // Acción para el botón Terminar
        endBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // Método para registrar corredores
    private void registerRunner() {
        if (runnersNumber < 5) {
            String name = nameField.getText();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
                return;
            }
            runners[runnersNumber] = new Runner(name);
            runnersArea.append((runnersNumber + 1) + " - " + name + "\n");
            runnersNumber++;
            nameField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Se ha alcanzado el límite de 5 corredores.");
        }
    }

    // Método para iniciar la carrera
    private void startRace() {
        if (runnersNumber < 5) {
            JOptionPane.showMessageDialog(this, "Debe haber 5 corredores registrados para iniciar la carrera.");
            return;
        }
        resultsArea.setText(""); // Limpiar resultados previos
        for (Runner runner : runners) {
            new ThreadRunner(runner, resultsArea).start();
        }
    }

    // Método para reiniciar la carrera
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
