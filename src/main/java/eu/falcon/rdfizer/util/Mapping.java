package eu.falcon.rdfizer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by jim on 16/9/2016.
 */
@Component
public class Mapping {
    @Autowired
    private ApplicationContext appContext;

    public String getMapping(String field) {
        Resource resource = appContext.getResource("classpath:mapping/mapping.txt");
        try {
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = br.readLine()) != null) {
                if(line.contains(field)) {
                    return line.split(",")[1];
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
