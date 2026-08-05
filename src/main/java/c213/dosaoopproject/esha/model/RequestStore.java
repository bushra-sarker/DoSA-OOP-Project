package c213.dosaoopproject.esha.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RequestStore {
    private static final RequestStore INSTANCE = new RequestStore();

    private final ObservableList<Request> allRequests = FXCollections.observableArrayList();

    private RequestStore() {}

    public static RequestStore getInstance() {
        return INSTANCE;
    }

    public void addRequest(Request request) {
        allRequests.add(request);
    }

    public ObservableList<Request> getAllRequests() {
        return allRequests;
    }

    public ObservableList<Request> getPendingRequests() {
        return allRequests.filtered(r -> "Pending".equals(r.getStatus()));
    }
}
