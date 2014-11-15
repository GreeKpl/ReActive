package pl.edu.agh.inz.reactive.games.finish.criteria;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by alek on 15.11.14.
 */
public class TimeBasedFinishCriteria extends FinishCriteria {

    ScheduledThreadPoolExecutor timer;

    public TimeBasedFinishCriteria(int seconds) {
        timer = new ScheduledThreadPoolExecutor(1);

        timer.schedule(new Runnable() {
            @Override
            public void run() {
                timer.shutdownNow();
                finishListener.onFinish();
            }
        }, seconds, TimeUnit.SECONDS);
    }


    @Override
    public void onScoreChange(int score) {
        // do nothing, score doesn't matter
    }

    @Override
    public void destroy() {
        timer.shutdownNow();
    }
}
