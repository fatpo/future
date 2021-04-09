package lift;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClosingState extends LiftState {
    public ClosingState(Lift lift) {
        super(lift);
    }

    @Override
    public void open() {
        mLift.setState(mLift.getOpeningState());
        mLift.open();
    }

    @Override
    public void close() {
        log.info("电梯已经是关闭状态啦...");
    }

    @Override
    public void run() {
        mLift.setState(mLift.getRunningState());
        mLift.run();
    }

    @Override
    public void stop() {
        // 有些电梯是这样子的，先关门后，等待调度，所以这个时候是stop状态...
        mLift.setState(mLift.getStoppingState());
        mLift.stop();
    }

}
