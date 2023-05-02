package service;

import model.Operation;

public interface OperationObserverService {

    void processOperation(Operation operation);

    void reset();
}
