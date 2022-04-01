package View;

import Control.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIControl {
    private SimulationFrame simulationFrame;

    public GUIControl(SimulationFrame simulationFrame) {
        this.simulationFrame = simulationFrame;
        simulationFrame.addListener(new RunListener());
    }

    class RunListener implements ActionListener{

        public void actionPerformed(ActionEvent e){
            int timeLimit = 0;
            int maxProcessingTime=0;
            int minProcessingTime=0;
            int minArrivalTime=0;
            int maxArrivalTime=0;
             int numberOfQueues=0;
             int numberOfClients=0;
             String policy = "";

             timeLimit = Integer.parseInt(simulationFrame.getSimulationTimeTextField().getText());
            maxProcessingTime = Integer.parseInt(simulationFrame.getMaxServiceTextField().getText());
            minProcessingTime = Integer.parseInt(simulationFrame.getMinServiceTextField().getText());
            minArrivalTime = Integer.parseInt(simulationFrame.getMinArrivalTextField().getText());
            maxArrivalTime = Integer.parseInt(simulationFrame.getMaxArrivalTextField().getText());
            numberOfClients = Integer.parseInt(simulationFrame.getNrOfClientsTextField().getText());
            numberOfQueues = Integer.parseInt(simulationFrame.getNrOfQueuesTextField().getText());
            policy = simulationFrame.getStrategyTextField().getText();

            SimulationManager simulationManager = new SimulationManager(timeLimit,minProcessingTime,maxProcessingTime,minArrivalTime,maxArrivalTime,numberOfClients,numberOfQueues,policy);

            Thread t = new Thread(simulationManager);
            t.start();

        }
    }
}
