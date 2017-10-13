package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.dominio.PessoaFisica;
import modelo.dominio.PessoaJuridica;

public class PessoaDao {
	
	 ConnectionFactory connection = null;
	    private Connection con;
	    private Statement stm;
	    private PreparedStatement preparedStatement;

	    public PessoaDao() {
	        ConnectionFactory cf = new ConnectionFactory();
	        con = cf.getConnection();
	    }


	    String sqlSalvarPessoa = "INSERT INTO eventos.pessoa" +
	            "(nome,endereco,telefone,celular,email,tipo)" +
	            "VALUES(?,?,?,?,?,?)";
	    
	    String sqlSalvarPessoaFisica = "INSERT INTO eventos.pessoaFisica"
	    		+ "(CPF, RG, sexo, dataNascimento,id_pessoa)"
	    		+ "values(?,?,?,?,?)";
	    
	    String sqlSalvarPessoaJuridica = "INSERT INTO eventos.pessoaJuridica"
	    		+ "(razaoSocial, CNPJ, IE, responsavel, id_pessoa)"
	    		+ "values(?,?,?,?,?)";
	    
	    String sqlEditar = "UPDATE clientes SET nome = ?, sobrenome = ?," +
	            "cpf = ?, rg = ?, telefone = ?, celular = ?, endereco = ?," +
	            "email = ?  WHERE id = ?";

	    String sqlDeletar = "DELETE from clientes where id = ?";

	    String ultimoID = "SELECT LAST_INSERT_ID() INTO @pessoa";

	   

	    public List<PessoaFisica> listarPessoa() {
	        List<PessoaFisica> list = new ArrayList<Cliente>();
	        ResultSet res = null;

	        try {
	            stm = con.createStatement();
	            res = stm.executeQuery("SELECT * FROM clientes");

	            while (res.next()) {

	                Cliente cliente = new Cliente();

	                cliente.setId(res.getInt("id"));
	                cliente.setNome(res.getString("nome"));
	                cliente.setSobrenome(res.getString("sobrenome"));
	                cliente.setCpf(res.getString("cpf"));
	                cliente.setRg(res.getString("rg"));
	                cliente.setEndereco(res.getString("endereco"));
	                cliente.setTelefone(res.getString("telefone"));
	                cliente.setCelular(res.getString("celular"));
	                cliente.setEmail(res.getString("email"));

	                list.add(cliente);
	            }
	        } catch (SQLException e) {
	            System.out.println("Erro na consulta1:" + e.getMessage());
	        }
	        return list;
	    }
	    public String editar(Cliente cliente) throws SQLException {
	        String salvo = "falha";
	        try {
	            con.setAutoCommit(false);
	            preparedStatement = con.prepareStatement(sqlEditar);

	            preparedStatement.setString(1, cliente.getNome());
	            preparedStatement.setString(2, cliente.getSobrenome());
	            preparedStatement.setString(3, cliente.getCpf());
	            preparedStatement.setString(4, cliente.getRg());
	            preparedStatement.setString(5, cliente.getTelefone());
	            preparedStatement.setString(6, cliente.getCelular());
	            preparedStatement.setString(7, cliente.getEndereco());
	            preparedStatement.setString(8, cliente.getEmail());
	            preparedStatement.setInt(9, cliente.getId());

	            preparedStatement.executeUpdate();
	            con.commit();
	            salvo = "salvo";


	        }catch (Exception e){
	            System.out.println("erro ao atualizar " + e.getMessage());
	            salvo = e.getMessage();
	        }
	        return salvo;
	    }

	    public String deletar(Cliente cliente) throws SQLException {
	        String salvo = "falha";
	        try {
	            con.setAutoCommit(false);
	            preparedStatement = con.prepareStatement(sqlEditar);

	            preparedStatement.setString(1, cliente.getNome());
	            preparedStatement.setString(2, cliente.getSobrenome());
	            preparedStatement.setString(3, cliente.getCpf());
	            preparedStatement.setString(4, cliente.getRg());
	            preparedStatement.setString(5, cliente.getTelefone());
	            preparedStatement.setString(6, cliente.getCelular());
	            preparedStatement.setString(7, cliente.getEndereco());
	            preparedStatement.setString(8, cliente.getEmail());
	            preparedStatement.setInt(9, cliente.getId());

	            preparedStatement.executeUpdate();
	            con.commit();
	            salvo = "salvo";


	        }catch (Exception e){
	            System.out.println("erro ao atualizar " + e.getMessage());
	            salvo = e.getMessage();
	        }
	        return salvo;
	    }
	
	public String salvar(PessoaFisica pessoafisica) {
		 String salvo = "falha";


	        try {
	            con.setAutoCommit(false);
	            preparedStatement = con.prepareStatement(sqlSalvarPessoa);
	            preparedStatement.setString(1, pessoafisica.getNome());
	            preparedStatement.setString(2, pessoafisica.getEndereco());
	            preparedStatement.setString(3, pessoafisica.getTelefone());
	            preparedStatement.setString(4, pessoafisica.getCelular());
	            preparedStatement.setString(5, pessoafisica.getEmail());
	            preparedStatement.setString(6, pessoafisica.getTipo());

	            preparedStatement.executeUpdate();

	            //Grava as informações se caso de problema os dados não são gravados
	            con.commit();
	            salvo = "salvo";

	        } catch (SQLException e) {
	            if (con != null) {
	                try {
	                    System.err.print("Rollback efetuado na transação");
	                    con.rollback();
	                } catch (SQLException e2) {
	                    System.err.print("Erro na transação!" + e2);
	                    salvo = "\"Erro na transação!\"+e2";
	                }
	            }
	        } finally {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            con.setAutoCommit(true);
	        }

	        return salvo;
		
		return "Salvo";
	}
	
	public String salvar(PessoaJuridica pessoaJuridica) {
		//aqui vamos salvar o objeto no banco
		
		return "Salvo";
	}
	
	public int buscarUltimoID() {
		ResultSet res = null;
		List<PessoaFisica> list = new ArrayList<>();
        try {
            stm = con.createStatement();
            res = stm.executeQuery(ultimoID);

            while (res.next()) {

                PessoaFisica pessoa = new PessoaFisica();

                pessoa.setId(res.getInt("id"));
                
                list.add(pessoa);
            }
        } catch (SQLException e) {
            System.out.println("Erro na consulta1:" + e.getMessage());
        }
        return list.get(0).getId();
		
	}

}
