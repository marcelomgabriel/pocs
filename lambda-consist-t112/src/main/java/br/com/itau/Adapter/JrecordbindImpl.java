package br.com.itau.Adapter;

import br.com.itau.domain.HeaderDomain;
import br.com.itau.domain.TraillerDomain;
import org.fissore.jrecordbind.DefinitionLoader;
import org.fissore.jrecordbind.RecordDefinition;
import org.fissore.jrecordbind.Unmarshaller;

import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Iterator;

public class JrecordbindImpl implements SetObject <HeaderDomain, TraillerDomain>{

    private static String LAYOUT_HEADER = "/HeaderLayout.xsd";
    private static String LAYOUT_TRAILLER = "/TraillerLayout.xsd";

    @Override
    public HeaderDomain setHeader(String message) {
        return (HeaderDomain) setObject(message, LAYOUT_HEADER);
    }

    @Override
    public TraillerDomain setTrailler(String message) {
        return (TraillerDomain) setObject(message, LAYOUT_TRAILLER);
    }

    private Object setObject (String message, String layout) {
        RecordDefinition recordDefinition;
        Unmarshaller<Object> unmarshaller;

        recordDefinition = new DefinitionLoader().load(
                new InputStreamReader(JrecordbindImpl.class.getResourceAsStream(layout))
        );

        unmarshaller = new Unmarshaller<>(recordDefinition);

        Iterator<Object> recordInterator = unmarshaller.unmarshallToIterator(new StringReader(message));

        return recordInterator.next();
    }
}