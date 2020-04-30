/**
Tamara Lawlor - 19276494
Rachel O'Donoghue - 19274505
Mary Tease - 19256434
*/
import java.util.LinkedList ;
import java.io.* ;
import java.util.*;
public class WordSearchPuzzle{
    
    private char[][] puzzle; 
    char[][] wordPuzzle;
    private List<String> puzzleWords; 
    List<String> wordDirection = new ArrayList<String>();
    List<String> position = new ArrayList<String>();
    List<String> words = new ArrayList<String>();
    int dimensions;
    int length = 0;
    double sum;
    char randomDirection;
    
    public WordSearchPuzzle(List<String> userSpecifiedWords) {
         puzzleWords = new ArrayList<>();
         for(int i = 0; i < userSpecifiedWords.size(); i++)  {
             puzzleWords.add(userSpecifiedWords.get(i));
         }
         
         for(String str : puzzleWords) {
             length = length + str.length();
         }
         sum = length*3;
         dimensions = (int)Math.ceil(Math.sqrt(sum));
         System.out.println("Dimensions = " + dimensions); 
    }
    
    
    public WordSearchPuzzle(String wordFile, int wordCount, int shortest, int longest){
        try {
            FileReader aFileReader = new FileReader(wordFile);
            BufferedReader aBufferReader = new BufferedReader(aFileReader);
            String aWord;
            int wordsRead = 0;
            // Use a LinkedList while we are reading the words from the file
            
            LinkedList<String> chosenWords = new LinkedList<String>();
            aWord = aBufferReader.readLine() ;
            while (aWord != null) {
                wordsRead++;
                if(aWord.length() <= longest && aWord.length() >= shortest) {
                    chosenWords.add(aWord.toUpperCase());
                }
                aWord = aBufferReader.readLine() ;
            }
            aBufferReader.close();
            aFileReader.close();
            // Now transfer the words in the LinkedList to an ArrayList
            for(int i = 0; i < wordCount; i++)  {
                int random = (int)(Math.random()*chosenWords.size());
                words.add(chosenWords.get(random).toUpperCase());
                chosenWords.remove(random);
            }
        }
        catch(IOException x) {
            System.out.println("Error accessing input file!");
        }
        
        puzzleWords = new ArrayList<>();
        for(int i = 0; i < words.size(); i++)  {
            puzzleWords.add(words.get(i));
        }
         
        for(String str : puzzleWords) {
            length = length + str.length();
        }
        sum = length*3;
        dimensions = (int)Math.ceil(Math.sqrt(sum));
        System.out.println("Dimensions = " + dimensions);
        
    }
    
     
    public List<String> getWordSearchList(){
          System.out.println(puzzleWords);
          return puzzleWords;
    }
      
    
    public char[][] getPuzzleAsGrid(){
          String alphabet = "abcdefghijklmnopqrstuvwxyz";
          puzzle = new char[dimensions][dimensions];
          
          for(int r = 0; r < dimensions; r++){
             for(int c = 0; c < dimensions; c++){
                 int random = (int)(Math.random()* alphabet.length());
                 puzzle[r][c] = alphabet.toUpperCase().charAt(random);
                 //System.out.print(puzzle[r][c] + " ");   
             }
             //System.out.println();
          }
          
          generateWordSearchPuzzle();  
          for(int i = 0; i < dimensions; i++){
             for(int j = 0; j < dimensions; j++){
                 System.out.print(wordPuzzle[i][j] + " ");   
             }
             System.out.println();   
          }
          //return puzzle;
          return wordPuzzle;
    }
    
    
    public String getPuzzleAsString(){
         // String puzzleAsString = "";
         // for(int r = 0; r < dimensions; r++){
             // puzzleAsString = 
             // puzzleAsString + Arrays.toString(puzzle[r]).replace(",", "").replace("[", "").replace("]", "") + "\n";
         // }
         // System.out.println(puzzleAsString); 
         String puzzleAsString = "";
         for(int r = 0; r < dimensions; r++){
             puzzleAsString = 
             puzzleAsString + Arrays.toString(wordPuzzle[r]).replace(",", "").replace("[", "").replace("]", "") + "\n";
         }
         System.out.println(puzzleAsString);
         return puzzleAsString;
    }
        
    
    private void generateWordSearchPuzzle(){
        Collections.sort(puzzleWords);
        String direction = "UDLR";
        int i, j, r, c, random, randomRow, randomCol, spaces, row, col, endOfWord;
        wordPuzzle = new char[dimensions][dimensions];
        char randomDirection;
        
        for(r = 0; r < dimensions; r++){
           for(c = 0; c < dimensions; c++){
              wordPuzzle[r][c] = ' ';
           }
        }
         
        for(j = 0; j < puzzleWords.size(); j++){ 
           String word = puzzleWords.get(j).toUpperCase();
           random = (int)(Math.random()*direction.length());
           randomDirection = direction.charAt(random);
           
           if(randomDirection == 'U'){
               wordDirection.add("Up");
               boolean found = false;
               int maxBoundaries = dimensions - word.length();
               randomCol = (int)(Math.random()*dimensions);
               randomRow = (int)(Math.random()*maxBoundaries + word.length()-1);
               endOfWord = randomRow - (word.length()-1);
               
               while(!found){
                   spaces = 0;
                   for (r = randomRow; r >= endOfWord; r--){
                       if (wordPuzzle[r][randomCol] == ' ' ){
                           spaces++;
                       }else{
                           spaces = 0; 
                           break;
                       }
                   } 
                   if(spaces > 0){
                       found = true;
                   }else{
                       randomCol = (int)(Math.random()*dimensions);
                       randomRow = (int)(Math.random()*maxBoundaries + word.length()-1);
                       found = false;
                   }
               }
               
               position.add(String.valueOf(randomRow) + "," + String.valueOf(randomCol)); 
               
               for(i = 0; i < word.length(); i++){
                   wordPuzzle[randomRow][randomCol] = word.charAt(i);
                   randomRow--;
               }
           } else if(randomDirection == 'D'){
               wordDirection.add("Down");
               boolean found = false;
               int maxBoundaries = dimensions - word.length();
               randomCol = (int)(Math.random()*dimensions);
               randomRow = (int)(Math.random()*maxBoundaries);
               endOfWord = randomRow + (word.length()-1);
               
               while(!found){
                    spaces = 0;
                    for (r = randomRow; r <= endOfWord; r++){
                        if (wordPuzzle[r][randomCol] == ' '){
                           spaces++;
                        }else{
                           spaces = 0; 
                           break;
                        }    
                    }  
                    if(spaces > 0){
                        found = true;
                    }else{
                        randomCol = (int)(Math.random()*dimensions);
                        randomRow = (int)(Math.random()*maxBoundaries);
                        found = false;
                    }
               }
               
               position.add(String.valueOf(randomRow) + "," + String.valueOf(randomCol));
               
               for(i = 0; i < word.length(); i++){
                   wordPuzzle[randomRow][randomCol] = word.charAt(i);
                   randomRow++;
               } 
           } else if(randomDirection == 'R'){
               wordDirection.add("Left to Right");
               boolean found = false;
               int maxBoundaries = dimensions - word.length();
               randomCol = (int)(Math.random()*maxBoundaries);
               randomRow = (int)(Math.random()*dimensions);
               endOfWord = randomCol + (word.length()-1);
               
               while(!found){
                   spaces = 0;
                   for (c = randomCol; c <= endOfWord; c++){
                       if (wordPuzzle[randomRow][c] == ' '){
                           spaces++;
                       }else{
                           spaces = 0; 
                           break;
                       }    
                   }
                   if(spaces > 0){
                        found = true;
                   }else{
                       randomCol = (int)(Math.random()*maxBoundaries);
                       randomRow = (int)(Math.random()*dimensions);
                        found = false;
                   }
               }
               
               position.add(String.valueOf(randomRow) + "," + String.valueOf(randomCol));
               
               for(i = 0; i < word.length(); i++){
                      wordPuzzle[randomRow][randomCol] = word.charAt(i);
                      randomCol++;
               } 
            } else if(randomDirection == 'L'){
                wordDirection.add("Right to Left");
                boolean found = false;
                int maxBoundaries = dimensions - word.length();
                randomCol = (int)(Math.random()*maxBoundaries + word.length()-1);
                randomRow = (int)(Math.random()*dimensions);
                endOfWord = randomCol - (word.length()-1);
                
                while(!found){
                    spaces = 0;
                    for (c = randomCol; c >= endOfWord; c--){
                        if (wordPuzzle[randomRow][c] == ' '){
                            spaces++;
                        }else{
                            spaces = 0; 
                            break;
                        }  
                    }
                    if(spaces > 0){
                            found = true;
                        }else{
                            randomCol = (int)(Math.random()*maxBoundaries + word.length()-1);
                            randomRow = (int)(Math.random()*dimensions);
                            found = false;
                        }
                }
                
                position.add(String.valueOf(randomRow) + "," + String.valueOf(randomCol));
                
                for(i = 0; i < word.length(); i++){
                    wordPuzzle[randomRow][randomCol] = word.charAt(i);
                    randomCol--;
                } 
             }
        }
        
        for(r = 0; r < dimensions; r++){
             for(c = 0; c < dimensions; c++){
                if(wordPuzzle[r][c] == ' '){
                 wordPuzzle[r][c] = puzzle[r][c];
                }
             }
        }
    }
    
