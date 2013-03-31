package middleware.igor_marques_basic_remote_patterns;

/* Instruções
 * 
 * Crie uma classe que herda de Client Proxy para cada
 * objeto remoto do seu servidor.
 * 
 * Ex:	public class BankProxy extends ClientProxy
 * 		public class LibraryProxy extends ClientProxy	
 * 
 * Ela deverá possuir os mesmos métodos que seu objeto
 * remoto possui e para cada implementação dos mesmos, 
 * deverá simplesmente chamar um método invoke do Requestor
 * passando:
 * 		- tipo do objeto remoto,
 * 		- id do objeto remoto,
 * 		- nome do método,
 * 		- parâmetros passados para o método 
 * O requestor se encarregará do resto.
 * 
 * Ex:
 * 
 * 	public void sacar(int quantia){ 
 *		requestor.invoke("banco", bancoID, "sacar", quantia);
 *	}
 * 	
 * 	onde: 
 * 
 * 		"banco" é um string informando que objeto remoto é o "alvo"
 * 		bancoID é um string  com o ID da instancia do objeto que deseja
 * 		ser atingido.
 * 		"sacar" é um método do objeto do tipo Banco
 * 		quantia é o valor passado como parâmetro 
 * 
 * Ex:
 * 
 * 	public void devolverLivro(Socio socio, Livro livro){
 * 		requestor.invoke("biblioteca", bibliotecaID, "devolverLivro", socio, livro) 
 * 
 * 	}
 * 
 * 
 * Recomendo que as implementações dos proxyes fiquem em uma
 * pasta separada e não misturadas aos arquivos do middleware!
*/
 
public abstract class ClientProxy {
	
	Requestor requestor = Requestor.getInstance();
	private String objectID;
	
	public ClientProxy(){
		
	}

	/*FALTA IMPLEMENTAR
	 * 	-Método de obter referencia remota de objeto
	 * 	
	 */
	
	/* ARRUMAR:
	 * 	-Descobrir onde fica o requestor nessa história
	 */
}
