package priv.thinkam.toycode.timer;

public class TimerTest1 {
	public static void main(String[] args) {
		final int timeInterval = 1000;
		new Thread(() -> {
			while (true) {
				System.out.println("Hello !!");
				try {
					Thread.sleep(timeInterval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
