package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.DaoException;
import dao.DaoPessoa;
import dao.DaoPessoaconc;
import model.Pessoa;

public class PessoaControl implements ActionListener 
{
	private JTextField cpf;
	private JTextField nome;
	
	public PessoaControl(JTextField cpf, JTextField nome) 
	{
		this.cpf = cpf;
		this.nome = nome;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try 
		{
			inserePessoa(cpf.getText(), nome.getText());
		} catch (DaoException | SQLException e1) 
		{
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void inserePessoa(String cpf, String nome) throws DaoException, SQLException 
	{
		Pessoa p = new Pessoa(cpf, nome);
		DaoPessoa dp = new DaoPessoaconc();
		String ret = dp.inserePessoa(p);
		JOptionPane.showMessageDialog(null, ret, "Aviso", JOptionPane.INFORMATION_MESSAGE);
	}
}
