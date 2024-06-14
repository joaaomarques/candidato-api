package br.com.cidadao.api.candidato_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjetoNaoEncontradoException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1230664543728526270L;

    public ObjetoNaoEncontradoException(String mensagem) {super(mensagem);}
}
