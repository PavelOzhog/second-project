package data;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

public class AggregateDataFormReg implements ArgumentsAggregator {

    @Override
    public DataFormReg aggregateArguments(ArgumentsAccessor argAc, ParameterContext parameterContext) throws ArgumentsAggregationException {
        return new DataFormReg(argAc.getString(0),argAc.getString(1),argAc.getString(2),argAc.getString(3));
    }
}
