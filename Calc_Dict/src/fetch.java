import org.jsoup.*;
import java.util.regex.*;
import java.net.MalformedURLException;
import org.jsoup.select.Elements;
import java.util.StringTokenizer; 
import org.jsoup.nodes.*;
public class fetch {        
 //   public static void main(String args[]){
   public String get(String word,String category){
        StringBuilder sb=new StringBuilder();
        Pattern pat;
        Matcher mat;
        String link="";
   //     String category="antonyms";
        if(category.equals("definitions")){
        link="http://dictionary.reference.com/browse/"+word+"?s=t";        
        int i=0;
        try{
    Document doc=Jsoup.connect(link).get();
    Elements title=doc.getElementsByClass("def-content");
    StringTokenizer st=new StringTokenizer(title.text(),".");
    while(st.hasMoreTokens()){
        StringTokenizer st1=new StringTokenizer(st.nextToken(),":");
        int totalTokens=st1.countTokens();
        sb.append(++i+": "+st1.nextToken()+"\n");        
        if(totalTokens>1)
        sb.append( "Example: "+st1.nextToken()+"\n");        
    }
    sb.append("\n--Powered by Dictionary.com");
        }catch(MalformedURLException e){
         return sb.append("Ergh.Please Check your spelling.\nFor nerds only: MalformedURLException").toString();
        }catch(HttpStatusException e){
         return sb.append("Ergh.Please Check your spelling.\nFor nerds only: HttpStatusException").toString();
        }catch(Exception e){
         return sb.append("Ergh.Connection Timed out.\n Please try again or check your internet connection.\n Make sure you database is loaded").toString();
     }}
        else if(category.equals("synonyms")){
            link="http://www.thesaurus.com/browse/"+word+"?s=t";
            try{
               Document doc=Jsoup.connect(link).get();
               String synonym=doc.getElementsByClass("relevancy-list").first().text();
               pat=Pattern.compile("star");
               mat=pat.matcher(synonym);
               synonym=mat.replaceAll(",\n");
               sb.append(synonym);
               sb.append("\n--Powered by Dictionary.com");
            }catch(MalformedURLException e){
                return sb.append("Ergh.Please Check your spelling.\nFor nerds only: MalformedURLException").toString();
            }catch(HttpStatusException e){
                return sb.append("Ergh.Please Check your spelling.\nFor nerds only: HttpStatusException").toString();
            }catch(Exception e){
                return sb.append("Ergh.Connection Timed out.\n Please try again or check your internet connection.").toString();
            }
        }
        else if(category.equals("antonyms")){
            link="http://www.thesaurus.com/browse/"+word+"?s=t";
            try{
               Document doc=Jsoup.connect(link).get();
               String antonym=doc.getElementsByClass("list").first().text();
               pat=Pattern.compile(" ");
               mat=pat.matcher(antonym);
               antonym=mat.replaceAll(",\n");
               sb.append(antonym);
               sb.append("\n--Powered by Dictionary.com");
            }catch(MalformedURLException e){
                return sb.append("Ergh.Please Check your spelling.\nFor nerds only: MalformedURLException").toString();
            }catch(HttpStatusException e){
                return sb.append("Ergh.Please Check your spelling.\nFor nerds only: HttpStatusException").toString();
            }catch(Exception e){
                return sb.append("Ergh.Connection Timed out.\n Please try again or check your internet connection.").toString();
            }
        }
        return sb.toString();
}
}
