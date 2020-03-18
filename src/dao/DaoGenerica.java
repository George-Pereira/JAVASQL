package dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface DaoGenerica 
{
	public Connection getConnection() throws DaoException, SQLException;
}
