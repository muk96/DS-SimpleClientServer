package org.example;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        //We'll be using the TCP protocol for communication
        //This is the server class. It will listen for incoming connections from other nodes

        //The port no. that nodes will use to communicate with each other
        int portNumber = 12345;

        //This is a Java try-with-resources statement, which ensures that the resources created within the parentheses
        // are properly closed after the block is executed, whether or not an exception is thrown.
        try (
                //We create a TCP socket and bind it to the port no. we defined earlier
                // which listens for incoming connections on a specified port number
                ServerSocket serverSocket = new ServerSocket(portNumber);

                //Socket object, which represents a connection to a client.
                //The accept() method of the ServerSocket blocks until a client connects,
                // at which point it returns a Socket object that can be used to communicate with the client.

                //                The accept() method blocks (just sits there) while
                //        it’s waiting for a client Socket connection. When a
                //        client finally tries to connect, the method returns
                //        a plain old Socket (on a different port) that knows
                //        how to communicate with the client (i.e., knows the
                //        client’s IP address and port number). The Socket is on
                //        a different port than the ServerSocket, so that the
                //        ServerSocket can go back to waiting for other clients.
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
        ) {
            System.out.println("Connected by " + clientSocket.getInetAddress());
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received message from client: " + inputLine);
                out.println("Message received: " + inputLine);
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

