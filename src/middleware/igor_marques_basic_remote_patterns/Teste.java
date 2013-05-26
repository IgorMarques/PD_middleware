package middleware.igor_marques_basic_remote_patterns;

import java.math.BigDecimal;
import java.rmi.RemoteException;

import org.tempuri.ArrayOfCServico;
import org.tempuri.CResultado;
import org.tempuri.CServico;
import org.tempuri.CalcPrecoPrazoWS;

public class Teste {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//http://www.correios.com.br/webServices/PDF/SCPP_manual_implementacao_calculo_remoto_de_precos_e_prazos.pdf
		String codServico = "40010"; //SEDEX sem contrato
		String cepOrigem = "82310510";
		String cepDestino = "05716090";
		calcPrazo(codServico,cepOrigem,cepDestino);
	}

	private static void calcPrazo(String nCdServico, String CepOrigem, String CepDestino) {
		CalcPrecoPrazoWS service = new CalcPrecoPrazoWS();
		CResultado retornoCorreios = service.getCalcPrecoPrazoWSSoap().calcPrecoPrazo("", "", "40010", "59040226", "50151250",
				"10", 1, new BigDecimal(16), new BigDecimal(2), new BigDecimal(11), new BigDecimal(16), "s", new BigDecimal(10), "s");
		ArrayOfCServico cservico = retornoCorreios.getServicos();
		String prazo = cservico.getCServico().get(0).getPrazoEntrega();
		
		
		System.out.println("retorno prazo entrega = " + prazo);

	
	}
}
