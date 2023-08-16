package main.conversor.api;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

// DÓLAR:
// ---------------------USD (United States Dollar)
//EUROS:
// ---------------------EUR (Euro)
//LIBRAS ESTERLINAS:
// --------------------GBP (Great British Pound)
//YEN JAPONÉS:
// -------------------JPY (Japanese Yen)
//WON SUL-COREANO:
// -------------------KRW (South Korean Won)
public class Currencies implements Converter{

    private final List<ConversionRates> currencies = new ArrayList<>();
    public Currencies() {
        HashMap<String, DoubleUnaryOperator> conversionUSD = new HashMap<>();
        ConversionRates USD = new ConversionRates("USD", conversionUSD);
        conversionUSD.put("USD", (n) -> n);
        conversionUSD.put("ARS", (n) -> n * 278.07);
        conversionUSD.put("EUR", (n) -> n * 0.9128);
        conversionUSD.put("GBP", (n) -> n * 0.7857);
        conversionUSD.put("KRW", (n) -> n * 1298.0991);

        HashMap<String, DoubleUnaryOperator> conversionARS = new HashMap<>();
        ConversionRates ARS = new ConversionRates("ARS", conversionARS);
        conversionARS.put("USD", (n) -> n * 0.0036);
        conversionARS.put("ARS", (n) -> n);
        conversionARS.put("EUR", (n) -> n * 0.0033);
        conversionARS.put("GBP", (n) -> n * 0.00284);
        conversionARS.put("KRW", (n) -> n * 4.67701);

        HashMap<String, DoubleUnaryOperator> conversionGBP = new HashMap<>();
        ConversionRates GBP = new ConversionRates("GBP", conversionGBP);
        conversionGBP.put("USD", (n) -> n * 1.28);
        conversionGBP.put("ARS", (n) -> n * 356.14);
        conversionGBP.put("EUR", (n) -> n * 1.16);
        conversionGBP.put("GBP", (n) -> n);
        conversionGBP.put("KRW", (n) -> n * 1663.0);

        HashMap<String, DoubleUnaryOperator> conversionEUR = new HashMap<>();
        ConversionRates EUR = new ConversionRates("EUR", conversionEUR);
        conversionEUR.put("USD", (n) -> n * 1.10);
        conversionEUR.put("ARS", (n) -> n * 307.37);
        conversionEUR.put("EUR", (n) -> n);
        conversionEUR.put("GBP", (n) -> n * 0.86);
        conversionEUR.put("KRW", (n) -> n * 1435.0);

        HashMap<String, DoubleUnaryOperator> conversionKRW = new HashMap<>();
        ConversionRates KRW = new ConversionRates("KRW", conversionKRW);
        conversionKRW.put("USD", (n) -> n * 0.0008);
        conversionKRW.put("ARS", (n) -> n * 0.21);
        conversionKRW.put("EUR", (n) -> n * 0.0);
        conversionKRW.put("GBP", (n) -> n * 0.0006);
        conversionKRW.put("KRW", (n) -> n);

        this.currencies.add(USD);
        this.currencies.add(ARS);
        this.currencies.add(GBP);
        this.currencies.add(EUR);
        this.currencies.add(KRW);
    }

    @Override
    public Double converter(String from, String to, Double value) {
        double converterValue = 0.0;
        for (ConversionRates currency : this.currencies) {
            if (currency.name().equals(from)) {
                if (currency.conversionRates().containsKey(to)) {
                    DoubleUnaryOperator converterTo = currency.conversionRates().get(to);
                    converterValue = converterTo.applyAsDouble(value);
                }
            }
        }
        return converterValue;
    }

    @Override
    public List<ConversionRates> getAll() {
        return this.currencies;
    }
}