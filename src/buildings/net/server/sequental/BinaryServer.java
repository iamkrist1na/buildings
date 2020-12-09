package buildings.net.server.sequental;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BinaryServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        Socket client = server.accept();
    }
}
