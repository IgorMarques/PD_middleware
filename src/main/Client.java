package main;

import library.core.Associate;
import library.core.Book;
import library.core.Librarian;

public class Client {
	private Librarian librarian;
	private Server server;

	public Client(Librarian librarian, Server server){
		this.librarian = librarian;
		this.server = server; 
		
	}
	
	public Librarian getLibrarian() {
		return librarian;
	}

	public void setLibrarian(Librarian librarian) {
		this.librarian = librarian;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
	public void login(){
		if ( this.server.login(this.librarian) ){
			System.out.println("-Logado com sucesso!");
		}
		else{
			System.out.println("-Login falhou!");
		}
	}
	
	public void logoff(){
		if ( this.server.logoff(this.librarian) ){
			System.out.println("-Logoff realizado com sucesso!");
		}
		else{
			System.out.println("-Logoff falhou!");
		}
	}
	
	public void getBookStatus(){
		this.server.bookStatus();
	}
	
	
	public void getBook(Book book, Associate associate){
		if (this.server.lendBook(book, associate)){
			System.out.println("-Livro emprestado com sucesso!");
		}
		else{
			System.out.println("-Livro n?o emprestado!");
		}
	}
	
	public void returnBook(Book book, Associate associate){
		if (this.server.returnBook(book, associate)){
			System.out.println("-Livro retornado com sucesso!");
		}
		else{
			System.out.println("-Livro n?o retornado!");
		}
	}

}
