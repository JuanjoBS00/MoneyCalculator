
package moneycalculator.persistence;

import java.util.List;
import moneycalculator.model.Currency;

/**
 *
 * @author Juanjo
 */
public interface CurrencyListLoader {
    public List<Currency> currencies();
    
}
