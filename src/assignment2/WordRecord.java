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

//An instance of this class stores information on one word from a text file
// -- the word
// -- its frequency in the text file
// -- a list of line-numbers where the word occurs, in ascending order
//A line-number is recorded no more than once
//
import java.util.*;
public class WordRecord implements Comparable
{
    
   //instance variables 
   private String word;
   private ArrayList<Integer> linenumber = new ArrayList<Integer>();
   private int frequency;
   //Constructor
   public WordRecord(String word, int lineNumber)
   {
       this.word = word;
       this.linenumber.add(lineNumber);
       frequency = 1;
   }
   
   //Accessor
   //returns the word
   public String getWord()
   {
      return this.word;
   }
   
   //Accessor
   //returns the number of times the word appears
   public int getFrequency()
   {
      return this.frequency;
   }
   
   //Accessor
   //returns the line number in which the words appear
   public int[] getLineNumbers()
   {
       int[] linenum = new int[this.linenumber.size()];
       for(int i = 0; i < linenum.length;i++)
       {
           linenum[i] = this.linenumber.get(i);
       }
      Arrays.sort(linenum);
      return linenum;
   }
   
   //Mutator
   //Update this WordRecord to reflect another occurence  
   // of its word on the line with the given line-number
   public void update(int lineNumber)
   {
       //increases the times it shows up by 1
       this.frequency++;
       boolean check = false;
       //checks to see if the wordRecord already contains this line number
       for(int i = 0; i <this.linenumber.size();i++)
       {
           if(lineNumber == this.linenumber.get(i))
               check = true;
       }
       if(!check)
       this.linenumber.add(lineNumber);
   }
   
   //Override
   // returns the WordRecord variables
   public String toString()
   {
      String ans = "";
      ans += this.word + " "+this.frequency+ " ";
      ans += Arrays.toString(getLineNumbers());

      return ans;
   }
   
   //Implements Comparable
   //Compare this WordRecord with other, based on their words
   public int compareTo(Object other)
   {
       //makes sure both words are lowercase and then compares them
       String minimize = this.word.toLowerCase();
       String min = ""+other;
       min = min.toLowerCase();
      return minimize.compareTo(min);
   }
}
      