/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Pesist~encia de Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

package aplicacao_swing;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class TelaContato {
	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel label_3;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton button;
	private JButton button_1;
	private JLabel label_4;
	private JTextField textField_3;
	private JLabel label_5;
	private JTextField textField;
	private JButton button_4;
	private JLabel label_6;
	private JTextField textField_4;
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
	public TelaContato() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Contato");
		frame.setBounds(100, 100, 851, 377);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				listagem();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 39, 788, 174);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
				new Object[][] {},
				new String[] {"nome", "nascimento", "idade", "endereco", "numeros"}
				));
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(21, 313, 711, 14);
		frame.getContentPane().add(label);

		label_3 = new JLabel("");
		label_3.setBounds(-138, 213, 538, 14);
		frame.getContentPane().add(label_3);

		label_1 = new JLabel("nome:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(21, 252, 71, 14);
		frame.getContentPane().add(label_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBackground(Color.WHITE);
		textField_1.setBounds(64, 249, 134, 20);
		frame.getContentPane().add(textField_1);

		label_2 = new JLabel("nascimento:");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_2.setBounds(208, 252, 71, 14);
		frame.getContentPane().add(label_2);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(277, 249, 89, 20);
		frame.getContentPane().add(textField_2);

		button_1 = new JButton("Criar novo contato");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(textField_1.getText().isEmpty() || textField_2.getText().isEmpty()
							|| textField_3.getText().isEmpty()) {
						label.setText("campo vazio");
						return;
					}
					String nome = textField_1.getText();
					String nascimento = textField_2.getText();
					String logradouro = textField_3.getText();
					String bairro = textField_4.getText();
					Fachada.criarContato(nome, nascimento,logradouro,bairro);
					label.setText("contato criado");
					listagem();
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(324, 279, 152, 23);
		frame.getContentPane().add(button_1);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(324, 10, 89, 23);
		frame.getContentPane().add(button);

		label_4 = new JLabel("logradouro:");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_4.setBounds(376, 252, 71, 14);
		frame.getContentPane().add(label_4);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(446, 249, 188, 20);
		frame.getContentPane().add(textField_3);

		label_5 = new JLabel("caracteres do nome:");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_5.setBounds(21, 14, 134, 14);
		frame.getContentPane().add(label_5);

		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField.setColumns(10);
		textField.setBackground(Color.WHITE);
		textField.setBounds(165, 13, 134, 20);
		frame.getContentPane().add(textField);

		button_4 = new JButton("Ver contato no mapa");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String endereco = (String) table.getValueAt( table.getSelectedRow(), 3);
						
						//abrir navegador
						String link = endereco.replaceAll(" ", "+"); // substitui espaço por "+"
						URI uri = new URI("https://www.google.com/maps/search/?api=1&query=" + link);
						Desktop.getDesktop().browse(uri);
					}
					else
						label.setText("selecione um contato");
				}
				catch(Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_4.setBounds(604, 213, 204, 23);
		frame.getContentPane().add(button_4);
		
		label_6 = new JLabel("bairro:");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_6.setBounds(644, 252, 71, 14);
		frame.getContentPane().add(label_6);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_4.setColumns(10);
		textField_4.setBounds(693, 249, 116, 20);
		frame.getContentPane().add(textField_4);
		
		button_2 = new JButton("Apagar contato selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0){
						String nome = (String) table.getValueAt( table.getSelectedRow(), 0);

						Fachada.apagarContato(nome);
						label.setText("contato apagado");
						listagem();
					}
					else {
						label.setText("selecione um contato");
					}
				} catch(Exception ex)  {
					
					label.setText(ex.getMessage());
					
				}
				
			}
		});
		
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(376, 213, 218, 23);
		frame.getContentPane().add(button_2);

		frame.setVisible(true);

	}

	public void listagem() {
		try{
			String caracteres = textField.getText().trim();
			List<Contato> lista = Fachada.listarContatos(caracteres);
		
			//model contem todas as linhas e colunas da tabela
			DefaultTableModel model = new DefaultTableModel();
			//criar as colunas (0,1,2) da tabela
			model.addColumn("nome");
			model.addColumn("nascimento");
			model.addColumn("idade");
			model.addColumn("endereco");
			model.addColumn("numeros");

			//criar as linhas da tabela
			String texto;
			ArrayList<String> numeros;
			for(Contato c : lista) {
				if(c.getTelefones().isEmpty())
					texto = "sem telefone";
				else {
					numeros = new ArrayList<>();
					for(Telefone t : c.getTelefones()) 
						numeros.add(t.getNumero());
					texto = String.join(",", numeros);
				}
				model.addRow(new Object[]{c.getNome(),c.getNascimentoStr(),c.getIdade(),c.getEnderecoStr(), texto});
			}
			table.setModel(model);
			label_3.setText("resultados: "+lista.size()+ " contatos");
			
			//redimensionar a coluna 2
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
			table.getColumnModel().getColumn(1).setMinWidth(50); //nascimento	
			table.getColumnModel().getColumn(2).setMinWidth(50); //idade
			table.getColumnModel().getColumn(3).setMinWidth(200);//endereco
			table.getColumnModel().getColumn(4).setMinWidth(300);//telefones
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
		}
		catch(Exception erro){
			label.setText(erro.getMessage());
		}
	}
}
