package priv.thinkam.toycode.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author thinkam
 * @date 2018/09/07
 */
public class TransferTest {
	public static void main(String[] args) {
		try (RandomAccessFile fromFile = new RandomAccessFile("/home/thinkam/DATA/IDEA/toy-code/java/src/main/java" +
				"/priv/thinkam/toycode/io/nio/TransferTest.java", "rw");
			 RandomAccessFile toFile = new RandomAccessFile("/home/thinkam/DATA/IDEA/toy-code/java/src/main/java/priv" +
					 "/thinkam/toycode/io/nio/TransferTest.java.bak.1", "rw")
		) {
			FileChannel fromChannel = fromFile.getChannel();
			toFile.getChannel().transferFrom(fromChannel, 0, fromChannel.size());
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (RandomAccessFile fromFile = new RandomAccessFile("/home/thinkam/DATA/IDEA/toy-code/java/src/main/java" +
				"/priv/thinkam/toycode/io/nio/TransferTest.java", "rw");
			 RandomAccessFile toFile = new RandomAccessFile("/home/thinkam/DATA/IDEA/toy-code/java/src/main/java/priv" +
					 "/thinkam/toycode/io/nio/TransferTest.java.bak.2", "rw")
		) {
			FileChannel fromChannel = fromFile.getChannel();
			fromChannel.transferTo(0, fromChannel.size(), toFile.getChannel());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
