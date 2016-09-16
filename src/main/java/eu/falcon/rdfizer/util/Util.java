package eu.falcon.rdfizer.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by jim on 16/9/2016.
 */
@Service
public class Util {

    @Value("${rdfizer.namespace.base}")
    private String namespace;
    @Value("${rdfizer.namespace.row}")
    private String rowQualifier;
    @Value("${rdfizer.namespace.hasId}")
    private String idQualifier;
    @Value("${rdfizer.namespace.rowId}")
    private String rowId;

    public String tagString(String string) {
        return "<" + string + ">";
    }
    public String generateTriplesForRow(String row, List<String> header) {
        List<String> rowToks = Arrays.asList(row.split(","));
        //random identifier until data set is completely defined
        Random rnd = new Random();
        int id = rnd.nextInt();
        String triples = tagString(namespace+rowQualifier) + tagString(namespace+idQualifier) +
                tagString(namespace+rowId+id)+"\n";
        for (int i = 0; i < rowToks.size(); i++) {
            triples += tagString(namespace+rowId) + tagString(header.get(i)) + rowToks.get(i) + "\n";
        }
        return triples;
    }
}
