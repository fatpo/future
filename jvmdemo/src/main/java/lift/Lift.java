package lift;

public class Lift {
    // 定义出电梯的所有状态
    private LiftState openingState;
    private LiftState closingState;
    private LiftState runningState;
    private LiftState stoppingState;

    // 定义当前电梯状态
    private LiftState mCurState;

    public Lift() {
        openingState = new OpeningState(this);
        closingState = new ClosingState(this);
        runningState = new RunningState(this);
        stoppingState = new StoppingState(this);
    }

    public void open() {
        mCurState.open();
    }

    public void close() {
        mCurState.close();
    }

    public void run() {
        mCurState.run();
    }

    public void stop() {
        mCurState.stop();
    }

    public void setState(LiftState state) {
        this.mCurState = state;
    }

    public LiftState getOpeningState() {
        return openingState;
    }

    public LiftState getClosingState() {
        return closingState;
    }

    public LiftState getRunningState() {
        return runningState;
    }


    public LiftState getStoppingState() {
        return stoppingState;
    }
}
