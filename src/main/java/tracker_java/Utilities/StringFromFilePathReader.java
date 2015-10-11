package tracker_java.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by cristian on 10/4/15.
 */
public class StringFromFilePathReader {

    private String filePath;

    public StringFromFilePathReader(String filePath) {
        this.filePath = filePath;
    }

    public String getData() throws IOException {
        try {
            File fileName = new File(this.filePath);
            String file = fileName.getAbsolutePath();

            FileInputStream fis = null;
            fis = new FileInputStream(file);
            char[] data = new char[fis.available()];
            int content;
            int position = 0;
            while ((content = fis.read()) != -1) {
                data[position] = (char) content;
                position++;
            }
            String stringData = new String(data);
            return stringData;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
