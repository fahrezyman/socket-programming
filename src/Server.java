import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                System.out.println("LOG : 'Client connected : " + clientHandler.clientUsername + "'");

                Thread thread = new Thread(clientHandler);
                thread.start();


                if (socket.isClosed()){
                    System.out.println("LOG : 'Client disconnected : " + clientHandler.clientUsername + "'");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }




    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);
        Server server = new Server(serverSocket);
        System.out.println("FTPServer Groupchat started");
        System.out.println("Server started in port 8000");
        server.startServer();

    }

}
