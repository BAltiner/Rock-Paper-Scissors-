import java.io.*;
import java.net.*;
import java.util.*;

public class RPSclient 
{
    public static Socket socket = null;
    public static void main(String[] args) 
    {
        try 
        {
            socket = new Socket("localhost", 8558);
            System.out.println("Connected to Server\n");
        } 
        catch (IOException ioEx) 
        {
        	System.out.println("Unable connect to host.\n");
        	System.exit(1);
     	}
            
        String player,result;
        BufferedReader in;
        PrintWriter out;
        Scanner input = new Scanner(System.in);
        
        try
        {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            int round=0;
            while(round<5)
            {	
            	System.out.println("************ROUND "+(round+1)+"*************");
                System.out.println("\nOptions: " + in.readLine());
                System.out.print("Choice is: ");
                out.println(input.nextLine());
                player = in.readLine();
			
                System.out.println("Round Winner: " + player+"\n");
                round++;
                out.println(round);
            }
            result=in.readLine();
            System.out.println(result);        
        } 
        catch (IOException ioEx) 
        {
        	ioEx.printStackTrace();
        }
    }
}