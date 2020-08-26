import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    private final static String DELIMITER = " ";
    private final List<String> inputData;

    public Parser() {
        this.inputData = new ArrayList<>();
    }

    List<String> parseDataFromStdInput() {
        try {
            return parseData(new BufferedReader(new InputStreamReader(System.in)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    List<String> parseFromFile(File file) {
        try {
            return parseData(new BufferedReader(new FileReader(file)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<String> parseData(BufferedReader bufferedReader) throws IOException {
        String tempLine;

        while ((tempLine = bufferedReader.readLine()) != null) {
            tempLine = tempLine.trim().replaceAll(" +", " ");

            String[] temp = tempLine.split(DELIMITER);
            inputData.addAll(Arrays.asList(temp));
        }

        inputData.removeIf(s -> s.equals(""));
        return inputData;
    }
}
