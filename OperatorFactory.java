import Operations.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OperatorFactory {
    static Map<String, Operation> operationMap = new HashMap<>();
    static {
        operationMap.put("+", new Addition());
        operationMap.put("-", new Subtraction());
        operationMap.put("*", new Multiply());
        operationMap.put("/", new Division());
    }

    public static Optional<Operation> getOperation(String operator) {
        return Optional.ofNullable(operationMap.get(operator));
    }
}