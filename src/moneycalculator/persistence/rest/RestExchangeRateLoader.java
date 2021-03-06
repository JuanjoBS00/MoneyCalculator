package moneycalculator.persistence.rest;

import moneycalculator.persistence.ExchangeRateLoader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import moneycalculator.model.Currency;
import moneycalculator.model.ExchangeRate;

public class RestExchangeRateLoader implements ExchangeRateLoader {

    @Override
    public ExchangeRate load(Currency from, Currency to) {
        try {
            return new ExchangeRate(from,to, read(from.getCode(), to.getCode()));
        } catch (MalformedURLException ex) {
            Logger.getLogger(RestExchangeRateLoader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RestExchangeRateLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private double read (String from, String to) throws IOException {
       String line = read(new URL("http://data.fixer.io/api/latest?access_key=fd1c12be2fb99cd3e070448f81731cb4&symbols="+from+","+to));
       return Double.parseDouble(line.substring(line.indexOf(from)+5, line.lastIndexOf(",")));
    }

    private String read(URL url) throws IOException {
        InputStream is = url.openStream();
        byte[] buffer = new byte[1024];
        int length = is.read(buffer);
        return new String(buffer,0,length);
    }
}


