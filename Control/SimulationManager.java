package Control;

import Model.Client;
import View.GUIControl;
import View.SimulationFrame;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


public class SimulationManager implements Runnable {
    private Scheduler scheduler;
    private SimulationFrame simulationFrame;
    private ArrayList<Client> generatedClients;

    public int timeLimit ;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int minArrivalTime;
    public int maxArrivalTime;
    public int numberOfQueues;
    public int numberOfClients;
    public SelectionPolicy selectionPolicy ;

    public int avgServiceTime =0;

    public static void main( String[] args )
    {
        SimulationFrame simulationFrame = new SimulationFrame();
        GUIControl guiControl = new GUIControl(simulationFrame);

    }


    public SimulationManager(int timeLimit, int minProcessingTime,int maxProcessingTime, int minArrivalTime, int maxArrivalTime, int numberOfClients, int numberOfQueues, String selectionPolicy){
       this.numberOfQueues = numberOfQueues;
       this.numberOfClients = numberOfClients;
       this.maxProcessingTime = maxProcessingTime;
       this.minProcessingTime = minProcessingTime;
       this.minArrivalTime = minArrivalTime;
       this.maxArrivalTime = maxArrivalTime;
       this.selectionPolicy =SelectionPolicy.valueOf(selectionPolicy);
       this.timeLimit = timeLimit;

        this.scheduler = new Scheduler(numberOfQueues,numberOfClients);

        this.scheduler.changeStrategy(SelectionPolicy.valueOf(selectionPolicy));
        generatedClients = new ArrayList<Client>();
        generateNRandomClients();

    }



    private void generateNRandomClients(){

        for(int i =1;i<= numberOfClients;i++){
            int id = i;
            Random random = new Random();
            int serviceTime = random.nextInt(maxProcessingTime - minProcessingTime) + minProcessingTime;
            avgServiceTime = avgServiceTime + serviceTime;
            int arrivalTime = random.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            Client client = new Client(id,arrivalTime,serviceTime);
            generatedClients.add(client);

        }
        generatedClients.sort(Comparator.comparing(Client::getArrivalTime));

    }


    @Override
    public void run() {

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("queues.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int currentTime = 1;

        printWriter.println("waiting clients: ");
        SimulationFrame.getQueuesArea().append("waiting clients\n\n");


        for(Client client1: generatedClients) {
            printWriter.println("(" + client1.getID() + ',' + client1.getArrivalTime() + ',' + client1.getServiceTime() + ')' + ' ');
            SimulationFrame.getQueuesArea().append("(" + client1.getID() + ',' + client1.getArrivalTime() + ',' + client1.getServiceTime() + ')' + ' ' + '\n');
        }
        SimulationFrame.getQueuesArea().append("\n");
        int maxClients = 0;
        int peakHour = -1;

        while(currentTime <= timeLimit){

            printWriter.println("Simulation time: " + currentTime + '\n');
            SimulationFrame.getQueuesArea().append("Simulation time: " + currentTime + "\n\n");

            int i=0;
            if(!generatedClients.isEmpty()) {

                while (i < generatedClients.size()) {

                    Client varClient = generatedClients.get(i);

                    if (varClient.getArrivalTime() == currentTime) {

                        scheduler.dispatchClient(varClient);
                        generatedClients.remove(varClient);
                    } else i++;
                }
            }

            int j= 0;
            while(j < numberOfQueues){
                printWriter.println("Queue " + (int)(j+1) + " :");
                SimulationFrame.getQueuesArea().append("Queue " + (int)(j+1) + " :");

                int nr = 0;
                String output = "";
                while(nr < scheduler.getQueues().get(j).getQueue().size()){
                     output = "";

                    output += " "+ scheduler.getQueues().get(j).toString();

                    nr++;

                }

                printWriter.println(output);
                SimulationFrame.getQueuesArea().append(output + "\n\n");
                j++;
            }


                int k = 0;
                while (k < scheduler.getQueues().size()) {
                    if(scheduler.getQueues().get(k).getQueue().peek() != null){
                        if(scheduler.getQueues().get(k).getQueue().peek().getServiceTime() > 1)
                            scheduler.getQueues().get(k).getQueue().peek().setServiceTime(scheduler.getQueues().get(k).getQueue().peek().getServiceTime() -1);
                        else
                            scheduler.getQueues().get(k).removeClient();

                    }

                     k++;
                }

            int sumSizes = 0;
            for(int l=0;l< scheduler.getQueues().size();l++)
                sumSizes = sumSizes + scheduler.getQueues().get(l).getQueue().size() ;


            if(sumSizes > maxClients) {
                maxClients = sumSizes;
                peakHour = currentTime;

            }

            currentTime++;


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        printWriter.println("Average service time is: " + (float)avgServiceTime/numberOfClients + '\n');
        printWriter.println("Average waiting time is: " + (float)avgServiceTime/numberOfClients/numberOfQueues + '\n');
        printWriter.println("Peak hour is " + peakHour +  '\n');
        printWriter.close();

        SimulationFrame.getQueuesArea().append("Average service time is: " + (float)avgServiceTime/numberOfClients + "\n\n");
        SimulationFrame.getQueuesArea().append("Average waiting time is: " + (float)avgServiceTime/numberOfClients/numberOfQueues + "\n\n");
        SimulationFrame.getQueuesArea().append("Peak hour is " + peakHour +  "\n\n");

    }



    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setMaxProcessingTime(int maxProcessingTime) {
        this.maxProcessingTime = maxProcessingTime;
    }

    public void setMinProcessingTime(int minProcessingTime) {
        this.minProcessingTime = minProcessingTime;
    }

    public void setMinArrivalTime(int minArrivalTime) {
        this.minArrivalTime = minArrivalTime;
    }

    public void setMaxArrivalTime(int maxArrivalTime) {
        this.maxArrivalTime = maxArrivalTime;
    }

    public void setNumberOfQueues(int numberOfQueues) {
        this.numberOfQueues = numberOfQueues;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public void setSelectionPolicy(SelectionPolicy selectionPolicy) {
        this.selectionPolicy = selectionPolicy;
    }


}
