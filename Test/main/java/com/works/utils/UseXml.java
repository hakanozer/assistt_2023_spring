package com.works.utils;

import com.works.props.Currency;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class UseXml {

    public List<Currency> xml() {
        List<Currency> currencies = new ArrayList<>();
        try {
            String url = "https://www.tcmb.gov.tr/kurlar/today.xml";
            String data = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get().toString();
            Document doc = Jsoup.parse(data, Parser.xmlParser());
            Elements elements = doc.getElementsByTag("Currency");
            for(Element item : elements) {
                String currencyName = item.getElementsByTag("CurrencyName").text();
                String forexSelling = item.getElementsByTag("ForexSelling").text();
                Currency currency = new Currency(currencyName, forexSelling);
                currencies.add(currency);
            }
        }catch (Exception ex) {
            System.err.println("xml error : " + ex);
        }
        return  currencies;
    }

}
