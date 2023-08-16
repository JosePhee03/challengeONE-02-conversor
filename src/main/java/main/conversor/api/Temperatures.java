package main.conversor.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

// Celsius (°C)
// Fahrenheit (°F)
// Kelvin (K)
// Rankine (°R)
public class Temperatures implements Converter {

    private final List<ConversionRates> temperatures = new ArrayList<>();
    public Temperatures() {

        HashMap<String, DoubleUnaryOperator> conversionCelsius = new HashMap<>();
        ConversionRates celsius = new ConversionRates("Celsius", conversionCelsius);
        conversionCelsius.put("Celsius", c -> c);
        conversionCelsius.put("Fahrenheit", c -> (c * 9/5) + 32);
        conversionCelsius.put("Kelvin", c -> c + 273.15);
        conversionCelsius.put("Rankine", c -> c * 9/5 + 491.67);

        HashMap<String, DoubleUnaryOperator> conversionFahrenheit = new HashMap<>();
        ConversionRates fahrenheit = new ConversionRates("Fahrenheit", conversionFahrenheit);
        conversionFahrenheit.put("Celsius", f -> (f - 32) * 5/9);
        conversionFahrenheit.put("Fahrenheit", f -> f);
        conversionFahrenheit.put("Kelvin", f -> (f + 459.67) * 5/9);
        conversionFahrenheit.put("Rankine", f -> f + 459.67);

        HashMap<String, DoubleUnaryOperator> conversionKelvin = new HashMap<>();
        ConversionRates kelvin = new ConversionRates("Kelvin", conversionKelvin);
        conversionKelvin.put("Celsius", k -> k - 273.15);
        conversionKelvin.put("Fahrenheit", k -> k * 9/5 - 459.67);
        conversionKelvin.put("Kelvin", k -> k);
        conversionKelvin.put("Rankine", k -> k * 9/5);

        HashMap<String, DoubleUnaryOperator> conversionRankine = new HashMap<>();
        ConversionRates rankine = new ConversionRates("Rankine", conversionRankine);
        conversionRankine.put("Celsius", r -> (r - 491.67) * 5/9);
        conversionRankine.put("Fahrenheit", r -> r - 459.67);
        conversionRankine.put("Kelvin", r -> r * 5/9);
        conversionRankine.put("Rankine", r -> r);

        this.temperatures.add(celsius);
        this.temperatures.add(fahrenheit);
        this.temperatures.add(kelvin);
        this.temperatures.add(rankine);
    }

    @Override
    public Double converter(String from, String to, Double value) {
        double converterValue = 0.0;
        for (ConversionRates temperature : this.temperatures) {
            if (temperature.name().equals(from)) {
                if (temperature.conversionRates().containsKey(to)) {
                    DoubleUnaryOperator converterTo = temperature.conversionRates().get(to);
                    converterValue = converterTo.applyAsDouble(value);
                }
            }
        }
        return converterValue;
    }

    @Override
    public List<ConversionRates> getAll() {
        return this.temperatures;
    }
}
