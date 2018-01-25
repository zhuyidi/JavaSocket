package multhreadfiletransport.client.reciever;

import multhreadfiletransport.model.FileInfo;
import multhreadfiletransport.model.RecieverSimpleInfo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.List;
import java.util.Scanner;

/**
 * Created by dela on 1/23/18.
 */
// 接收中心
// 接收中心首先收到文件的信息, 然后开启接收服务器, 在接收服务器中开启接收线程
public class RecieverCenter {
    private Socket socket;    // 与server连接的socket
    private BufferedInputStream inputStream;    // 与server连接的socket的输入流
    private List<FileInfo> fileInfoList;    // 从server那里接收到的文件列表
    private RecieverMap recieverMap;    // 存储文件所有信息的map类
    private int senderCount;    // 服务器分配的sender的数量
    private RecieverServer recieverServer;  // RT持续accept, 连接sender, 一旦连接完璧, 立即关闭


    public RecieverCenter() {
        try {
            socket = new Socket("127.0.0.1", 33000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

//    // 模拟接收分片信息(略去解析过程, 直接构造进RecieverSectionInfo对象中)
//    // 1. 目标文件名
//    // 2. 分片文件名
//    // 3. 起始偏移量
//    // 4. 分片长度
//    public void input() {
//        Scanner scanner = new Scanner(System.in);
//
//        for (int i = 0; i < 3; i++) {
//            RecieverSectionInfo sectionInfo =
//                    new RecieverSectionInfo(scanner.next(), scanner.next(), scanner.nextLong(), scanner.nextLong());
//            sectionInfoList.add(sectionInfo);
//        }
//
//    }

    public void start() throws IOException {
        // 连接服务器, 然后接收服务器发来的目标文件列表和服务器分配的sender数量.
        // 但是此时是模拟服务器发送数据, 所有数据从键盘输入,
        // 所以留出recieve接口, 暂时在里面实现从键盘输入, 之后要改为真正从服务器接收数据
        if (null == socket) {
            socket = new Socket("127.0.0.1", 33000);
        }
        recieveFromServer();

        // 将从服务器接收的文件列表信息保存一份List在自己的实例中, 并new出map类, 进行map初始化
        initRecieveMap();

        // new出RecieverServer类, 进行持续监听
        recieverServer = new RecieverServer();
    }

    public void recieveFromServer() {
        inputTargetFileInfo();
    }

    public void inputTargetFileInfo() {
        Scanner scanner = new Scanner(System.in);

        senderCount = scanner.nextInt();
        for (int i = 0; i < 1; i++) {
            FileInfo fileInfo = new FileInfo(scanner.next(), scanner.nextLong());
            fileInfoList.add(fileInfo);
        }
    }

    public void initRecieveMap() {
        recieverMap = new RecieverMap();
        Map<String, RecieverSimpleInfo> fileInfoMap = new HashMap<>();

        for (FileInfo fileInfo : fileInfoList) {
            fileInfoMap.put(fileInfo.getFileName(), new RecieverSimpleInfo(fileInfo.getFileName(), fileInfo.getFileLen()));
        }
    }
}
