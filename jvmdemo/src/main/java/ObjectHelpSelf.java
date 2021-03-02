import java.util.concurrent.TimeUnit;

/**
 * finalize 自救演示
 */
public class ObjectHelpSelf {
    private static ObjectHelpSelf objectHelpSelf = null;

    public static void main(String[] args) throws InterruptedException {
        objectHelpSelf = new ObjectHelpSelf();
        objectHelpSelf = null;
        System.gc();
        //finalize方法的优先级很低，所以睡眠一秒，方便看到效果
        TimeUnit.SECONDS.sleep(1);
        System.out.println("第一次gc后，objectHelpSelf=" + objectHelpSelf);
        objectHelpSelf = null;
        System.gc();
        //finalize方法的优先级很低，所以睡眠一秒，方便看到效果
        TimeUnit.SECONDS.sleep(1);
        System.out.println("第二次gc后，objectHelpSelf=" + objectHelpSelf);
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("进入finalize方法中，对象开始自救");
        //重新赋值
        ObjectHelpSelf.objectHelpSelf = this;
    }
}