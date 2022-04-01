package Control;

import Model.Client;
import Model.Queue;

import java.util.ArrayList;

public interface Strategy {

    public void addClient(ArrayList<Queue> queues, Client client );
}

