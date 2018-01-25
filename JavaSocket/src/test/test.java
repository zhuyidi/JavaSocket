import multhreadfiletransport.util.PackageUtil;
import multhreadfiletransport.util.ParseUtil;

import java.lang.*;

/**
 * Created by dela on 1/25/18.
 */
public class test {
    public static void main(String[] args) {
        String filename = "0000123";
        PackageUtil.addHeader(filename);

        byte[] temp = filename.getBytes();

        System.out.println(ParseUtil.getByteStrLen(temp));

    }
}
