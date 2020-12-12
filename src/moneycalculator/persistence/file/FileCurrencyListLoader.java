package moneycalculator.persistence.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import moneycalculator.model.Currency;
import moneycalculator.persistence.CurrencyListLoader;

public class FileCurrencyListLoader implements CurrencyListLoader{
    private final String fileName;

    public FileCurrencyListLoader(String fileName) {
        this.fileName = fileName;
    }
    
    
    @Override
    public List<Currency> currencies() {
        List<Currency> list = new ArrayList<>();
        try {
        BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
        
            while(true){
                String line = reader.readLine();
                if(line == null) break;
                list.add(currencyOf(line));
            }
        
        } catch(FileNotFoundException exception){
            System.out.println("ERROR MailListReader::read (File not Found)" + exception.getMessage());
        } catch (IOException exception){
            System.out.println("ERROR MailListReader::read (IO)" + exception.getMessage());
        }
        
        
        return list;
    }

    private Currency currencyOf(String line) {
        String[] split = line.split(",");
        return new Currency(split[0],split[1],split[2]);
    }

}
