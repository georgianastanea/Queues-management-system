package Control;

import Model.Client;
import Model.Queue;

import java.util.ArrayList;

public class Scheduler {

    private ArrayList<Queue> queues;
    private int maxNumberOfQueues;
    private int maxNumberOfClients;
    private Strategy strategy;

    public Scheduler(int maxNumberOfQueues, int maxNumberOfClients) {

        queues = new ArrayList<Queue>();
        for (int i = 0; i < maxNumberOfQueues; i++) {
            Queue queue = new Queue();
            queues.add(queue);

            Thread t = new Thread(queue);
            t.start();

        }
        changeStrategy(SelectionPolicy.SHORTEST_QUEUE);
        this.maxNumberOfQueues = maxNumberOfQueues;
        this.maxNumberOfClients = maxNumberOfClients;
    }

        public void changeStrategy(SelectionPolicy policy) {

            if(policy == SelectionPolicy.SHORTEST_QUEUE){
                strategy = new ConcreteStrategyQueue();
            }
            if(policy == SelectionPolicy.SHORTEST_TIME){
                strategy = new ConcreteStrategyTime();
            }

        }

        public void dispatchClient (Client client){
            strategy.addClient(queues,client);
        }

        public ArrayList<Queue> getQueues(){
        return queues;
        }


}
