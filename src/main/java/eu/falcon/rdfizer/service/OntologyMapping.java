package eu.falcon.rdfizer.service;

import java.util.List;

public interface OntologyMapping
{
    public List<String> getOntologyClassNames(List<String> sourceFields);
}