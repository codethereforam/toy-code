package priv.thinkam.toycode.io;

import java.io.File;
import java.util.Objects;

/**
 * @author yanganyu
 * @date 2021/7/12 23:29
 */
public class FindFileInDir {
    public static final String PATH = "";
    public static final String NAME_PATTERN = ".m4a";

    public static void main(String[] args) {
        File file = new File(PATH);
        deleteByNamePattern(file);
    }

    static void deleteByNamePattern(File file) {
        if (file.isDirectory())  {
            for (File f : Objects.requireNonNull(file.listFiles())) {
                System.out.println(f.getName() + "," + f.isDirectory());
                if (f.getName().contains(NAME_PATTERN)) {
                    System.out.println("----delete:" + f.getName());
                    f.delete();
                }
                if (f.isDirectory()) {
                    deleteByNamePattern(f);
                }
            }
        } else {
            throw new AssertionError("file is not dir");
        }
    }
}
