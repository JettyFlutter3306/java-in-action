package cn.element.xml.transform;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.*;
import javax.xml.transform.stream.*;

import org.xml.sax.*;

/**
 * This program demonstrates XSL transformations. It applies a transformation to a set of
 * employee records. The records are stored in the file employee.dat and turned into XML
 * format. Specify the stylesheet on the command line, e.g.<br>
 * java transform.TransformTest transform/makeprop.xsl
 */
public class TransformTest {
    
    public static void main(String[] args) throws Exception {
        Path path;
        if (args.length > 0) {
            path = Paths.get(args[0]);
        } else {
            path = Paths.get("java-xml/src/main/resources/makehtml.xsl");
        }
        
        try (InputStream styleIn = Files.newInputStream(path)) {
            var styleSource = new StreamSource(styleIn);

            Transformer t = TransformerFactory.newInstance().newTransformer(styleSource);
            t.setOutputProperty(OutputKeys.INDENT, "yes");
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

            try (InputStream docIn = Files.newInputStream(Paths.get("java-xml/src/main/resources/employee.dat"))) {
                t.transform(new SAXSource(new EmployeeReader(), new InputSource(docIn)),
                        new StreamResult(System.out));
            }
        }
    }
}

