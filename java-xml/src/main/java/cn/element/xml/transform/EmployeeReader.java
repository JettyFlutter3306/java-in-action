package cn.element.xml.transform;

import org.xml.sax.*;
import org.xml.sax.helpers.AttributesImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * This class reads the flat file employee.dat and reports SAX parser events to act as if it
 * was parsing an XML file.
 */
public class EmployeeReader implements XMLReader {
    private ContentHandler handler;

    public void parse(InputSource source) throws IOException, SAXException {
        InputStream stream = source.getByteStream();
        var in = new BufferedReader(new InputStreamReader(stream));
        var atts = new AttributesImpl();

        if (handler == null) throw new SAXException("No content handler");

        handler.startDocument();
        handler.startElement("", "staff", "staff", atts);
        String line;
        while ((line = in.readLine()) != null) {
            handler.startElement("", "employee", "employee", atts);
            var t = new StringTokenizer(line, "|");

            handler.startElement("", "name", "name", atts);
            String s = t.nextToken();
            handler.characters(s.toCharArray(), 0, s.length());
            handler.endElement("", "name", "name");

            handler.startElement("", "salary", "salary", atts);
            s = t.nextToken();
            handler.characters(s.toCharArray(), 0, s.length());
            handler.endElement("", "salary", "salary");

            atts.addAttribute("", "year", "year", "CDATA", t.nextToken());
            atts.addAttribute("", "month", "month", "CDATA", t.nextToken());
            atts.addAttribute("", "day", "day", "CDATA", t.nextToken());
            handler.startElement("", "hiredate", "hiredate", atts);
            handler.endElement("", "hiredate", "hiredate");
            atts.clear();

            handler.endElement("", "employee", "employee");
        }

        handler.endElement("", "staff", "staff");
        handler.endDocument();
    }

    public void setContentHandler(ContentHandler newValue) {
        handler = newValue;
    }

    public ContentHandler getContentHandler() {
        return handler;
    }

    // the following methods are just do-nothing implementations
    public void parse(String systemId) throws IOException, SAXException {
    }

    public void setErrorHandler(ErrorHandler handler) {
    }

    public ErrorHandler getErrorHandler() {
        return null;
    }

    public void setDTDHandler(DTDHandler handler) {
    }

    public DTDHandler getDTDHandler() {
        return null;
    }

    public void setEntityResolver(EntityResolver resolver) {
    }

    public EntityResolver getEntityResolver() {
        return null;
    }

    public void setProperty(String name, Object value) {
    }

    public Object getProperty(String name) {
        return null;
    }

    public void setFeature(String name, boolean value) {
    }

    public boolean getFeature(String name) {
        return false;
    }
}
