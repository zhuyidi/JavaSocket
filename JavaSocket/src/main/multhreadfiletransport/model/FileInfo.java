package multhreadfiletransport.model;

/**
 * Created by dela on 1/23/18.
 */

// 一个文件的基本信息, 包括文件名和文件长度
public class FileInfo {
    private String fileName;
    private int fileLen;

    public FileInfo() { }

    public FileInfo(String fileName, int fileLen) {
        this.fileName = fileName;
        this.fileLen = fileLen;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }
}
