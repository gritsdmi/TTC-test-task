import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Application {

    private final String[] arguments;
    private final Parser parser;
    private Logic logic;

    public Application(String[] arguments) {
        this.arguments = arguments;
        this.parser = new Parser();
    }

    void start() {

        System.err.println("arguments " + Arrays.toString(arguments));
        if (arguments.length < 1) {
            System.err.println("There are not parameters");
            return;
        }

        List<String> dataFromParser = parseInput();

        Logic logic = new Logic(dataFromParser);
        List<Integer> result = logic.getResultedData();

        //in case if 2 args received
        if (arguments.length > 1) {
            try {
                writeToFile(result.stream().map(Object::toString).collect(Collectors.toList()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            result.forEach(System.out::println);
        }
    }

    private List<String> parseInput() {
        List<String> dataFromParser = new ArrayList<>();
        try {
            int i = Integer.parseInt(arguments[0]);

            if (i > 0) {
                dataFromParser = parser.parseDataFromStdInput();
            }
            return dataFromParser;

        } catch (NumberFormatException e) {
            if (new File(arguments[0]).exists()) {
                dataFromParser = parser.parseFromFile(new File(arguments[0]));
            } else {
                System.err.println("Wrong file path");
            }
            return dataFromParser;
        }
    }

    private void writeToFile(final List<String> data) throws IOException {
        File output = new File(arguments[1]);
        if (output.createNewFile()) {
            FileWriter writer = (new FileWriter(output));
            data.forEach(item -> {
                try {
                    writer.write(item);
                    writer.write('\n');
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
        } else {
            System.err.println("File with this name already exist");
        }
    }

}
