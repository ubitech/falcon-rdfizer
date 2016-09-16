package eu.falcon.rdfizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import eu.falcon.rdfizer.service.RDFizer;
import eu.falcon.rdfizer.service.RDFizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;


@SpringBootApplication
public class Application {

    private static RDFizer rdfizer;

    @Autowired
    public Application(RDFizer rdfizer) {
        Application.rdfizer = rdfizer;
    }

    public static void main(String[] args) {
        ApplicationContext appContext = SpringApplication.run(Application.class, args);

        Resource resource = appContext.getResource("classpath:data/data.csv");

        try {
            InputStream is = resource.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String line;
            String data="";
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                data += (line + "\n");
            }
            br.close();
            System.out.println(rdfizer.transformDataToRDF(data));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}