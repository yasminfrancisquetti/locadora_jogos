package locadora.service;

import locadora.exception.JogoSemEstoqueException;
import locadora.exception.ValorZeroException;
import locadora.Cliente;
import locadora.Jogo;
import locadora.Locacao;
import locadora.util.DataUtil;

import java.util.Date;

public class LocacaoService 
{

    private final int diasPadrao = 5;
    public int testePublic;
    int testeDefault;

    public Locacao alugarJogo(Cliente cliente, Jogo jogo) throws JogoSemEstoqueException, ValorZeroException {

        if (jogo.getQtdestoque() == 0) 
        {
            throw new JogoSemEstoqueException();
        }

        if (jogo.getValor() == 0) 
        {
            throw new ValorZeroException();
        }

        Locacao locacao = new Locacao();
        locacao.setCliente(cliente);
        locacao.setJogo(jogo);
        locacao.setValor(jogo.getValor());
        locacao.setRetirada(new Date());

        Date devolucao = new DataUtil().addDias(new Date(), diasPadrao);
        locacao.setDevolucao(devolucao);

        return locacao;
    }
    
}
