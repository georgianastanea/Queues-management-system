package Control;

import Model.Client;
import Model.Queue;

import java.util.ArrayList;

public class ConcreteStrategyQueue implements Strategy{

    @Override
    public void addClient(ArrayList<Queue> queues, Client client) {

        int minSize = queues.get(0).getQueue().size();

        for(Queue q: queues)
        {
            if(q.getQueue().size() < minSize)
                minSize = q.getQueue().size();
        }

        for(Queue q: queues)
        {
            if(minSize == q.getQueue().size()) {
                q.addClient(client);
                break;
            }
        }


    }
}
