package lift;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunningState extends LiftState {
    public RunningState(Lift lift) {
        super(lift);
    }

    @Override
    public void open() {
        throw new IllegalThreadStateException("运行中不可以电梯开门!!");
    }

    @Override
    public void close() {
        throw new IllegalThreadStateException("运行中不可以电梯关门，因为它已经是关闭状态了!!");
    }

    @Override
    public void run() {
        log.info("电梯正在运行...");
    }

    @Override
    public void stop() {
        throw new IllegalThreadStateException("运行中不可以电梯停止!!");
    }


}