    public void showWordSearchPuzzle(boolean hide){
        Collections.sort(puzzleWords);
        System.out.println("PUZZLE");
        System.out.println("------");
        if(hide == false){
             for(int r = 0; r < dimensions; r++){
                for(int c = 0; c < dimensions; c++){
                    System.out.print(wordPuzzle[r][c] + " ");
                }
                System.out.println();            
             }
             System.out.println();
             for(int i = 0; (i < puzzleWords.size()) && (i < wordDirection.size()); i++){ 
                 System.out.print(puzzleWords.get(i).toUpperCase() + " ");
                 System.out.println("[" + position.get(i) + "]");
                 System.out.println("Direction: " + wordDirection.get(i) + "\n");
             }
        }else{
             for(int r = 0; r < dimensions; r++){
                for(int c = 0; c < dimensions; c++){
                     System.out.print(wordPuzzle[r][c] + " ");
                }
                System.out.println();            
             }
             System.out.println();
             for(int i = 0; i < puzzleWords.size(); i++){ 
                 System.out.println(puzzleWords.get(i).toUpperCase() + randomDirection);
             }
        }
    }
    
    private void dimensions(){
        for(String str : puzzleWords) {
             length = length + str.length();
         }
         sum = length*3;
         dimensions = (int)Math.ceil(Math.sqrt(sum));
         System.out.println("Dimensions = " + dimensions);
    }
}
