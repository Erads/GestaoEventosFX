package modelo;

import java.text.ParseException;

import modelo.dominio.PessoaFisica;
import modelo.dominio.PessoaJuridica;
import visao.controle.PessoaControle;

public class Principal {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		PessoaControle controle = new PessoaControle();
		PessoaFisica p = new PessoaFisica();
		PessoaJuridica pj = new PessoaJuridica();
		controle.salvar(pj);
		
	}

}
