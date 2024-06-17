package vl.example.accountsrest.external;

import java.util.List;

public interface ExternalClient {
    List<CoinModel> getExternalData();
}

