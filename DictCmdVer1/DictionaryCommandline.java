package Dict;

public class DictionaryCommandline {
    private  int th;
    public  void   dictionaryBasic()
    {
                  DictionaryManagement dm= new DictionaryManagement();
                  dm.insertFromFile();
                  dm.insertFromCommandline();
                  dm.dictionaryLookup();
                  showAllWords();
    }
    public  void showAllWords()
    {
        th=1;
        System.out.println(String.format("%s %20s %20s %20s %20s ","No","|","English","|","Vietnamese"));
        for (Word w: Dictionary.arrWord)
        {
            //System.out.println(th+"     | "+w.getWord_target()+"     | "+ w.getWord_explain()+" ");

            System.out.println(String.format("%02d %20s %20s %20s %20s ", th, "|", w.getWord_target(), "|" ,w.getWord_explain()));
            th++;

        }
    }
}
