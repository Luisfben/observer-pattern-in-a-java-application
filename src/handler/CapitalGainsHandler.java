package handler;

import model.Operation;
import service.OperationObserverService;

import java.util.ArrayList;

public class CapitalGainsHandler {
    private ArrayList<OperationObserverService> operationObserverList = new ArrayList<>();

    public void subscribe(OperationObserverService operationObserver) {
        this.operationObserverList.add(operationObserver);
    }

    public void unsubscribe(OperationObserverService operationObserver) {
        this.operationObserverList.remove(operationObserver);
    }

    public void handleOperation(Operation operation) {
        this.operationObserverList.stream().forEach(operationObserver -> operationObserver.processOperation(operation));
    }
}
