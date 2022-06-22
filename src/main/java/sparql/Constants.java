/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sparql;

/**
 *
 * @author elahi
 */
public interface Constants {

    public static final String ID = "id";
    public static final String Apertium="Apertium";
    public static final String Panlex="Panlex";
    public static final String endpointApertium="http://linguistic.linkeddata.es/sparql/";
    public static final String endpointPanlex="http://linguistic.linkeddata.es/sparql/";
    public static final String ANSWER_URI = "answer_uri";
    public static final String ANSWER_LABEL = "answer_label";
    public static final String LEXICAL_ENTRY = "lexicalEntry";
    public static final String SENTENCETYPE = "sentenceType";
    public static final String numberOfTripes = "numberOfTripes";
    public static final String numberOfProperties = "numberOfProperties";
    public static final String numberOfEntities = "numberOfEntities";
    public static final String numberOfLinks = "numberOfEntities";

    public static final String interlinkingCompleteness = "interlinkingCompleteness";
    public static String qaldFileBinding = "qaldEntities.csv";
    public static String propertyReport = "A-propertyReport.txt";
    public String[] questionHeader = new String[]{ID, ANSWER_URI, ANSWER_LABEL};
    
    //http://linguistic.linkeddata.es/apertium/sparql-editor/
    /*@prefix dc: <http://purl.org/dc/elements/1.1/> .
@prefix grddl: <http://www.w3.org/2003/g/data-view#> .
@prefix owl: <http://www.w3.org/2002/07/owl#> .
@prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> .
@prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#> .
@prefix xml: <http://www.w3.org/XML/1998/namespace> .
@prefix xsd: <http://www.w3.org/2001/XMLSchema#> .*/
    
    
    /*
    PREFIX lemon: <http://www.lemon-model.net/lemon#>
PREFIX tr: <http://purl.org/net/translation#>
PREFIX lexinfo: <http://www.lexinfo.net/ontology/2.0/lexinfo#>

SELECT DISTINCT ?translated_written_rep ?indirect_translated_written_rep 
WHERE {
  ?form_a lemon:writtenRep "bank"@en .
  ?lex_entry_a lemon:lexicalForm ?form_a .
  ?sense_a lemon:isSenseOf  ?lex_entry_a .

  #retrieve translations
  ?trans  tr:translationSense  ?sense_a .
  ?trans  tr:translationSense  ?sense_b .

  #retrieve translated terms
  ?sense_b lemon:isSenseOf  ?lex_entry_b .
  ?lex_entry_b lemon:lexicalForm ?form_b .
  ?form_b lemon:writtenRep ?translated_written_rep .
  ?lexicon_b lemon:entry ?lex_entry_b .

  #take the ones in an English lexicon
  ?lexicon_b lemon:language "es".

  #retrieve indirect translations
   ?sense_pivot_b lemon:isSenseOf  ?lex_entry_b .
   ?indirect_trans  tr:translationSource  ?sense_pivot_b .
   ?indirect_trans  tr:translationTarget  ?sense_target .

   #retrieve indirect translated terms
   ?sense_target lemon:isSenseOf  ?lex_entry_target .
	?lex_entry_target lemon:lexicalForm ?form_target .
	?form_target lemon:writtenRep ?indirect_translated_written_rep .

  #take the ones in a Portuguese lexicon
  ?lexicon_c lemon:entry ?lex_entry_target ;
   lemon:language "pt".
}
    */
    
    /*
    PREFIX lemon: <http://www.lemon-model.net/lemon#>
PREFIX tr: <http://purl.org/net/translation#>
PREFIX lexinfo: <http://www.lexinfo.net/ontology/2.0/lexinfo#>

SELECT ?translation_set ?tran
WHERE {
  ?translation_set tr:trans ?tran .
}
    */
    
    /*
    PREFIX lemon: <http://www.lemon-model.net/lemon#>
PREFIX tr: <http://purl.org/net/translation#>
PREFIX lexinfo: <http://www.lexinfo.net/ontology/2.0/lexinfo#>

SELECT DISTINCT   ?trans    ?sense_a 
WHERE {
  # retrieve lexical entry and senses for "red"
  ?trans  tr:translationSense  ?sense_a .
}
Limit 100
    */

}
