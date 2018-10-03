package Dict;

import java.util.Collections;
import java.util.Scanner;

public class DictionaryCommandline {
    private  int th;
    public void dictionaryBasic()
    {
        DictionaryManagement dm= new DictionaryManagement();
        dm.insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced()
    {
        DictionaryManagement dm= new DictionaryManagement();
        dm.insertFromFile();
        System.out.println(Dictionary.arrWord.size()+" words available");
        commandLine(dm);
    }

    public void commandLine(DictionaryManagement dm){
        System.out.println("Chose command: search | update | edit | remove | showAll | exit");
        Scanner sc = new Scanner(System.in);
        String cmd = sc.nextLine();
        cmd.toLowerCase();
        if(cmd.equals("search")) dictionarySeacher();
        else if(cmd.equals("update")) dm.insertFromCommandline();
        else if(cmd.equals("edit")) dm.editWord();
        else if(cmd.equals("remove")) dm.removeWord();
        else if(cmd.equals("showall")) showAllWords();
        else if (cmd.equals("exit")){
            dm.dictionaryExportToFile();
            System.out.println("All data saved! Goodbye!");
            return;
        }
        else {
            System.out.println("Command unrecognized! Please try again!");
        }
        commandLine(dm);
        return;
    }


    public void showAllWords()
    {
        th=1;
        System.out.println(String.format("%s %20s %20s %20s %20s ","No","|","English","|","Vietnamese"));
        for (Word w: Dictionary.arrWord)
        {
            System.out.println(String.format("%02d %20s %20s %20s %20s ", th, "|", w.getWord_target(), "|" ,w.getWord_explain()));
            th++;

        }
    }
    public void dictionarySeacher(){
        System.out.print("Enter characters: ");
        Scanner sc = new Scanner(System.in);
        String charInput = sc.nextLine().toLowerCase();
        int wordCount = 0;
        int letterCount = charInput.length();
        for(int i =0; i<Dictionary.arrWord.size(); i++){
            Word wordCheck = Dictionary.arrWord.get(i);
            if(charInput.equals(wordCheck.getWord_target().substring(0, letterCount).toLowerCase())){
                wordCount = wordCount+1;
                System.out.println(String.format("%20s %20s %20s ", wordCheck.getWord_target(), "|" ,wordCheck.getWord_explain()));
            }
        }
        if(wordCount == 0) System.out.println("No word found!");
    }
}
