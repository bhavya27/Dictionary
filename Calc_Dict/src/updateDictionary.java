import java.io.*;
class updating extends Thread{
    String category="";
    updating(String category){
        this.category=category;
    }
    public void run(){
        try{
       String word="";
       BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("listOfWords.txt")));
       while((word=br.readLine())!=null)    
       System.out.println(new Database().main(word, category));
    }catch(Exception e){e.printStackTrace();}
    }
}
public class updateDictionary{
       public void main(){
       new updating("definitions").start();
       new updating("synonyms").start();
       new updating("antonyms").start();
    }
}