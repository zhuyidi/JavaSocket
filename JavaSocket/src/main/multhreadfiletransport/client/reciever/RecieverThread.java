package multhreadfiletransport.client.reciever;

import multhreadfiletransport.model.RecieverSectionInfo;
import multhreadfiletransport.observer.ISectionInfoListener;
import multhreadfiletransport.observer.ISectionInfoSpeaker;
import multhreadfiletransport.util.ParseUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by dela on 1/23/18.
 */
public class RecieverThread implements Runnable, ISectionInfoSpeaker {
    private Socket socket;
    private BufferedInputStream inputStream;
    private int headerSize;
    private int bufferSize;
    private byte[] buffer;
    private String sectionListInfo;
    private List<RecieverSectionInfo> sectionFileInfoList;

    {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("file-config");
        this.headerSize = Integer.parseInt(resourceBundle.getString("headerSize"));
        this.bufferSize = Integer.parseInt(resourceBundle.getString("bufferSize"));
        buffer = new byte[bufferSize];
    }

    public RecieverThread(Socket socket) {
        this.socket = socket;
        try {
            inputStream = new BufferedInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        // 先接收整个分片文件列表
        // 然后再接收每个分片
        try {
            recieveSectionInfoList();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void recieveSectionInfoList() throws IOException {
        // 接收分片文件列表
        // 先循环接收size个字节, 然后再接收分片文件列表

        byte[] tempInfoLen = new byte[headerSize];
        int temp = 0;
        while (temp != headerSize) {
            temp = inputStream.read(tempInfoLen, temp, headerSize - temp);
        }
        long infoLen = ParseUtil.getByteStrLen(tempInfoLen);

        // 用buffer读分片文件信息
        int readLen = 0;
        temp = 0;
        while (temp != infoLen) {
            readLen = inputStream.read(buffer, 0, bufferSize);
            temp += readLen;
            sectionListInfo += new String(buffer, 0, readLen);
        }

        // 接下来将收到的分片信息进行解析并传给RCenter, 然后整合到RC里的map中
        parseSectionInfo(sectionListInfo);

    }

    public void parseSectionInfo(String sectionListInfo) {
        sectionFileInfoList = ParseUtil.parseStringToSectionInfoList(sectionListInfo);
    }

    @Override
    public void registerListener(ISectionInfoListener listener) {

    }

    @Override
    public void removeListener(ISectionInfoListener listener) {

    }

    @Override
    public void sendInfo() {

    }
}
