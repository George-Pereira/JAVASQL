package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.PessoaControl;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PessoaView extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCPF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PessoaView frame = new PessoaView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PessoaView() {
		setTitle("Cadastro de Pessoas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 527);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNome = new JTextField();
		txtNome.setBounds(131, 73, 612, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblNome = new JLabel("NOME:");
		lblNome.setBounds(73, 76, 48, 14);
		contentPane.add(lblNome);
		
		txtCPF = new JTextField();
		txtCPF.setBounds(131, 118, 178, 20);
		contentPane.add(txtCPF);
		txtCPF.setColumns(10);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(84, 121, 48, 14);
		contentPane.add(lblCpf);
		
		JButton btnInsere = new JButton("INSERIR");
		btnInsere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnInsere.setBounds(371, 206, 135, 38);
		contentPane.add(btnInsere);
		
		ActionListener insercao = new PessoaControl(txtCPF, txtNome);
		btnInsere.addActionListener(insercao);
		txtCPF.setText("");
		txtNome.setText("");
	}
}
