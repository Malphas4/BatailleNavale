package M1.reseau.client.console;
import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.PrintWriter;
        import java.net.Socket;

public final class ClientTCP {

    private void process() {
        Socket socket = null;

        BufferedReader in = null;
        PrintWriter out = null;

        while(!isIn)
        try{

            socket = new Socket("127.0.0.1", 9999);

            // Get the input and output streams
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Send a GET_TIME request and print the response to the standard output
            out.println("GET_TIME");
            System.out.println(in.readLine());

            // Send a GET_JAVA_VERSION request and print the response to the standard output
            out.println("GET_JAVA_VERSION");
            System.out.println(in.readLine());

            // Send a CLOSE request to the server to end communication
            out.println("CLOSE");
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try{
                if(socket != null)
                    socket.close(); // Close the socket (closing the socket also closes the input and output streams)
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args){
        new TcpClient().process();
    }
}
