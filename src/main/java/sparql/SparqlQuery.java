/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sparql;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.query.QueryType;

/**
 *
 * @author elahi
 */
public class SparqlQuery implements Constants{

    private  String endpoint = "https://dbpedia.org/sparql";
    private String answer;
    public static String FIND_ANY_ANSWER = "FIND_ANY_ANSWER";
    public static String FIND_LABEL = "FIND_LABEL";
    private String sparqlQuery = null;
    public static String RETURN_TYPE_OBJECT = "objOfProp";
    public static String RETURN_TYPE_SUBJECT = "subjOfProp";
    private String resultSparql = null;
    private String command = null;
    private String type = null;


    public SparqlQuery(String endpoint, String type) {
        this.endpoint = endpoint;
        if (type.contains(numberOfTripes)) {
            this.sparqlQuery = this.numberOfTripes();
        } else if (type.contains(numberOfEntities)) {
            this.sparqlQuery = this.numberOfEntities();
        } else if (type.contains(numberOfProperties)) {
            this.sparqlQuery = this.numberOfProperties();
        } else if (type.contains(numberOfLinks)) {
            this.sparqlQuery = this.numberOfLinks();
        }

        if (this.sparqlQuery != null) {
            this.resultSparql = executeSparqlQueryCurl(sparqlQuery);
            this.parseResult(this.resultSparql);
        }

        System.out.println("endpoint::" + endpoint);
        System.out.println("sparqlQuery::" + sparqlQuery);
        System.out.println("resultSparql::" + resultSparql);
        System.out.println("objectOfProperty::" + this.answer);

    }

    SparqlQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Integer isSimpleOrComposite(String sparql) {
        Integer index = 0;
        /*if (sparql != null) {
            String[] lines = sparql.split(System.getProperty("line.separator"));
            for (String line : lines) {
                if (line.contains("triple")) {
                    index = index + 1;
                }
            }
        }*/

        if (sparql != null) {
            String[] lines = sparql.split(System.getProperty("line.separator"));
            for (String line : lines) {
                if (line.contains(".")) {
                    index = index + 1;
                }
            }
        }

        return index;
    }

