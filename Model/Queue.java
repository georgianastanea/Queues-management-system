package Model;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable{

    private BlockingQueue<Client> queue;
    private AtomicInteger waitingTime;

    public Queue() {
        this.queue = new LinkedBlockingQueue<Client>();
        this.waitingTime = new AtomicInteger(0);
    }



    public AtomicInteger getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(AtomicInteger waitingTime) {
        this.waitingTime = waitingTime;
    }

    public void addClient(Client client){
       queue.add(client);
        waitingTime.addAndGet(client.getServiceTime());
    }

    public void removeClient(){
        queue.remove();

    }


    public BlockingQueue<Client> getQueue() {
        return queue;
    }

    @Override
    public void run() {
        while(true){
            if(this.queue.size() > 0){

                Client currentClient = queue.peek();

            try {

                    Thread.sleep(currentClient.getServiceTime() * 1000);
                    waitingTime.addAndGet((-1) * currentClient.getServiceTime());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.remove(currentClient);
        }else {
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            }
        }

    }

    @Override
    public String toString() {
        return queue + "";
    }
}
