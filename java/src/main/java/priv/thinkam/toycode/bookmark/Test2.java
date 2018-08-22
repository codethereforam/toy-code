package priv.thinkam.toycode.bookmark;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 去除test2.txt重复的行
 *
 * @author thinkam
 * @date 2018/08/22
 */
public class Test2 {

	public static void main(String[] args) {
		File file = new File(Test1.class.getResource("").getFile() + "test2.txt");
		if (!file.exists()) {
			System.out.println(file.getName() + " not exist");
			return;
		}
		File targetFile = new File("/home/thinkam/Downloads/result.txt");
		if (!targetFile.exists()) {
			boolean success;
			try {
				success = targetFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("create fail");
				return;
			}
			if (!success) {
				System.out.println("create fail");
				return;
			}
		}

		Set<String> subLineSet = new HashSet<>();
		try (BufferedReader br = new BufferedReader(new FileReader(file));
			 BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))
		) {
			String line;
			while ((line = br.readLine()) != null) {
				boolean existent = false;
				if (line.length() >= 5) {
					String subLine = line.substring(0, 5);
					if (subLineSet.remove(subLine)) {
						existent = true;
					} else {
						subLineSet.add(subLine);
					}
				}

				if (!existent) {
					System.out.println(line);
					bw.write(line);
					bw.newLine();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
