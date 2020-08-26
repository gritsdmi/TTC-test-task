import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Logic {
    private final List<String> inputData;
    private List<Integer> resultedData;

    public Logic(List<String> data) {
        this.inputData = data;
    }

    private boolean checkNumbers() {
        //TODO what if digits and not digits will be mixed?
        try {
            resultedData = inputData.stream().map(Integer::parseInt).collect(Collectors.toList());
            return true;
        } catch (NumberFormatException e) {
            System.err.println("Some numbers are not integer");
        }
        return false;
    }

    private void filter() {
        if (resultedData.size() % 2 == 0) {
            resultedData.removeIf(item -> (item % 2) != 0);
        } else {
            resultedData.removeIf(item -> (item % 2) == 0);
        }
    }

    public List<Integer> getResultedData() {
        if (checkNumbers()) {
            filter();
            return resultedData;
        }
        return new LinkedList<>();
    }

}
