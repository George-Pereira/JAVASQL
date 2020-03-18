package dao;

import java.sql.SQLException;

import model.Pessoa;

public interface DaoPessoa 
{
	public String inserePessoa(Pessoa p) throws SQLException;
}
