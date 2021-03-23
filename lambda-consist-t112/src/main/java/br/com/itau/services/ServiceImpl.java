package br.com.itau.services;

import br.com.itau.Adapter.JrecordbindImpl;
import br.com.itau.Adapter.SetObject;
import br.com.itau.domain.HeaderDomain;
import br.com.itau.domain.TraillerDomain;

public class ServiceImpl implements Service {

    private static String MTI_HEADER_TRAILLER = "1644";
    private static String FUNCTION_CODE_HEADER = "697";
    private static String FUNCTION_CODE_TRAILLER = "695";
    private static int MTI_POSICAO_INICIAL = 0;
    private static int MTI_POSICAO_FINAL = 4;
    private static int FUNCTION_CODE_POSICAO_INICIAL = 20;
    private static int FUNCTION_CODE_POSICAO_FINAL = 23;

    JrecordbindImpl setObject = new JrecordbindImpl();

    @Override
    public boolean isHeader(String message) {
        if (!message.substring(MTI_POSICAO_INICIAL, MTI_POSICAO_FINAL).equals(MTI_HEADER_TRAILLER)) {
            return false;
        }

        if (!message.substring(FUNCTION_CODE_POSICAO_INICIAL, FUNCTION_CODE_POSICAO_FINAL).equals(FUNCTION_CODE_HEADER)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isTrailler(String message) {
        if (!message.substring(MTI_POSICAO_INICIAL, MTI_POSICAO_FINAL).equals(MTI_HEADER_TRAILLER)) {
            return false;
        }

        if (!message.substring(FUNCTION_CODE_POSICAO_INICIAL, FUNCTION_CODE_POSICAO_FINAL).equals(FUNCTION_CODE_TRAILLER)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isHeaderCompatibleTrailler(String headerMessage, String traillerMessage) {

        HeaderDomain header = setObject.setHeader(headerMessage);
        HeaderDomain trailler = setObject.setHeader(traillerMessage);

        if (!header.getMti().equals(trailler.getMti())) {
            return false;
        }

        if (!header.getFunctionCode().equals(trailler.getFunctionCode())) {
            return false;
        }

        return true;
    }
}
