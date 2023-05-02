package impl;

import model.Operation;
import service.OperationObserverService;

public class OverallProfitObserverImpl implements OperationObserverService {

    private static double overallProfit;

    private static double deductLoss = 0;

    @Override
    public void processOperation(Operation operation) {
        if (operation.getOperation().equals("sell")) {
            overallProfit = (operation.getUnitCost() * operation.getQuantity()) -
                    (WeightedAverageObserverImpl.getWightedAverage() * operation.getQuantity());
            if (overallProfit < 0) {
                deductLoss = deductLoss + overallProfit;
            }
        }
    }

    public static double getOverallProfit() {
        return overallProfit;
    }

    public static double getDeductLoss() {
        return deductLoss;
    }

    @Override
    public void reset() {
        overallProfit = 0;
        deductLoss = 0;
    }
}
