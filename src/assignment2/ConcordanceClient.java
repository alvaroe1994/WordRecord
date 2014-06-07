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

import java.io.*;
import java.util.*;
import javax.swing.*;
public class ConcordanceClient
{
   public static void main(String[] args)
   {
      boolean another = true;
      do
      {
         String fileName = JOptionPane.showInputDialog(null,
                           "Enter the name of a text file, Click Cancel to quit");
         
         if (fileName != null)
         {
            Concordance table = new Concordance(fileName);
            processQueries(table);
         }
         else
            another = false;
            
      } while (another);
   }
   
   private static void processQueries(Concordance table)
   {
      System.out.println(table);
      
      boolean another = true;
      do
      {
         String word = JOptionPane.showInputDialog(null,
                        "Enter a word for look-up, Click Cancel to quit",
                        "CONCORDANCE " + table.getFileName(), 
                        JOptionPane.PLAIN_MESSAGE);
         
         if (word == null)
            another = false;
         else
         {
            int count = table.getFrequency(word);
            
            String message;
            if (count == 0)
               message = "No entry for >" + word + "<";
            else
               message = word + " (" + count + " times) on lines " +
                         Arrays.toString(table.getLineNumbers(word));
               
            JOptionPane.showMessageDialog(null, message, 
                                          "CONCORDANCE " + table.getFileName(),
                                          JOptionPane.INFORMATION_MESSAGE);
         }
            
      } while (another);
             
      //The code is not working well for the first word in each document. fix.

   }
             

}