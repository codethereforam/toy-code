package priv.thinkam.toycode.bookmark;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将test1.txt每行页数加17
 * Created by thinkam on 1/25/18.
 */
public class Test1 {

	public static void main(String[] args) {
		File file = new File(Test1.class.getResource("").getFile() + "test1.txt");
		if(!file.exists()) {
			System.out.println(file.getName() + " not exist");
			return;
		}
		File targetFile = new File("/home/thinkam/Downloads/result.txt");
		if(!targetFile.exists()) {
			try {
				targetFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("create fail");
				return;
			}
		}
		try (BufferedReader br = new BufferedReader(new FileReader(file));
			 BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile))
		) {
			String line;
			while ((line = br.readLine()) != null) {
				String result;
				Pattern pattern = Pattern.compile("^(.*\t)(\\d+$)");
				Matcher matcher = pattern.matcher(line);
				if (matcher.find()) {
					int page = Integer.parseInt(matcher.group(2)) + 17;
					result = matcher.group(1) + page;
				} else {
					result = line;
				}
				System.out.println(result);

				bw.write(result);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
