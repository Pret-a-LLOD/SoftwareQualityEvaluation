
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.FileUtils;
import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.math.BigInteger;
import java.util.Set;
import java.util.TreeSet;
import sparql.Constants;
import sparql.SparqlQuery;
import utils.InputCofiguration;

@NoArgsConstructor
public class QueGG implements Constants {

    private static final Logger LOG = LogManager.getLogger(QueGG.class);
    private static String questionsFile = "questions";
    private static String summaryFile = "summary";

    public static void main(String[] args) throws Exception {
        QueGG queGG = new QueGG();
        String configFile = null, dataSetConfFile = null;
        Set<String> menu = new TreeSet<String>();
        //menu.add(Constants.numberOfTripes);
        //menu.add(Constants.numberOfEntities);
        //menu.add(Constants.numberOfProperties);
        menu.add(Constants.interlinkingCompleteness);
        
        Integer sum=(1053624/2624353);
        System.out.println(sum);
        
        Integer add=((899+2081)*100);
        System.out.println(add);
        exit(1);
        
        


       

        for (String type : menu) {
            SparqlQuery sparqlQuery=null;
            if (type.contains(interlinkingCompleteness)) {
                String value1 = new SparqlQuery(endpointApertium, Constants.numberOfLinks).getObject();
                String value2 = new SparqlQuery(endpointApertium, Constants.numberOfEntities).getObject();
                System.out.println("value1::"+value1);
                System.out.println("value2::"+value2);
            }
            else
             sparqlQuery = new SparqlQuery(endpointApertium, type);
        }

    }

}
