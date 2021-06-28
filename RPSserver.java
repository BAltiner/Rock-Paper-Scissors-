import java.io.*;
import java.net.*;
import java.util.*;

public class RPSserver 
{
    private static ServerSocket server;
    private static Socket socket;
    private static final int PORT = 8558;
    
    private static boolean  connection = true;
    
    public static void main(String[] args) 
    {
        String serverTurn;
        
        BufferedReader in;
        PrintWriter out;
        Scanner clientChoiceIn = new Scanner(System.in);
        
        try 
        {
            server = new ServerSocket(PORT);
            System.out.println("Status ready. Waiting for client..");
        } 
        catch (IOException ioEx) {
        System.exit(1);
        }
        while(connection) {            
            try 
            {
                socket = server.accept();
                System.out.println("Client connected. Game Starting..\n");
            } 
            catch (IOException ioEx) 
            {
                System.exit(1);
            }
            try 
            {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(),true);
                
                int count=1;
                String round="";
                int serversScore=0;
                int clientsScore=0;
                
                while(!round.equals("5")){

                	Random rand = new Random();
                	int i = 3;
                	int random = rand.nextInt(i)+1;
                	out.println("Rock = 1, Paper = 2, Scissor = 3");
                
                	String clientsChoice = in.readLine();
                	if (clientsChoice.equals("1")){
                		System.out.println("************ROUND "+(count)+"*************");
                		System.out.println("Clients Choice: Rock");
                	}
                	else if (clientsChoice.equals("2")){
                		System.out.println("************ROUND "+(count)+"*************");
                		System.out.println("Clients Choice: Paper");
                	}
                	
                	else if (clientsChoice.equals("3")){
                		System.out.println("************ROUND "+(count)+"*************");
                		System.out.println("Clients Choice: Scissor");
                	}
                	
                	else{
                		System.out.println("************ROUND "+(count)+"*************");
                		System.out.println("Clients Choice: INVALID");
                		out.println("No one");
                	}
                    count = count +1;
                	serverTurn=String.valueOf(random);
                	
                	if (serverTurn.equals("1")){
                		System.out.println("Servers Choice: Rock");
                	}
                	else if (serverTurn.equals("2")){
                		System.out.println("Servers Choice: Paper");
                	}
                	
                	else if (serverTurn.equals("3")){
                		System.out.println("Servers Choice: Scissor");
                	}
                	                   
                    //inc scores
                	if (clientsChoice.equals(serverTurn)) {
                        out.println("Draw State.");
                        System.out.println("Clients Score:" + clientsScore + "\nServers Score:" + serversScore+"\n");
                	}
                    
                    else if (clientsChoice.equals("1") && serverTurn.equals("3")) {
                        out.println("Client");
                        clientsScore++;
                        System.out.println("Clients Score:" + clientsScore + "\nServers Score:" + serversScore+"\n");
                    }
                    
                    else if (clientsChoice.equals("3") && serverTurn.equals("1")) {
                        out.println("Server");
                        serversScore++;
                        System.out.println("Clients Score:" + clientsScore + "\nServers Score:" + serversScore+"\n");
                    }
                    
                    else if (clientsChoice.equals("1") && serverTurn.equals("2")) {
                        out.println("Server");
                        serversScore++;
                        System.out.println("Clients Score:" + clientsScore + "\nServers Score:" + serversScore+"\n");
                    }
                    
                    else if (clientsChoice.equals("2") && serverTurn.equals("1")) {
                        out.println("Client");
                        clientsScore++;
                        System.out.println("Clients Score:" + clientsScore + "\nServers Score:" + serversScore+"\n");
                    }
                    
                    else if (clientsChoice.equals("3") && serverTurn.equals("2")) {
                        out.println("Client");
                        clientsScore++;
                        System.out.println("Clients Score:" + clientsScore + "\nServers Score:" + serversScore+"\n");
                    }
                    
                    else if (clientsChoice.equals("2") && serverTurn.equals("3")) {
                        out.println("Server");
                        serversScore++;
                        System.out.println("Clients Score:" + clientsScore + "\nServers Score:" + serversScore+"\n");
                    }
                    
                    round = in.readLine();
                }
                
                // Final Score Compare
                if(clientsScore == serversScore)
                {	
                	System.out.println("\n************FINAL*************\nGame is Draw.");
                	out.println("\n************RESULT: Game is Draw.*************\n");
                    System.out.println("Clients Score:" + clientsScore+"\nServers Score:"+serversScore+"\n");
                }

                else if(clientsScore > serversScore)
                {
                	System.out.println("************WINNER*************\nis Client");
                	out.println("************WINNER is Client!*************\n");
                    System.out.println("Clients Score:" + clientsScore+"\nServers Score:" + serversScore+"\n");
                }
                    
                else if(serversScore > clientsScore)
                {
                	System.out.println("************WINNER*************\nis Server");
                	out.println("************WINNER is Server!*************\n");
                    System.out.println("Servers Score:" + serversScore+"\nClients Score:"+clientsScore+"\n");
                }
            }
            catch (IOException ioEx) 
            {
                System.out.print("Client left from game.\n");
                clientChoiceIn.close();
            }
        }
    }
}