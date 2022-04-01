package View;

import javax.swing.*;
import javax.swing.JFrame;


import java.awt.*;
import java.awt.event.ActionListener;


public class SimulationFrame extends JFrame{
    private JPanel contentPane;
    private JPanel upperPanel;
    private JLabel nrOfClientsLabel;
    private JTextField nrOfClientsTextField;
    private JLabel nrOfQueuesLabel;
    private JTextField nrOfQueuesTextField;
    private JLabel simulationTimeLabel;
    private JTextField simulationTimeTextField;
    private JLabel arrivalTimeLabel;
    private JLabel minArrivalLabel;
    private JTextField minArrivalTextField;
    private JLabel maxArrivalLabel;
    private JTextField maxArrivalTextField;
    private JLabel minServiceLabel;
    private JTextField minServiceTextField;
    private JLabel maxServiceLabel;
    private JTextField maxServiceTextField;
    private JLabel serviceTimeLabel;
    private JScrollPane lowerPanel;
    static  JTextArea queuesArea;
    private JButton startButton;
    private JLabel strategyLabel;
    private JTextField strategyTextField;


    public SimulationFrame() {
        this.prepareGui();
    }

    public void prepareGui() {
        this.setSize(1000, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.contentPane = new JPanel(new GridLayout(2, 1));
        prepareUpperPanel();
        prepareLowerPanel();
        this.setContentPane(this.contentPane);
        this.setVisible(true);
    }


    private void prepareUpperPanel() {

        this.upperPanel = new JPanel();
        this.upperPanel.setLayout(new GridLayout(6, 4));


        this.nrOfClientsLabel = new JLabel("Number of clients", JLabel.CENTER);
        this.upperPanel.add(this.nrOfClientsLabel);

        this.nrOfClientsTextField = new JTextField();
        nrOfClientsTextField.setPreferredSize(new Dimension(50, 40));
        this.upperPanel.add(nrOfClientsTextField);


        this.nrOfQueuesLabel = new JLabel("Number of queues", JLabel.CENTER);
        this.upperPanel.add(this.nrOfQueuesLabel);

        this.nrOfQueuesTextField = new JTextField();
        nrOfQueuesTextField.setPreferredSize(new Dimension(20, 20));
        this.upperPanel.add(nrOfQueuesTextField);

        this.simulationTimeLabel = new JLabel("Simulation time", JLabel.CENTER);
        this.upperPanel.add(this.simulationTimeLabel);
        this.simulationTimeTextField = new JTextField();
        simulationTimeTextField.setPreferredSize(new Dimension(50, 40));
        this.upperPanel.add(simulationTimeTextField);

        this.upperPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.arrivalTimeLabel = new JLabel("Arrival time", JLabel.CENTER);
        this.upperPanel.add(this.arrivalTimeLabel);

        Box b = Box.createHorizontalBox();
        b.add(Box.createHorizontalGlue());
        this.upperPanel.add(b);

        this.minArrivalLabel = new JLabel("min");
        this.minArrivalLabel.setPreferredSize(new Dimension(50, 40));
        minArrivalLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        b.add(minArrivalLabel);
        b.add(Box.createHorizontalStrut(5));

        this.minArrivalTextField = new JTextField();
        b.add(minArrivalTextField);
        b.add(Box.createHorizontalStrut(5));

        this.maxArrivalLabel = new JLabel("max");
        this.maxArrivalLabel.setPreferredSize(new Dimension(50, 40));
        maxArrivalLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        b.add(maxArrivalLabel);
        b.add(Box.createHorizontalStrut(5));

        this.maxArrivalTextField = new JTextField();
        b.add(maxArrivalTextField);
        b.add(Box.createHorizontalStrut(250));
        b.add(Box.createHorizontalGlue());

        this.serviceTimeLabel = new JLabel("Service time", JLabel.CENTER);
        this.upperPanel.add(this.serviceTimeLabel);

        Box b1 = Box.createHorizontalBox();
        b1.add(Box.createHorizontalGlue());
        this.upperPanel.add(b1);

        this.minServiceLabel = new JLabel("min");
        this.minServiceLabel.setPreferredSize(new Dimension(50, 40));
        minServiceLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        b1.add(minServiceLabel);
        b1.add(Box.createHorizontalStrut(5));

        this.minServiceTextField = new JTextField();
        b1.add(minServiceTextField);
        b1.add(Box.createHorizontalStrut(5));

        this.maxServiceLabel = new JLabel("max");
        this.maxServiceLabel.setPreferredSize(new Dimension(50, 40));
        maxServiceLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        b1.add(maxServiceLabel);
        b1.add(Box.createHorizontalStrut(5));

        this.maxServiceTextField = new JTextField();
        b1.add(maxServiceTextField);
        b1.add(Box.createHorizontalStrut(107));
        b1.add(Box.createHorizontalGlue());

        this.startButton = new JButton("start simulation");
        this.startButton.setFont(new Font("Arial", Font.BOLD, 15));
        b1.add(startButton);

        b1.add(Box.createHorizontalGlue());

        this.strategyLabel = new JLabel("Strategy", JLabel.CENTER);
        this.upperPanel.add(this.strategyLabel);

        this.strategyTextField = new JTextField();
        strategyTextField.setPreferredSize(new Dimension(20, 20));
        this.upperPanel.add(strategyTextField);

        this.contentPane.add(this.upperPanel);


    }

    private void prepareLowerPanel() {

        this.queuesArea = new JTextArea();
        this.queuesArea.setEditable(false);

        this.lowerPanel = new JScrollPane(queuesArea);
        this.lowerPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.lowerPanel.setLayout(new ScrollPaneLayout());


        this.contentPane.add(this.lowerPanel);

    }

    public JTextField getNrOfClientsTextField() {
        return nrOfClientsTextField;
    }

    public JTextField getNrOfQueuesTextField() {
        return nrOfQueuesTextField;
    }

    public JTextField getSimulationTimeTextField() {
        return simulationTimeTextField;
    }

    public JTextField getMinArrivalTextField() {
        return minArrivalTextField;
    }

    public JTextField getMaxArrivalTextField() {
        return maxArrivalTextField;
    }

    public JTextField getMinServiceTextField() {
        return minServiceTextField;
    }

    public JTextField getMaxServiceTextField() {
        return maxServiceTextField;
    }

    public JTextField getStrategyTextField() {
        return strategyTextField;
    }


    public static JTextArea getQueuesArea() {
        return queuesArea;
    }


    public void addListener(ActionListener e) {

        startButton.addActionListener(e);


    }
}


