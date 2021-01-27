/**
 * 栈上分配： 逃逸分析 + 标量替换，性能差距50倍
 * jdk1.8 默认开启： -XX:+DoEscapeAnalysis -XX:+EliminateAllocations
 */
public class StackAllocationDemo {
    public static void main(String[] args) {
        long t1 = System.currentTimeMillis();
        StackAllocationDemo demo = new StackAllocationDemo();
        for (int i = 0; i < 100000000; i++) {
            demo.haha();
        }
        long t2 = System.currentTimeMillis();
        System.out.println("耗时：" + (t2 - t1) + "ms");
    }

    private void haha() {
        User user = new User();
        user.age = 19;
        user.name = "fatpo";
    }
}

