package vl.example.accountsrest.external;


import vl.example.accountsrest.entity.Coin;
import vl.example.accountsrest.entity.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoinModel {
    private String id;
    private String symbol;
    private String name;
    private String nameid;
    private Integer rank;
    private Float price_usd;
    private Float percent_change_24h;
    private Float percent_change_1h;
    private Float percent_change_7d;
    private String market_cap_usd;
    private Float volume24;
    private Float volume24a;
    private String csupply;
    private String price_btc;
    private String tsupply;
    private String msupply;

    public Coin toCoin() {
        Coin coin = new Coin();
        coin.setCode(this.getId());
        coin.setName(this.getName());
        coin.setPrice((float) (Math.round(this.getPrice_usd()*100)/100.00));
        coin.setChange1h(this.getPercent_change_1h());
        coin.setChange24h(this.getPercent_change_24h());
        coin.setChange7d(this.getPercent_change_7d());
        coin.setUpdatedAt(LocalDateTime.now());
        coin.setStatus(Status.ACTIVE);

        return coin;
    }
}


