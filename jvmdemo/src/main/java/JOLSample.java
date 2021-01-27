import org.openjdk.jol.info.ClassLayout;

/**
 * 看对象头
 */
public class JOLSample {
    public static void main(String[] args) {
        ClassLayout layout = ClassLayout.parseInstance(new Object());
        System.out.println(layout.toPrintable());

        System.out.println();

        ClassLayout layout1 = ClassLayout.parseInstance(new int[4]);
        System.out.println(layout1.toPrintable());

        System.out.println();

        ClassLayout layout2 = ClassLayout.parseInstance(new A());
        System.out.println(layout2.toPrintable());

    }

    public static class A {
        int id;
        String name;
        byte b;
        Object o;
    }
}


