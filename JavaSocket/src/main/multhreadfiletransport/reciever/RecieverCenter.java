package multhreadfiletransport.reciever;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by dela on 1/23/18.
 */
// 接收中心
// 接收端兼客户端, 建立两个Socket, 既与Server通信, 又与多个Sender通信
// 与Server通信: 接收所要发送的文件的文件信息列表(可能是一个文件, 也可能是多个文件, Server负责发送这些信息)
// 与Sender通信: 负责接收每个分片文件的内容

// 接收中心首先收到文件的信息, 然后开启接收服务器, 在接收服务器中开启接收线程
public class RecieverCenter {
    private Socket socket;
    private BufferedInputStream inputStream;

    public RecieverCenter() { }

    public RecieverCenter(Socket socket) {
        this.socket = socket;
    }

    public RecieverCenter(String host, int port) {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        inputStream = new BufferedInputStream(socket.getInputStream());
        // 先接收8个字节的长度, 解析出Json串

        byte[] buffer = new byte[8];

        int readSize = 0;
        while (readSize != 8) {
            readSize += inputStream.read(buffer);
        }


    }
}
