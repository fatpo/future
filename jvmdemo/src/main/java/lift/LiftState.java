package lift;

public abstract class LiftState {
    protected Lift mLift;

    public LiftState(Lift lift) {
        this.mLift = lift;
    }

    public abstract void open();
    public abstract void close();
    public abstract void run();
    public abstract void stop();

}


