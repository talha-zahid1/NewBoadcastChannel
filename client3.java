import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client3 {
    public static void main(String[] args)throws Exception {
        Socket socket=new Socket("localhost",9090);
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
        System.out.println("Do You Want to chat?(yes/no)");
        String ans=in.readLine();
        String mess;
        while (!ans.equalsIgnoreCase("no")  && ans.equalsIgnoreCase("yes")) {
            System.out.println("Enter Message");
            mess=in.readLine();
            out.println(mess);
            System.out.println("Do You Want to end the chat?(yes/no)");
            ans=in.readLine();
        }
        if (ans.equalsIgnoreCase("no")) {
            out.println(ans);
        }
        while (!ans.equalsIgnoreCase("no")) {
         
            System.out.println("Invalid Answer.Please Answer in yes or No");
            ans=in.readLine();
            out.println(ans);
           
        }
        out.close();
        in.close();
        socket.close();
    }   
}
