package priv.thinkam.toycode.io.bio.time;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author thinkam
 * @date 2018/09/04
 */
public class TimeServer {

	public static void main(String[] args) {
		ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), 50, 120L,
				TimeUnit.SECONDS, new ArrayBlockingQueue<>(10000));
		try (ServerSocket serverSocket = new ServerSocket(Common.SERVER_PORT)) {
			System.out.println("server start...");
			Socket socket;
			while (true) {
				socket = serverSocket.accept();
				System.out.println("a client connected - " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort());
				executorService.execute(new Handler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static class Handler implements Runnable {
		private Socket socket;

		Handler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			try (BufferedReader bufferedReader =
						 new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				 PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream()), true)
			) {
				String order = bufferedReader.readLine();
				System.out.println("server receive order: " + order);
				String result = Common.QUERY_TIME_ORDER.equals(order) ?
						"server time: " + new Date() : Common.BAD_ORDER_MESSAGE;
				printWriter.println(result);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
