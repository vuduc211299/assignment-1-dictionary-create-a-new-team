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

}
