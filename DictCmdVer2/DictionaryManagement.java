
package Dict;
import java.io.File;
import java.io.*;
import java.util.Scanner;

public class DictionaryManagement {
    private int numOfWord;
    private File file = new File("F:\\5. COLLEGES\\Java\\dict\\src\\Dict\\dictionaries.txt");
      public  void insertFromCommandline()
      {
          Scanner sc = new Scanner(System.in);
          System.out.print("Enter word target:  ");
          String word_target = sc.nextLine();
          System.out.print("Enter word explain:  ");
          String word_explain = sc.nextLine();
          Dictionary.addWord(new Word(word_target,word_explain));
          System.out.println("New word updated!");
      }

      public void insertFromFile(){
          try{

              BufferedReader br = new BufferedReader(new FileReader(file));
              String i = br.readLine();
              while(i != null){
                  String words[] = i.split("\\t");  //t la regular expression cua tab
                  Word w = new Word(words[0], words[1]);
                  Dictionary.addWord(w);
                  i = br.readLine();
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
                  System.out.println(String.format("%20s %20s %20s ", wordCheck.getWord_target(), "|" ,wordCheck.getWord_explain()));
                  return wordCheck;
              }
          }
          System.out.println(wordInput+ " not found!");
          return null;
      }

      public void editWord(){
          System.out.print("Word to edit: ");
          Scanner sc = new Scanner(System.in);
          String wordInput = sc.nextLine();
          int wordLocation = -1;
          for(int i=0; i< Dictionary.arrWord.size(); i++){
              Word wordCheck = Dictionary.arrWord.get(i);
              if(wordInput.equals(wordCheck.getWord_target())){
                  wordLocation = i;
              }
          }
          if(wordLocation == -1){
              System.out.println(wordInput+ " not found!");
              return;
          }
          System.out.println("Edit word target");
          Dictionary.arrWord.get(wordLocation).setWord_target(sc.nextLine());
          System.out.println("Edit word explain");
          Dictionary.arrWord.get(wordLocation).setWord_explain(sc.nextLine());
          return;

      }
      public void removeWord() {
          System.out.print("Word to delete: ");
          Scanner sc = new Scanner(System.in);
          String wordInput = sc.nextLine();
          int wordLocation = -1;
          for(int i=0; i< Dictionary.arrWord.size(); i++){
              Word wordCheck = Dictionary.arrWord.get(i);
              if(wordInput.equals(wordCheck.getWord_target())){
                  wordLocation = i;
              }
          }
          if(wordLocation == -1){
              System.out.println(wordInput+ " not found!");
              return;
          }
          Dictionary.arrWord.remove(wordLocation);
          System.out.println(wordInput+ " removed!");
          return;
      }

      public void dictionaryExportToFile(){
          try {
              FileWriter fw = new FileWriter(file);
              for (int i=0; i<Dictionary.arrWord.size(); i++){
                  Word newWord = Dictionary.arrWord.get(i);
                  fw.write(newWord.getWord_target()+(char)(9)+newWord.getWord_explain()+"\n");
              }
              fw.close();
          }
          catch (Exception ex){
              System.out.println("Error: "+ ex.getLocalizedMessage());
          }
      }
}
