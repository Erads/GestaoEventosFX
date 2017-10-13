package controle;


import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import dominio.PessoaFisica;
import dominio.PessoaJuridica;
import dominio.Usuario;
import negocio.PessoaNegocio;


public class PessoaControle implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static PessoaFisica pessoaFisica;
	static PessoaJuridica pessoaJuridica;

	public static void cadastraPessoa(String tipo) {
		
		if(tipo.equals("f")) {
			pessoaFisica = new PessoaFisica();
			pessoaFisica.setNome("Murilo");
			pessoaFisica.setCPF("111.111.111-11");
			pessoaFisica.setRG("11111");
			pessoaFisica.setEndereco("Rua 1 numero 1 centro");
			pessoaFisica.setSexo("masculino");
			pessoaFisica.setTelefone("(62) 22222-2222");
			pessoaFisica.setCelular("(62)99999-9999");
			pessoaFisica.setTipo("fisica");
			pessoaFisica.setDataNascimento(new Date());
			pessoaFisica.setEmail("teste@teste.com");
			Usuario usuario = new Usuario();
			usuario.setLogin(pessoaFisica.getEmail());
			usuario.setSenha("senha");
		}else {
			pessoaJuridica = new PessoaJuridica();
			pessoaJuridica.setNome("empresa x");
			pessoaJuridica.setCNPJ("11.111.111/0001-11");
			pessoaJuridica.setInscricaoEstadual("123");
			pessoaJuridica.setEndereco("rua 1 numero 1 centro");
			pessoaJuridica.setRazaoSocial("empresa s/a");
			pessoaJuridica.setResponsavel("fulano");
			pessoaJuridica.setTelefone("(61) 22222-9898");
			pessoaJuridica.setCelular("(61) 98888-9898");
			pessoaJuridica.setTipo("juridica");
			pessoaJuridica.setEmail("empresa@empresa.com");
			Usuario usuario = new Usuario();
			usuario.setLogin(pessoaJuridica.getEmail());
			usuario.setSenha("senha");
		}
	}
	
	public void salvar(PessoaFisica pessoaFisica) throws ParseException {
		PessoaFisica pessoa = new PessoaFisica();
		cadastraPessoa("f");
		PessoaNegocio pessoaNegocio = new PessoaNegocio();
		String salvo = pessoaNegocio.salvar(this.pessoaFisica);
		System.out.println(salvo);
	}
	
	public void salvar(PessoaJuridica pessoaJuridica) {
		cadastraPessoa("j");
		PessoaNegocio pessoaNegocio = new PessoaNegocio();
		String salvo = pessoaNegocio.salvar(this.pessoaJuridica);
		System.out.println(salvo);
	}
	
		
}
