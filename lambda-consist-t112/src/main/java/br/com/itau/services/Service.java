package br.com.itau.services;

public interface Service {
    boolean isHeader (String message);
    boolean isTrailler (String message);
    boolean isHeaderCompatibleTrailler (String header, String trailler);

}
