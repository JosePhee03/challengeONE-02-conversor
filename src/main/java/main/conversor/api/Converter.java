package main.conversor.api;

import java.util.Collection;
import java.util.List;

public interface Converter {
    Double converter(String from, String to, Double value);

    List<ConversionRates> getAll ();
}
