package priv.thinkam.toycode.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author thinkam
 * @date 2018/09/05
 */
public class FileChannelTest {
	public static void main(String[] args) {
		try (RandomAccessFile randomAccessFile = new RandomAccessFile("/home/thinkam/DATA/IDEA/toy-code/java/src/main/java" +
				"/priv/thinkam/toycode/io/nio/FileChannelTest.java", "rw")) {
			FileChannel inChannel = randomAccessFile.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(48);
			while (inChannel.read(byteBuffer) != -1) {
				byteBuffer.flip();
				while (byteBuffer.hasRemaining()) {
					System.out.print((char) byteBuffer.get());
				}
				byteBuffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
