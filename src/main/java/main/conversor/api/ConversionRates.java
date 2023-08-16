package main.conversor.api;

import java.util.HashMap;

public record ConversionRates(String name, HashMap<String, java.util.function.DoubleUnaryOperator> conversionRates) {



}