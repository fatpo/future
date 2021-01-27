import java.util.ArrayList;
import java.util.List;

/**
 * 配合 jvisualvm 看 新生代eden、from、to对象流转
 * -Xms20m -Xmn10m -Xmx20m
 */
public class GCDemo {
    byte[] a = new byte[1024 * 100]; // 100KB

    public static void main(String[] args) throws InterruptedException {
        List<GCDemo> haha = new ArrayList<>();
        while (true) {
            haha.add(new GCDemo());
            Thread.sleep(1000);
        }
    }
}
