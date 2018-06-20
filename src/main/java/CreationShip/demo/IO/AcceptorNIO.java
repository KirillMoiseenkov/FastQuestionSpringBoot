package CreationShip.demo.IO;

import CreationShip.demo.service.MessageService;

public class AcceptorNIO implements Runnable{

    private MessageService messageService;

   public AcceptorNIO(MessageService messageService)
    {
    this.messageService = messageService;
    }

    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(1000);
                System.out.println(messageService.getAll().size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
