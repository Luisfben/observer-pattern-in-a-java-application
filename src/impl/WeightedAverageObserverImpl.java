package impl;

import model.Operation;
import service.OperationObserverService;

public class WeightedAverageObserverImpl implements OperationObserverService {

    private static Double weightedAverage;

    @Override
    public void processOperation(Operation operation) {
        if (operation.getOperation().equals("buy")) {
            if (weightedAverage == null) {
                weightedAverage = operation.getUnitCost();
            } else {
                weightedAverage = ((CurrentStockQuantityPreviousObserverImpl.getCurrentStockQuantityPrevious() * weightedAverage) +
                        (operation.getQuantity() * operation.getUnitCost())) /
                        (CurrentStockQuantityPreviousObserverImpl.getCurrentStockQuantityPrevious() + operation.getQuantity());
            }
        }
    }

    public static Double getWightedAverage() {
        return weightedAverage;
    }

    @Override
    public void reset() {
        weightedAverage = null;
    }
}
