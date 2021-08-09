package org.ibs.appline;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

import java.time.LocalDate;

public class ConverterToLocalDate implements ArgumentConverter {


    @Override
    public LocalDate convert(Object o, ParameterContext parameterContext) throws ArgumentConversionException {
        if (!(o instanceof String)){
            throw new ArgumentConversionException("не строка");
        }
        try {
         String [] stringArgs =  ((String) o).split("/");
         return  LocalDate.of(Integer.parseInt(stringArgs[0]),Integer.parseInt(stringArgs[1]), Integer.parseInt(stringArgs[2]));
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
