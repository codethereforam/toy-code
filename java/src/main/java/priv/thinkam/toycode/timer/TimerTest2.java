package priv.thinkam.toycode.timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerTest2 {
	public static void main(String[] args) {
		TimerTask task1 = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Hello !!!");
			}
		};
		TimerTask task2 = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Hi !!!");
				try {
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Timer timer = new Timer();
		int intervalPeriod = 1000;
		timer.scheduleAtFixedRate(task1, 1000, intervalPeriod);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.scheduleAtFixedRate(task2, 2000, intervalPeriod);

		try {
			TimeUnit.SECONDS.sleep(8);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		timer.cancel();
//		timer.schedule(task, 1000);
	}
}
