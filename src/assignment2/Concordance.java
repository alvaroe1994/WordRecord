//  PROGRAMMER:  Alvaro E. de Castro  123-45-6789
//
//  CLASS:       COP 3337-01   TWT 11:00AM
//
//  INSTRUCTOR:  Norman Pestaina  ECS 364
//
//  ASSIGNMENT:  #2 ArrayLists   DUE Sunday 06/08
//
// CERTIFICATION: I certify that this work is my own and that
//                 none of it is the work of any other person.
//

package assignment2;

//An instance of this class represents a Concordance for a text file,
// a table of entries, one for each word from the file
//Each entry records a word, its frequency, and ite line-numbers
// 
import java.util.*;
import java.io.*;
public class Concordance
{
   //Instance Variables
      //Name of the text file 
      //Table of Concordance entries
    private String textfile;
    ArrayList<WordRecord> concordance;
   
   //Constructor
   public Concordance(String fileName)
   {
      //Initialize the Instance Variables
      this.textfile = fileName;
      this.concordance = new ArrayList<WordRecord>();
      //Construct this Concordance by scanning words from the text file
      try
      {
         //Open the text file, and create a scanner over the file
         Scanner source = new Scanner( new FileReader(fileName) );
         
         //Scan words from the file to create the concordance
         this.buildWordTable( source );
      }
      catch (FileNotFoundException fnfe)
      {
         throw new RuntimeException( fnfe.getMessage() );
      } 
   }
   
   //Accessor
   public String getFileName()
   {
      return this.textfile;
   }
   
   //Accessor
   public int getFrequency(String word)
   {
       //loops through concordance to find the word then returns frequency
       for(int i = 0;i<this.concordance.size();i++)
       {
           if(word.equalsIgnoreCase(this.concordance.get(i).getWord()))
               return this.concordance.get(i).getFrequency();
       }
      return 0;
   }
   
   //Accessor
   public int[] getLineNumbers(String word)
   {
       //loops through concordance to find the word then returns line numbers
     for(int i = 0;i<this.concordance.size();i++)
       {
           if(word.equalsIgnoreCase(this.concordance.get(i).getWord()))
               return this.concordance.get(i).getLineNumbers();
       }
      return null;
   }
   
   //Override
   public String toString()
   {
       String ret = "";
       for(int i = 0;i < this.concordance.size();i++)
       {
           ret += this.concordance.get(i).toString()+" ";
       }
      return ret;
   }
   
   //Constructor Helper
   // Input each line of the text file
   //    Create a Scanner for the input line
   //    Scan the words from the input line
   //       Update this Concordance with each word scanned
   private void buildWordTable(Scanner source)
   {
       int linenum = 0;
       String currentline = "";
       String[] splitsent;
       //Scans file line by line
       while(source.hasNextLine())
       {
           //line number of the file
           linenum++;
           //reads the current line of the file
           currentline = source.nextLine();
           //replaces all punctions to "" so the file doesnt read it
           currentline = currentline.replace(",", "");
           currentline = currentline.replace(".", "");
           currentline = currentline.replace(";", "");
           currentline = currentline.replace(":", "");
           currentline = currentline.replace("?", "");
           currentline = currentline.replace("!", "");
           //split the line into an array whereever there is a space
           splitsent = currentline.split(" ");
           for(String run : splitsent)
           {
               update(run,linenum);
           }
       }
   }
    
   //Mutator - Constructor Helper
   //Update this Concordance with a word from the source file and its line-number
   //If the word is already stored in this Concordance it's WordRecord is updated
   // with the line-number, 
   //If the word is not already stored in this Concordance a new WordRecord is 
   // created and inserted into this Concordance
   private void update(String word, int lineNumber)
   {
       //checks to see if its the first word of concordance or not
       if(this.concordance.size()>0)
               {
                   //if it is not the first word it loops through the concordance
                   for(int i = 0; i < this.concordance.size();i++)
                   {
                       int check = 0;
                       //if word is in concordance it updates the WordRecord for that word
                       if(word.equalsIgnoreCase(concordance.get(i).getWord()))
                       {
                           concordance.get(i).update(lineNumber);
                           check++;
                       }
                       
                       //if the word is not found it adds it to the concordance and breaks out of the loop
                       if(check == 0 && i == concordance.size() - 1)
                       {
                           WordRecord temp = new WordRecord(word,lineNumber);
                           this.concordance.add(temp);
                           break;
                       }
                           
                   }
               }
       
               else
               //if it is the first word of the concordance it adds it to the concordance
               {
                   WordRecord temp = new WordRecord(word,lineNumber);
                   this.concordance.add(temp);
               }
   }
   
   //Accessor Helper - Search
   //Returns a reference to the WordRecord for a given word
   private WordRecord lookUp(String word)
   {
       int contains = 0;
       boolean check = false;
       //loops through concordance
      for(int i = 0; i < this.concordance.size();i++)
      {
          //checks to see if the words are equal
          if(word.equalsIgnoreCase(this.concordance.get(i).getWord()))
          {
              //gets location of what position the word is true
              contains = i;
              check = true;
          }
              
      }
      //checks to see if it was found in concordance
      if(check)
          return this.concordance.get(contains);
      
      return null;
   }
}