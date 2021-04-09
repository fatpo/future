package lift;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LiftDemo {
    public static void main(String[] args) {
        Lift lift = new Lift();
        lift.setState(new ClosingState(lift));
        try {
            log.info("尝试停止");
            lift.stop();
        } catch (Exception e) {
            log.error("", e);
        }
        try {
            log.info("尝试打开");
            lift.open();
        } catch (Exception e) {
            log.error("", e);
        }
        try {
            log.info("尝试运行");
            lift.run();
        } catch (Exception e) {
            log.error("", e);
        }
        try {
            log.info("尝试打开");
            lift.open();
        } catch (Exception e) {
            log.error("", e);
        }
        try {
            log.info("尝试停止");
            lift.stop();
        } catch (Exception e) {
            log.error("", e);
        }

        try {
            log.info("尝试运行");
            lift.run();
        } catch (Exception e) {
            log.error("", e);
        }

        try {
            log.info("尝试关闭");
            lift.close();
        } catch (Exception e) {
            log.error("", e);
        }

        try {
            log.info("尝试停止");
            lift.stop();
        } catch (Exception e) {
            log.error("", e);
        }


    }
}

