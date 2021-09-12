package locadora.teste;

import locadora.exception.JogoSemEstoqueException;
import locadora.exception.ValorZeroException;
import locadora.service.LocacaoService;
import locadora.Cliente;
import locadora.Jogo;
import locadora.Locacao;
import locadora.util.DataUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.zip.DataFormatException;

import static org.junit.Assert.*;

public class LocacaoServiceTest 
{

    @Test
    public void alugarJogo() throws Exception 
    {

        Cliente cliente = new Cliente("Antonio");
        Jogo jogo = new Jogo("Harry Potter", 15.00, 1);

        LocacaoService locacaoService = new LocacaoService();
        Locacao locacao;

        locacao = locacaoService.alugarJogo(cliente, jogo);

        assertEquals(locacao.getJogo().getNome(), jogo.getNome());
        assertEquals(locacao.getCliente().getNome(), cliente.getNome());
        assertEquals("Erro no valor do jogo", locacao.getValor(), jogo.getValor(), 0.0);
        assertTrue(new DataUtil().verificarDatasIguais(locacao.getRetirada(), new Date()));
    }

    @Test(expected = JogoSemEstoqueException.class)
    public void testeSemEstoque() throws Exception 
    {

        Cliente cliente = new Cliente("Antônio");
        Jogo jogo = new Jogo("Harry Potter", 15.00, 0);

        LocacaoService locacaoService = new LocacaoService();
        Locacao locacao;
        locacao = locacaoService.alugarJogo(cliente, jogo);
    }

    @Test(expected = ValorZeroException.class)
    public void testeValorZero() throws Exception 
    {

        Cliente cliente = new Cliente("Antônio");
        Jogo jogo = new Jogo("Harry Potter", 0, 2);

        LocacaoService locacaoService = new LocacaoService();
        Locacao locacao;
        locacao = locacaoService.alugarJogo(cliente, jogo);
    }
    
}