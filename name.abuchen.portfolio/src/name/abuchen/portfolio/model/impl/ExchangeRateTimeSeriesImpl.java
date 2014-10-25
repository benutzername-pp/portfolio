package name.abuchen.portfolio.model.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import name.abuchen.portfolio.model.ExchangeRate;
import name.abuchen.portfolio.model.ExchangeRateProvider;
import name.abuchen.portfolio.model.ExchangeRateTimeSeries;

public class ExchangeRateTimeSeriesImpl implements ExchangeRateTimeSeries
{
    private transient ExchangeRateProvider provider;

    private String baseCurrency;
    private String termCurrency;
    private List<ExchangeRate> rates = new ArrayList<ExchangeRate>();

    public ExchangeRateTimeSeriesImpl()
    {
        // empty constructor needed for xstream
    }

    public ExchangeRateTimeSeriesImpl(ExchangeRateTimeSeriesImpl template)
    {
        this.provider = template.provider;
        this.baseCurrency = template.baseCurrency;
        this.termCurrency = template.termCurrency;
        this.rates.addAll(template.rates);
    }

    public ExchangeRateTimeSeriesImpl(ExchangeRateProvider provider, String baseCurrency, String termCurrency)
    {
        this.provider = provider;
        this.baseCurrency = baseCurrency;
        this.termCurrency = termCurrency;
    }

    @Override
    public String getBaseCurrency()
    {
        return baseCurrency;
    }

    @Override
    public String getTermCurrency()
    {
        return termCurrency;
    }

    @Override
    public ExchangeRateProvider getProvider()
    {
        return provider;
    }

    @Override
    public List<ExchangeRate> getRates()
    {
        return new ArrayList<ExchangeRate>(rates);
    }

    public void setProvider(ExchangeRateProvider provider)
    {
        this.provider = provider;
    }

    public void setBaseCurrency(String baseCurrency)
    {
        this.baseCurrency = baseCurrency;
    }

    public void setTermCurrency(String termCurrency)
    {
        this.termCurrency = termCurrency;
    }

    /* package */void addRate(ExchangeRate rate)
    {
        int index = Collections.binarySearch(rates, rate);

        if (index < 0)
            rates.add(~index, rate);
        else
            rates.set(index, rate);
    }

    public ExchangeRate getLatest()
    {
        return rates.isEmpty() ? null : rates.get(rates.size() - 1);
    }

}
