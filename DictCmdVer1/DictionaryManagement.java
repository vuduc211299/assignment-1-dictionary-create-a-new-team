
package Dict;
import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private  int numOfWord;

      public  void insertFromCommandline()
      {

          System.out.print("Enter number of word: ");
          Scanner sc = new Scanner(System.in);
          numOfWord = Integer.parseInt(sc.nextLine());
          for (int i=1;i<=numOfWord;i++) {
              System.out.print("Enter word target:  ");
             String word_target = sc.nextLine();
              System.out.print("Enter word explain:  ");
              String word_explain = sc.nextLine();
              Dictionary.addWord(new Word(word_target,word_explain));
          }
      }

      public void insertFromFile(){
          try{
              File file = new File("F:\\5. COLLEGES\\Java\\dict\\src\\Dict\\dictionaries.txt");
              BufferedReader br = new BufferedReader(new FileReader(file));

              for(String i = br.readLine(); i != null; i = br.readLine()){
                  String words[] = i.split("\\t");  //t la regular expression cua tab
                  Word w = new Word(words[0], words[1]);
                  Dictionary.addWord(w);
              }
          }
          catch(Exception ex){
              System.out.println("Error: "+ ex.getLocalizedMessage());
          }
      }

      public Word dictionaryLookup(){
          System.out.print("Enter word target (for lookup): ");
          Scanner sc = new Scanner(System.in);
          String wordInput = sc.nextLine();
          for(int i=0; i< Dictionary.arrWord.size(); i++){
              Word wordCheck = Dictionary.arrWord.get(i);
              if(wordInput.equals(wordCheck.getWord_target())){
                  System.out.println(wordInput+ " found!");
                  System.out.println(wordCheck.getWord_target()+ " | "+ wordCheck.getWord_explain());
                  return wordCheck;
              }
          }
          System.out.println(wordInput+ " not found!");
          return null;
      }
}

