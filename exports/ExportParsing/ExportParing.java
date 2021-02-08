
import edu.duke.*;
import org.apache.commons.csv.*;

public class ExportParing {

  //1st PHASE
    void TestExports()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        //1st qs
        String countryInfo = CountryInfo(parser, "Nauru");
        System.out.println(countryInfo);
        //Reseting parser.
        //2nd qs 
        parser = fr.getCSVParser();
        ListExportersTwoProducts(parser,"cotton", "flowers");
        //3rd qs
        parser = fr.getCSVParser();
        int exporterCounts = NumberOfExporters(parser, "cocoa");
        System.out.println("cocoa sold countries' counts are " + exporterCounts);
        //4th qs
        parser = fr.getCSVParser();
        BigExporters(parser, "$999,999,999,999");
        
        
    }
    
    
    //Returns Countries info in csv files(name, exports, value(dolars))
    String CountryInfo (CSVParser parser, String country)
    {
        String returnValue = "NOT FOUND";
        for(CSVRecord record : parser )
        {
            String recordCountry = record.get("Country");
            if( recordCountry.equals(country) )
            {
                returnValue = recordCountry + " : " + record.get("Exports") + " : " + record.get("Value (dollars)");
            }
        }
        
        return returnValue;
    }
    
    //Prints the names of all the countries that have both exportItem1 and exportItem2 as export items.
    void ListExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2)
    {
        for(CSVRecord record : parser)
        {
            String exports = record.get("Exports");
            if ( exports.contains(exportItem1) && exports.contains(exportItem2) )
            {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    //This method returns the number of countries that export exportItem. 
    int NumberOfExporters( CSVParser parser, String exportItem) 
    {
        int counter = 0;
        for(CSVRecord record : parser)
        {
            if ( record.get("Exports").contains(exportItem) )
                counter++;
        }
        return counter ;
    }
    
    //This method prints the names of countries and their Value amount for all countries whose Value (dollars) string is longer than the amount string. 
    void BigExporters ( CSVParser parser, String amountOfDollar)
    {
        for(CSVRecord record : parser)
        {
            if ( record.get("Value (dollars)").length() > amountOfDollar.length() )
                System.out.println( record.get("Country") + " " + record.get("Value (dollars)") );
            
        }
    }
       
}