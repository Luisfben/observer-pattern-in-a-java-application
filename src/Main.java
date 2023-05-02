import com.google.gson.Gson;
import handler.CapitalGainsHandler;
import impl.*;
import model.Operation;
import service.OperationObserverService;

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();
        Operation[] operations = gson.fromJson(args[0], Operation[].class);

        OperationObserverService currentStockQuantityPrevious = new CurrentStockQuantityPreviousObserverImpl();
        OperationObserverService weightedAverage = new WeightedAverageObserverImpl();
        OperationObserverService overallProfit = new OverallProfitObserverImpl();
        OperationObserverService taxesPayable = new TaxesPayableObserverImpl();

        CapitalGainsHandler capitalGainsHandler = new CapitalGainsHandler();
        capitalGainsHandler.subscribe(currentStockQuantityPrevious);
        capitalGainsHandler.subscribe(weightedAverage);
        capitalGainsHandler.subscribe(overallProfit);
        capitalGainsHandler.subscribe(taxesPayable);

        for (Operation operation : operations){
            capitalGainsHandler.handleOperation(operation);
        }

        System.out.println(new Gson().toJson(TaxesPayableObserverImpl.getTaxList()));
    }
}
