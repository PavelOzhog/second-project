package data;

import lombok.Data;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;




@Data
public class DataFormReg {
    String FIO;
    String date;
    String town;
String passSeriesPar;


    public DataFormReg(String FIO, String date, String town, String passSeriesPar) {
        this.FIO = FIO;
        this.date = date;
        this.town = town;
        this.passSeriesPar = passSeriesPar;
    }
}
