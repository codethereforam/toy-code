package priv.thinkam.toycode.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimerTest3 {
	public static void main(String[] args) {
		Runnable runnable = () -> {
			System.out.println("Hello !!");
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(runnable, 2, 1, TimeUnit.SECONDS);
//		service.schedule(runnable, 2, TimeUnit.SECONDS);
	}
}
