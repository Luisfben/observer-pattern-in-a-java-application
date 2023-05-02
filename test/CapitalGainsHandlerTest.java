import com.google.gson.Gson;
import handler.CapitalGainsHandler;
import impl.*;
import model.Operation;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import service.OperationObserverService;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CapitalGainsHandlerTest {

    private final OperationObserverService currentStockQuantityPrevious = new CurrentStockQuantityPreviousObserverImpl();
    private final OperationObserverService weightedAverage = new WeightedAverageObserverImpl();
    private final OperationObserverService overallProfit = new OverallProfitObserverImpl();
    private final OperationObserverService taxesPayable = new TaxesPayableObserverImpl();

    private CapitalGainsHandler capitalGainsHandler = new CapitalGainsHandler();

    @BeforeEach
    private void initializeSubscribe() {
        currentStockQuantityPrevious.reset();
        weightedAverage.reset();
        overallProfit.reset();
        taxesPayable.reset();

        capitalGainsHandler = new CapitalGainsHandler();
        capitalGainsHandler.subscribe(currentStockQuantityPrevious);
        capitalGainsHandler.subscribe(weightedAverage);
        capitalGainsHandler.subscribe(overallProfit);
        capitalGainsHandler.subscribe(taxesPayable);
    }

    @Test
    public void case1() throws IOException {
        for (Operation operation : getOperations("input-case-1.json")){
            capitalGainsHandler.handleOperation(operation);
        }
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(0).getTax(), 0.0);
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(1).getTax(), 0.0);
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(2).getTax(), 0.0);
    }

    @Test
    public void case2() throws IOException {
        for (Operation operation : getOperations("input-case-2.json")){
            capitalGainsHandler.handleOperation(operation);
        }
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(0).getTax(), 0.0);
        assertEquals(10000.0, TaxesPayableObserverImpl.getTaxList().get(1).getTax(), 0.0);
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(2).getTax(), 0.0);
    }

    @Test
    public void case1_2() throws IOException {
        for (Operation operation : getOperations("input-case-1-2.json")){
            capitalGainsHandler.handleOperation(operation);
        }

        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(0).getTax(), 0.0);
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(1).getTax(), 0.0);
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(2).getTax(), 0.0);
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(3).getTax(), 0.0);
        assertEquals(10000.0, TaxesPayableObserverImpl.getTaxList().get(4).getTax(), 0.0);
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(5).getTax(), 0.0);
    }

    @Test
    public void case3() throws IOException {
        for (Operation operation : getOperations("input-case-3.json")){
            capitalGainsHandler.handleOperation(operation);
        }

        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(0).getTax(), 0.0);
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(1).getTax(), 0.0);
        assertEquals(1000.0, TaxesPayableObserverImpl.getTaxList().get(2).getTax(), 0.0);
    }

    @Test
    public void case4() throws IOException {
        for (Operation operation : getOperations("input-case-4.json")){
            capitalGainsHandler.handleOperation(operation);
        }

        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(0).getTax(), 0.0);
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(1).getTax(), 0.0);
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(2).getTax(), 0.0);
    }

    @Test
    public void case5() throws IOException {
        for (Operation operation : getOperations("input-case-5.json")){
            capitalGainsHandler.handleOperation(operation);
        }

        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(0).getTax(), 0.0);
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(1).getTax(), 0.0);
        assertEquals(0.0, TaxesPayableObserverImpl.getTaxList().get(2).getTax(), 0.0);
        assertEquals(10000.0, TaxesPayableObserverImpl.getTaxList().get(3).getTax(), 0.0);
    }

    private Operation[] getOperations(String fileName) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(fileName));
        java.io.BufferedReader bufferedReader = new java.io.BufferedReader(reader);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        Gson gson = new Gson();
        return gson.fromJson(stringBuilder.toString(), Operation[].class);
    }
}
