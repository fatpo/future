package lift;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StoppingState extends LiftState {
    public StoppingState(Lift lift) {
        super(lift);
    }

    @Override
    public void open() {
        mLift.setState(mLift.getOpeningState());
        mLift.open();
    }

    @Override
    public void close() {
        throw new IllegalThreadStateException("停止的电梯不需要关门，因为它已经是关闭状态了!!");
    }

    @Override
    public void run() {
        mLift.setState(mLift.getRunningState());
        mLift.run();
    }

    @Override
    public void stop() {
        log.info("电梯停止运行...");
    }


}
