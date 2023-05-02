package impl;

import model.Operation;
import service.OperationObserverService;

public class CurrentStockQuantityPreviousObserverImpl implements OperationObserverService {

    private static int currentStockQuantityPrevious = -1;
    private int previousQuantity;
    private String previousOperation;

    @Override
    public void processOperation(Operation operation) {
        if (currentStockQuantityPrevious == -1) {
            currentStockQuantityPrevious = 0;
        } else if (previousOperation.equals("buy")) {
            currentStockQuantityPrevious = currentStockQuantityPrevious + previousQuantity;
        } else if (previousOperation.equals("sell")) {
            currentStockQuantityPrevious = currentStockQuantityPrevious - previousQuantity;
        }
        previousQuantity = operation.getQuantity();
        previousOperation = operation.getOperation();
    }

    public static int getCurrentStockQuantityPrevious() {
        return currentStockQuantityPrevious;
    }

    @Override
    public void reset() {
        currentStockQuantityPrevious = -1;
        previousQuantity = 0;
        previousOperation = null;
    }
}
