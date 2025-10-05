package NewBoadcastChannel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class Boadcastcahnnel {
    static AtomicInteger i=new AtomicInteger(0);
    static AtomicInteger j=new AtomicInteger(0);
    public static void main(String[] args)throws Exception {
        ServerSocket serverSocket=new ServerSocket(9090);
        System.out.println("Welcome to The Boadcast Channel");
        
        while (true) {
            try {
            Socket clientssocket;
            clientssocket=serverSocket.accept();
            i.incrementAndGet();
            System.out.println("Client"+i.get()+" Has Joined the Chat");
            new Thread(()->{
                try {
                    int id=i.get();
                    BufferedReader in=new BufferedReader(new InputStreamReader(clientssocket.getInputStream()));
                    PrintWriter out=new PrintWriter(clientssocket.getOutputStream(),true);
                    String mess=in.readLine();
                    while (!mess.equalsIgnoreCase("no")) {
                        System.out.println("CLient"+id+" Says:"+mess);
                        mess=in.readLine();
                    }
                    System.out.println("CLient"+id+" Has Left the chat");
                    j.incrementAndGet();
                    if (i.get()==j.get()) {
                        serverSocket.close();
                    }
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                }
            }).start();    
            } catch (Exception e) {
                System.out.println("Boadcast Channel Has Been Ended successfully");
                break;
            }
            
        }

    }
}