
package findinggeneındna;



public class Gene {
    
    private String dna;
    
    public Gene( String dna)
    {
        this.dna = dna;
    }
    
    public void setDna( String dna )
    {
        this.dna = dna;
    }
    
    public String getDna()
    {
        return dna;
    }
    
    public String FindSimpleGene()
    {
        int startCodonIndex,stopCodonIndex ;
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        startCodonIndex = dna.indexOf(startCodon);
        //We should make end 
        stopCodonIndex = dna.indexOf(stopCodon , startCodonIndex) + 2 ;
       
        if (startCodonIndex == -1 || stopCodonIndex == -1 ) {
            return "";
        }
        return dna.substring(startCodonIndex, stopCodonIndex +1 );
    }
    
    public String FindGene()
    {
        //Converting to uppercase whatever it is.
        String originDna = dna.toUpperCase();
        int startCodonIndex,stopCodonIndex ;
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        startCodonIndex = originDna.indexOf(startCodon);
        //We should make end 
        stopCodonIndex = originDna.indexOf(stopCodon , startCodonIndex) + 2 ;
       
        if (startCodonIndex == -1 || stopCodonIndex == -1 ) {
            return "";
        }
        boolean IsUppercase = dna.equals(dna.toUpperCase());
        boolean IsLowercase = dna.equals(dna.toLowerCase());
        
        if ( IsLowercase ) 
        {   
            originDna = dna.toLowerCase();
        }

        return originDna.substring(startCodonIndex, stopCodonIndex +1 );
    }
    
    //Part 3: Problem Solving with Strings

    public static boolean TwoOccurrences(String subString, String mainString ) 
    {
                                                                 //for passing 1st substring
        int firstIndex = mainString.indexOf(subString) ;
        int secondIndex = mainString.indexOf(subString, firstIndex + 1);
        
        return (firstIndex != -1 && secondIndex != -1 );
    }
    
    public static void TestTwoOccurrences() throws Exception
    {
        boolean test1Actual = TwoOccurrences("by", "A story by Abby Long");
        boolean test1Expected =  true;
        
        Exception exc = new Exception("Actual and expected value is not equal");
        if (test1Actual != test1Expected) 
            throw exc;
        
        boolean test2Actual = TwoOccurrences("a", "banana");
        boolean test2Expected =  true;
        if (test2Actual != test2Expected) 
            throw exc;
        
        boolean test3Actual = TwoOccurrences("atg", "ctgtatgta");
        boolean test3Expected =  false;
        if (test3Actual != test3Expected) 
            throw exc;
        
        
        System.out.println("Test is successfull");
        
    }
    
    
    public static String LastPart(String subString, String mainString)
    {
        int foundIndex = mainString.indexOf(subString);
        if (foundIndex == -1)
            return mainString;
        
        else                                     //passing the substring.
            return mainString.substring(foundIndex + subString.length());
    }
    
    public static void TestLastPart()
    {
        String actualValue1 =  LastPart("an", "banana");
        String expectedValue1 = "ana";
        //if it not passed
        if ( !actualValue1.equals(expectedValue1) ) 
            System.out.println(" " + actualValue1 + " is wrong");
        else   
            System.out.println(" " + actualValue1 + " is passed");
        
        
        String actualValue2 =  LastPart("zoo", "forest");
        String expectedValue2 = "forest";
        //if it not passed
        if ( !actualValue2.equals(expectedValue2) ) 
              System.out.println(" " + actualValue2 + " is wrong");
        else   
            System.out.println(" " + actualValue2 + " is passed");
        
    }
    
    
    //Part3 END

    
    //Part 5: FINDING ALL GENES
    
    
    public static int FindStopCodonIndex(String dna, int startIndex, String stopCodon )
    {
        int endIndex = dna.indexOf(stopCodon , startIndex + 3);
        
        while(endIndex != -1 )
        {
            if ( (endIndex - startIndex) % 3 == 0) 
                break;
            
            endIndex = dna.indexOf(stopCodon, endIndex + 2);
            
        }
        
        return endIndex;
        
    }
    
    
    //Find genes using advances algorithm.
    public String FindAdvGenes( int startLocation)
    {
        int startIndex = dna.indexOf("ATG",startLocation);
        int endIndexTAA = FindStopCodonIndex(dna, startIndex , "TAA");
        int endIndexTAG = FindStopCodonIndex(dna, startIndex , "TAG");
        int endIndexTGA = FindStopCodonIndex(dna, startIndex , "TGA");
        
        if (endIndexTAA == -1 && endIndexTAG == -1 && endIndexTGA == -1 ) 
            return ""; 
        
        if (endIndexTAA == -1  )
             endIndexTAA = Integer.MAX_VALUE;
        if (endIndexTAG == -1  )
             endIndexTAG = Integer.MAX_VALUE;
        if (endIndexTGA == -1  )
             endIndexTGA = Integer.MAX_VALUE;
        
        int endIndex = Math.min(endIndexTAA, Math.min(endIndexTAG, endIndexTGA));
        
        return dna.substring(startIndex, endIndex + 3);
    }
    
    public void PrintAllGenes()
    {
        int startIndex= 0;
        
        while (true) {            
            String currentGene = FindAdvGenes(startIndex);
            if (currentGene.isEmpty() ) 
                break;
            
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }
    
    //PART5 END
    
    
    
    //Part6
    
    public static int HowMany(String subString ,String mainString) throws Exception
    {
        if (subString.length() > mainString.length())
        {
            throw new Exception("subString cannot be long than mainString");
        }
        int startIndex = 0;
        int iter = mainString.indexOf(subString, startIndex);
        int counter = 0;
        while (-1 != iter ) 
        {            
            iter =  mainString.indexOf(subString, iter + subString.length());
            counter ++;
        }
        return counter;
    }
    
    public int CountGenes()
    {
        int startLocation = 0;
        int counter = 0;
        String currentGene = FindAdvGenes(startLocation);
        
        while ( !currentGene.isEmpty() ) 
        {            
            startLocation = dna.indexOf(currentGene, startLocation) + currentGene.length();
            currentGene = FindAdvGenes(startLocation);
            counter++;
        }
        return counter ;
    }
    
    //PART6 END
    
    
    //PART7 
   public void findAbc(String input){
       int index = input.indexOf("abc");
       while (true){
           if (index == -1 || index >= input.length() - 3){
               break;
           }
           String found = input.substring(index+1, index+4);
           System.out.println(found);
           index = input.indexOf("abc",index+3);
       }
   }

   
    //PART7 END
   
   //PART8 
   public float CgRatio()
   {
       int cytosineCounter = 0;
       int guanineCounter = 0;
       int index = 0;
       while(-1 != index)
       {
            cytosineCounter++;
           index = dna.indexOf('C', index + 1);
       }
       
       index = 0;
       while(-1 != index)
       {
           guanineCounter++;
           index = dna.indexOf('G', index + 1);
       }
       int sum = cytosineCounter + guanineCounter -2 ;
       return ((float)sum) / dna.length();
   }
   
   public void ProcessGenes()
   {
       
       
    
   }
    @Override 
    public String toString()
    {
        return " dna is -> " + dna + "\n" ;
    }
    
}
