/**
Tamara Lawlor - 19276494
Rachel O'Donoghue - 19274505
Mary Tease - 19256434
/**
Tamara Lawlor - 19276494
Rachel O'Donoghue - 19274505
Mary Tease - 19256434
*/
import java.util.*;
import java.util.LinkedList ;
import java.io.* ;
public class Driver1
{
    
    public static void main(String[] args) {
       
        List<String> userSpecifiedWords =  
        Arrays.asList("green", "blue", "red", "orange", "yellow", "purple", "violet", "black", "white");
        
        WordSearchPuzzle instance = new WordSearchPuzzle(userSpecifiedWords);
        
        System.out.println("CS4222 Project - Example 1");
        System.out.println();
        System.out.println("Puzzle Words:");
        instance.getWordSearchList();
        System.out.println();
        System.out.println("PUZZLE - Random Characters");
        System.out.println("--------------------------");
        instance.getPuzzleAsGrid();
        System.out.println();
        System.out.println("PUZZLE - As String");
        System.out.println("------------------");
        instance.getPuzzleAsString();
        System.out.println();
        instance.showWordSearchPuzzle(true);
        System.out.println();
        instance.showWordSearchPuzzle(false);
       
    }
}