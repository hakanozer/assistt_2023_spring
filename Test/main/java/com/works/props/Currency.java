package com.works.props;

public class Currency {

    private String currencyName;
    private String forexSelling;

    public Currency(String currencyName, String forexSelling) {
        this.currencyName = currencyName;
        this.forexSelling = forexSelling;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getForexSelling() {
        return forexSelling;
    }

    public void setForexSelling(String forexSelling) {
        this.forexSelling = forexSelling;
    }
}
