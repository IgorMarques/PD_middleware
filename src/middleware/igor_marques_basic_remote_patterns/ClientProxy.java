package middleware.igor_marques_basic_remote_patterns;

/* Instru��es
 * 
 * Crie uma classe que herda de Client Proxy para cada
 * objeto remoto do seu servidor.
 * 
 * Ex:	public class BankProxy extends ClientProxy
 * 		public class LibraryProxy extends ClientProxy	
 * 
 * Ela dever� possuir os mesmos m�todos que seu objeto
 * remoto possui e para cada implementa��o dos mesmos, 
 * dever� simplesmente chamar um m�todo invoke do Requestor
 * passando:
 * 		- tipo do objeto remoto,
 * 		- id do objeto remoto,
 * 		- nome do m�todo,
 * 		- par�metros passados para o m�todo 
 * O requestor se encarregar� do resto.
 * 
 * Ex:
 * 
 * 	public void sacar(int quantia){ 
 *		requestor.invoke("banco", bancoID, "sacar", quantia);
 *	}
 * 	
 * 	onde: 
 * 
 * 		"banco" � um string informando que objeto remoto � o "alvo"
 * 		bancoID � um string  com o ID da instancia do objeto que deseja
 * 		ser atingido.
 * 		"sacar" � um m�todo do objeto do tipo Banco
 * 		quantia � o valor passado como par�metro 
 * 
 * Ex:
 * 
 * 	public void devolverLivro(Socio socio, Livro livro){
 * 		requestor.invoke("biblioteca", bibliotecaID, "devolverLivro", socio, livro) 
 * 
 * 	}
 * 
 * 
 * Recomendo que as implementa��es dos proxyes fiquem em uma
 * pasta separada e n�o misturadas aos arquivos do middleware!
*/
 
public abstract class ClientProxy {
	
	Requestor requestor = Requestor.getInstance();
	private String objectID;
	
	public ClientProxy(){
		
	}

	/*FALTA IMPLEMENTAR
	 * 	-M�todo de obter referencia remota de objeto
	 * 	
	 */
	
	/* ARRUMAR:
	 * 	-Descobrir onde fica o requestor nessa hist�ria
	 */
}
