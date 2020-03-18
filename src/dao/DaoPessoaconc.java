package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import model.Pessoa;

public class DaoPessoaconc implements DaoPessoa
{
	private Connection conexao;
	public DaoPessoaconc() throws DaoException, SQLException 
	{
		DaoGenerica dao = new DaoGenericoconc();
		conexao = dao.getConnection();
	}
	@Override
	public String inserePessoa(Pessoa p) throws SQLException 
	{
		String sql = "{CALL sp_inserePessoa(?,?,?)}";
		CallableStatement chamada = conexao.prepareCall(sql);
		chamada.setString(1, p.getCpf());
		chamada.setString(2, p.getNome());
		chamada.registerOutParameter(3, Types.VARCHAR);
		chamada.execute();
		String retorno = chamada.getString(3);
		return retorno;
	}
	
}
