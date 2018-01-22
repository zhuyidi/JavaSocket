package test;

/**
 * Created by dela on 1/22/18.
 */
public class test {
    public static void main(String[] args) {
        int len = 3;
        String str = String.valueOf(len + 100000000).substring(1);
        System.out.println(str);
    }
}

class Size {
    private int a;
    private String str;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
