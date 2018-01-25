package multhreadfiletransport.client.reciever;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Created by dela on 1/23/18.
 */
public class RecieverServer {
    private ServerSocket serverSocket;
    private boolean goon;
    private int senderCount;
    private List<Socket> socketList;

    {
        try {
            serverSocket = new ServerSocket(33001);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RecieverServer() {
        goon = true;
    }

    public RecieverServer(int senderCount) {
        this.senderCount = senderCount;
        goon = true;
    }

    public void start() throws IOException {
        // RS持续监听, 当有一个sender连接, 就分发一个线程RT
        int time = 0;
        while (goon) {
            Socket socket = serverSocket.accept();
            new Thread(new RecieverThread(socket)).start();
            time++;

            if(time == senderCount) {
                goon = false;
            }
        }
    }
}
