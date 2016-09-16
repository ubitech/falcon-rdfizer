package eu.falcon.rdfizer.service;

import eu.falcon.rdfizer.util.SarefOntologyParser;
import eu.falcon.rdfizer.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by jim on 16/9/2016.
 */
@ComponentScan(basePackages = {"eu.falcon.rdfizer.util"})
@Service
public class RDFizerImpl implements RDFizer {
    @Autowired
    SarefOntologyParser sarefOntologyParser;
    @Autowired
    Util util;
    public String transformDataToRDF(String data) {

        List<String> headerFields = Arrays.asList(data.split("\n")[0].split(","));
        //System.out.println("splited headers");
        //System.out.println(headerFields);
        headerFields = sarefOntologyParser.getOntologyClassesFromMapping(headerFields);
        List<String> rows = new LinkedList<String>(Arrays.asList(data.split("\n")));
        rows.remove(0);

        String triples = "";
        for (String row : rows) {
            System.out.println("ROW");
            System.out.println(row);
            String triplesFromRow = util.generateTriplesForRow(row, headerFields);
            triples += triplesFromRow;
        }


        return triples;
    }
}
