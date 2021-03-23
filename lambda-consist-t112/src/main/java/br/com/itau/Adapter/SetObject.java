package br.com.itau.Adapter;

import br.com.itau.domain.HeaderDomain;

public interface SetObject<T1, T2>{
    T1 setHeader (String message);
    T2 setTrailler (String message);
}
