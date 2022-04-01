package Control;

import Model.Client;
import Model.Queue;

import java.util.ArrayList;

public class ConcreteStrategyTime implements Strategy{

    @Override
    public void addClient(ArrayList<Queue> queues, Client client) {

       int minTime = queues.get(0).getWaitingTime().intValue();

        for(Queue q : queues)
            if(q.getWaitingTime().intValue() < minTime)
                minTime = q.getWaitingTime().intValue();

        for(Queue q: queues)
            if(q.getWaitingTime().intValue() == minTime)
            {
                q.addClient(client);
                break;
            }

    }
}
