package eu.falcon.rdfizer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jim on 16/9/2016.
 */
@Component
public class SarefOntologyParser {

    @Value("${saref.ontology.prefix}")
    String prefix;
    @Autowired
    Mapping mapping;
    public List<String> getOntologyClassesFromMapping(List<String> fields) {

        List<String> ontologyFields = new ArrayList<String>();
        for (String field : fields) {
            field = mapping.getMapping(field);
            ontologyFields.add(prefix+field);
        }
        return ontologyFields;
    }
}