    public String executeSparqlQueryCurl(String query) {
        String result = null, resultUnicode = null;
        Process process = null;

        try {
            resultUnicode = this.stringToUrlUnicode(query);
            if (this.endpoint.contains(endpointApertium)) {
                this.command = "curl " + endpoint + "?default-graph-uri=&query=" + resultUnicode;
            } else {
                this.command = "curl " + endpoint + "?query=" + resultUnicode;
            }
            //http://linguistic.linkeddata.es/sparql/?query=SELECT+%28COUNT%28*%29+as+%3FTriples%29++WHERE+%7B+%3Fs+%3Fp+%3Fo+%7D
            //http://linguistic.linkeddata.es/sparql/?default-graph-uri=&query=SELECT+%28COUNT%28*%29+as+%3FTriples%29++WHERE+%7B+%3Fs+%3Fp+%3Fo+%7D&format=text%2Fhtml&timeout=0&debug=on
            System.out.println(this.command);
            process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            result = builder.toString();
        } catch (IOException ex) {
            Logger.getLogger(SparqlQuery.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("error in reading sparql query!" + ex.getMessage());
            ex.printStackTrace();
        }

        return result;
    }

    public void parseResult(String xmlStr) {
        Document doc = convertStringToXMLDocument(xmlStr);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            this.parseResult(builder, xmlStr);
        } catch (Exception ex) {
            Logger.getLogger(SparqlQuery.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("error in parsing sparql in XML!" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private Document convertStringToXMLDocument(String xmlString) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void parseResult(DocumentBuilder builder, String xmlStr) {

        try {
            Document document = builder.parse(new InputSource(new StringReader(
                    xmlStr)));
            NodeList results = document.getElementsByTagName("results");
            for (int i = 0; i < results.getLength(); i++) {
                NodeList childList = results.item(i).getChildNodes();
                for (int j = 0; j < childList.getLength(); j++) {
                    Node childNode = childList.item(j);
                    if ("result".equals(childNode.getNodeName())) {
                        String answer = childList.item(j).getTextContent().trim();
                        //System.out.println(answer);
                        if (endpoint.contains("dbpedia") && type.contains(FIND_ANY_ANSWER) && answer.contains("--")) {
                            continue;
                        } else {
                            this.answer = childList.item(j).getTextContent().trim();
                        }
                    }
                }

            }

        } catch (SAXException ex) {
            Logger.getLogger(SparqlQuery.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("no result after sparql query!" + ex.getMessage());
            return;
        } catch (IOException ex) {
            Logger.getLogger(SparqlQuery.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("no result after sparql query!" + ex.getMessage());
            return;
        }

    }

    public String setSubjectLabelWikipedia(String entityUrl, String property, String language) {
        String sparql = null;
        if (isEntity(entityUrl)) {
            sparql = PrepareSparqlQuery.setLabelWikipedia(entityUrl, language);
            String resultSparql = executeSparqlQueryCurl(sparql);
            this.parseResult(resultSparql);
            entityUrl = this.answer;
            sparql = "select  ?s\n"
                    + "    {\n"
                    + "   " + "?s" + " " + "<" + property + ">" + "  " + "'" + entityUrl + "'" + "\n"
                    + "    }";
        } else {
            sparql = "select  ?s\n"
                    + "    {\n"
                    + "   " + "?s" + " " + "<" + property + ">" + "  " + entityUrl + "\n"
                    + "    }";
        }
        return sparql;
    }

    public String stringToUrlUnicode(String string) throws UnsupportedEncodingException {
        String encodedString = URLEncoder.encode(string, "UTF-8");
        return encodedString;
    }

    public String getObject() {
        return this.answer;
    }

    public String getSparqlQuery() {
        return sparqlQuery;
    }

    public String getResultSparql() {
        return resultSparql;
    }

    @Override
    public String toString() {
        return "SparqlQuery{" + "objectOfProperty=" + answer + ", sparqlQuery=" + sparqlQuery + '}';
    }

   
    private String modifyBindingListSparql(String sparqlQuery) {
        String[] lines = sparqlQuery.strip().trim().split(System.getProperty("line.separator"));
        String str = "";
        for (String line : lines) {
            if (line.contains("FILTER")) {
                continue;
            } else {
                str += line + "\n";
            }

        }
        return str;
    }

    /*private String  modifyObjectAnswerSparql(String sparqlQuery) {
        String[] lines =  sparqlQuery.strip().trim().split(System.getProperty("line.separator"));
        String str="";
        for(String line :lines){
            if(line.contains("FILTER"))
               continue;
            else if(line.contains("rdfs:label"))
               continue;
            else
            str+=line+"\n";

        }
        return str;
    }*/
 /*
    
PREFIX dbo: <http://dbpedia.org/ontology/>
PREFIX res: <http://dbpedia.org/resource/>
SELECT DISTINCT ?uri WHERE { 
        res:French_Polynesia dbo:capital ?x .
        ?x dbo:mayor ?uri .
}
    
    
    
       sparqlQuery:::(bgp
  
  (triple ?subjOfPropx <http://dbpedia.org/ontology/capital> ?subjOfProp)
  (triple ?subjOfProp <http://dbpedia.org/ontology/mayor> ?objOfProp)
)
    "bindingType" : "Country",
    "returnType" : "Person",
     */
    private String setObjectWikiPediaComposite(String entityUrl, String sparqlOrg, QueryType queryType, String returnVariable) {
        String[] lines = sparqlOrg.split(System.getProperty("line.separator"));
        //entityUrl="http://dbpedia.org/resource/France";
        String sparql = "";
        for (String line : lines) {

            if (line.contains("bgp") && queryType.equals(QueryType.SELECT)) {
                line = "SELECT DISTINCT " + "?" + returnVariable + " WHERE { ";
            } /*else if (queryType.equals(QueryType.SELECT) && type.contains(RETURN_TYPE_SUBJECT)) {
                line = "SELECT DISTINCT " + "?" + RETURN_TYPE_SUBJECT + " WHERE { ";

            }*/ else if (line.contains("triple") || line.contains("?subjOfPropx") || line.contains("?objOfPropx")) {
                line = line.replace("triple", "");
                line = line.replace("?subjOfPropx", "<" + entityUrl + ">");
                line = line.replace("?objOfPropx", "<" + entityUrl + ">");
                line = line + " .";
            }

            sparql += line;

        }

        sparql = sparql.replace("(", "").replace(")", "") + "}";

        return sparql;
    }

  
    private boolean isEntity(String entityUrl) {
        if (entityUrl.contains("http:")) {
            return true;
        }
        return false;
    }

  

    private String numberOfTripes() {
        return "SELECT (COUNT(*) as ?Triples)  WHERE { ?s ?p ?o }";
    }

    private String numberOfEntities() {
        return "SELECT  (count(distinct ?entity) AS ?Entities) WHERE{   ?entity ?property ?object}";
    }

    private String numberOfProperties() {
        return "SELECT  (count(distinct ?property) AS ?Properties) WHERE{   ?entity ?property ?object}";
    }
    private String numberOfOwlSameAs() {
        return "SELECT  Count(?subject)  WHERE{   ?subject owl:sameAs ?object}";
    }
  
    private String numberOfInstances() {
        return "SELECT DISTINCT Count(?s)  WHERE { ?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?o .}";
    }
    private String numberOfClasses() {
        return "SELECT DISTINCT Count(?o)  WHERE { ?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> ?o .}";
    }
    private String numberOfLinks() {
        return "SELECT DISTINCT   Count(?trans)   WHERE {?trans  <http://purl.org/net/translation#translationSense>  ?sense_a .}";
    }
    
    
    
}
