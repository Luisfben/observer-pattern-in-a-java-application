package impl;

import model.Operation;
import model.Result;
import service.OperationObserverService;

import java.util.ArrayList;

public class TaxesPayableObserverImpl implements OperationObserverService {

    private static ArrayList<Result> taxList = new ArrayList<>();

    private static String error;

    @Override
    public void processOperation(Operation operation) {
        Result result = new Result();
        if (error == null) {
            double tax = 0.0;
            if (operation.getOperation().equals("sell")) {
                if (OverallProfitObserverImpl.getOverallProfit() > 20000.0) {
                    tax = ((OverallProfitObserverImpl.getOverallProfit() + OverallProfitObserverImpl.getDeductLoss()) * 20)/100;
                }
            }
            result.setTax(tax);
        } else {
            result.setError(error);
        }
        taxList.add(result);
    }

    public static ArrayList<Result> getTaxList() {
        return taxList;
    }

    @Override
    public void reset() {
        taxList = new ArrayList<>();
        error = null;
    }

    public static String getError() {
        return error;
    }

    public static void setError(String error) {
        TaxesPayableObserverImpl.error = error;
    }
}
