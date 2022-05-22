/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Pesist~encia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
package aplicacao_swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import fachada.Fachada;
import modelo.Contato;
import modelo.Telefone;

public class TelaTelefone {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JButton button;
	private JLabel label;
	private JLabel label_8;
	private JButton button_4;
	private JLabel label_3;
	private JTextField textField;
	private JButton button_1;
	private JButton button_2;

	/**
	 * Launch the application.
	 */
	//	public static void main(String[] args) {
	//		EventQueue.invokeLater(new Runnable() {
	//			public void run() {
	//				try {
	//					TelaTelefone window = new TelaTelefone();
	//					window.frame.setVisible(true);
	//				} catch (Exception e) {
	//					e.printStackTrace();
	//				}
	//			}
	//		});
	//	}

	/**
	 * Create the application.
	 */
	public TelaTelefone() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});
		frame.setTitle("Telefone");
		frame.setBounds(100, 100, 376, 415);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 47, 309, 193);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"numero", "nome"}
				));
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


		button = new JButton("Alterar telefone");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String numero = (String) table.getValueAt( table.getSelectedRow(), 0);
						String novo = JOptionPane.showInputDialog("novo numero");
						Fachada.alterarTelefone(numero,novo);
						label.setText("numero "+numero+ " alterado para "+novo);
						listagem();
					}
					else
						label.setText("selecione um numero para alterar");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(26, 266, 125, 23);
		frame.getContentPane().add(button);

		label = new JLabel("");
		label.setBackground(Color.RED);
		label.setBounds(26, 351, 302, 14);
		frame.getContentPane().add(label);

		label_8 = new JLabel("resultado");
		label_8.setBounds(26, 241, 309, 14);
		frame.getContentPane().add(label_8);

		button_4 = new JButton("Listar");
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button_4.setBounds(256, 13, 81, 23);
		frame.getContentPane().add(button_4);

		label_3 = new JLabel("digitos do numero:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_3.setBounds(26, 17, 117, 14);
		frame.getContentPane().add(label_3);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBounds(138, 15, 94, 20);
		frame.getContentPane().add(textField);

		button_1 = new JButton("Remover telefone do contato");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String numero = (String) table.getValueAt( table.getSelectedRow(), 0);
						String nome = (String) table.getValueAt( table.getSelectedRow(), 1);
						Fachada.removerTelefoneContato(numero,nome);
						label.setText("numero "+numero +"removido do contato "+nome);
						listagem();
					}
					else
						label.setText("selecione um numero/nome para remover");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(26, 292, 206, 23);
		frame.getContentPane().add(button_1);

		button_2 = new JButton("Adicionar telefone ao contato");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String nome = JOptionPane.showInputDialog("nome do contato");
						String numero = JOptionPane.showInputDialog("numero para adicionar ao contato "+nome);
						Fachada.adicionarTelefoneContato(numero,nome);
						label.setText("numero "+numero+" adicionado ao contato "+nome);
						listagem();
					}
					else
						label.setText("selecione um nome para adicionar telefone");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(26, 317, 205, 23);
		frame.getContentPane().add(button_2);

		frame.setVisible(true);
	}

	//*****************************
	public void listagem () {
		try{
			String digitos = textField.getText().trim();
			List<Telefone> lista = Fachada.listarTelefones(digitos);

			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			model.addColumn("numero");
			model.addColumn("nome");
			for(Telefone t : lista) {
				for(Contato c : t.getContatos())	{
					model.addRow(new String[]{t.getNumero(), c.getNome()});
				}
			}
			table.setModel(model);
			label_8.setText("resultados: "+lista.size()+ " telefones");
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}

	}
}
