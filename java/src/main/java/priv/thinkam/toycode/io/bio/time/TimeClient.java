package priv.thinkam.toycode.io.bio.time;

import java.io.*;
import java.net.Socket;

/**
 * @author thinkam
 * @date 2018/09/04
 */
public class TimeClient {
	public static void main(String[] args) {
		try (Socket socket = new Socket(Common.SERVER_ID_ADDRESS, Common.SERVER_PORT);
			 BufferedReader bufferedReader =
					 new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)
		) {
			printWriter.println(Common.QUERY_TIME_ORDER);
			System.out.println("an order has sent to server...");
			System.out.println("receive response - " + bufferedReader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
