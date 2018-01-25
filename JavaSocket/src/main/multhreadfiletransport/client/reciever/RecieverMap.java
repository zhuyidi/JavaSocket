package multhreadfiletransport.client.reciever;

import multhreadfiletransport.model.RecieverSimpleInfo;
import java.util.Map;


/**
 * Created by dela on 1/25/18.
 */
public class RecieverMap {
    private Map<String, RecieverSimpleInfo> fileMap;

    public RecieverMap() { }

    public RecieverMap(Map<String, RecieverSimpleInfo> fileMap) {
        this.fileMap = fileMap;
    }

    public Map<String, RecieverSimpleInfo> getFileMap() {
        return fileMap;
    }

    public void setFileMap(Map<String, RecieverSimpleInfo> fileMap) {
        this.fileMap = fileMap;
    }
}
