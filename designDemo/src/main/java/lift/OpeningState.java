package lift;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OpeningState extends LiftState {
    public OpeningState(Lift lift) {
        super(lift);
    }

    @Override
    public void open() {
        log.info("电梯正在打开...");
    }

    @Override
    public void close() {
        mLift.setState(mLift.getClosingState());
        mLift.close();
    }

    @Override
    public void run() {
        throw new IllegalThreadStateException("电梯打开状态下，不可以让电梯运行!!");
    }

    @Override
    public void stop() {
        throw new IllegalThreadStateException("电梯打开状态下，本来就是停止，哪能让电梯停止!!");
    }


}
