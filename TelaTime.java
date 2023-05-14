package Telas;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import Conexao.ModuloConexao;

public class TelaTime extends JFrame {

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtAbreviacao;
	private JLabel lblAbreviacao;
	private JButton btnAlterar;
	private JLabel lblFoto;
	
	//instanciar objeto para o fluxo de bytes
	private FileInputStream fis;
	
	//variavel global pra armazenar o tamanho da imagem
	private int tamanho;
	private JButton btnRemover;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaTime frame = new TelaTime();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void cadastrarTime() {
		try {
			String teste, teste2;
			String sql = "insert into time (nome, abreviacao) values (?,?)";
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtNome.getText());
			pst.setString(2, txtAbreviacao.getText());
			teste = txtNome.getText();
			teste2 = txtAbreviacao.getText();
			JOptionPane.showMessageDialog(null, teste+" "+teste2);
			//validação dos campos obrigatorios
			if (txtNome.getText().isEmpty() || txtAbreviacao.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			} else {
				int adicionado = pst.executeUpdate();
				System.out.println(adicionado);
				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
					txtNome.setText(null);
					txtAbreviacao.setText(null);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void consultarTime() {
		try {
			String sql = "select * from time where nome = ?";
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtNome.getText());
			rs = pst.executeQuery();

			if (rs.next()) {
				txtAbreviacao.setText(rs.getString(2));
			} else {
				JOptionPane.showMessageDialog(null, "Time não cadastrado");
				txtAbreviacao.setText(null);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	
	
	private void alterarTime() {
		String sql = "update time set abreviacao=? where nome=?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, txtAbreviacao.getText());
			pst.setString(2, txtNome.getText());
			
			if (txtNome.getText().isEmpty() || txtAbreviacao.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
			} else {
				int adicionado = pst.executeUpdate();
				System.out.println(adicionado);
				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso");
					txtNome.setText(null);
					txtAbreviacao.setText(null);
				}
			}
		} catch (Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private void removerTime() {
		int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover?");
		if (confirma == JOptionPane.YES_OPTION) {
			String sql = "delete from time where nome=?";
			try {
				pst = conexao.prepareStatement(sql);
				pst.setString(1, txtNome.getText());
				int apagado = pst.executeUpdate();
				if (apagado > 0) {
					JOptionPane.showMessageDialog(null, "Time removido com sucesso");
					txtNome.setText(null);
					txtAbreviacao.setText(null);
				}
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
			}
		} else {
			
		}
	}
	
	/*private void carregarFoto() {
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("Selecionar arquivo");
		jfc.setFileFilter(new FileNameExtensionFilter("Arquivo de imagens(*.PNG, *.JPG, *.JPEG)", "png","jpg", "jpeg"));
		int resultado = jfc.showOpenDialog(this);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			try {
				fis = new FileInputStream(jfc.getSelectedFile());
				tamanho = (int) jfc.getSelectedFile().length();
				Image foto = ImageIO.read(jfc.getSelectedFile()).getScaledInstance(lblFoto.getWidth(), lblFoto.getHeight(), Image.SCALE_SMOOTH);
				lblFoto.setIcon(new ImageIcon(foto));
				lblFoto.updateUI();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	
	}*/
	
	public TelaTime() {

		conexao = ModuloConexao.conector();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 874, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtNome = new JTextField();
		txtNome.setBounds(190, 38, 148, 36);
		contentPane.add(txtNome);
		txtNome.setColumns(10);

		txtAbreviacao = new JTextField();
		txtAbreviacao.setColumns(10);
		txtAbreviacao.setBounds(190, 101, 148, 36);
		contentPane.add(txtAbreviacao);

		JButton btnTime = new JButton("Inserir");
		btnTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrarTime();
			}
		});
		btnTime.setBounds(10, 236, 111, 41);
		contentPane.add(btnTime);

		JButton btnRead = new JButton("Buscar");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarTime();
			}
		});
		btnRead.setBounds(142, 236, 111, 41);
		contentPane.add(btnRead);

		JLabel lblNome = new JLabel("* Nome");
		lblNome.setBounds(127, 49, 45, 13);
		contentPane.add(lblNome);

		lblAbreviacao = new JLabel("* Abreviação");
		lblAbreviacao.setBounds(109, 112, 71, 13);
		contentPane.add(lblAbreviacao);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alterarTime();
			}
		});
		btnAlterar.setBounds(273, 236, 111, 41);
		contentPane.add(btnAlterar);
		
		/*JButton btnCarregar = new JButton("Carregar foto");
		btnCarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//carregarFoto();
			}
		});
		btnCarregar.setBounds(377, 101, 128, 36);
		contentPane.add(btnCarregar);
		
		lblFoto = new JLabel("");
		lblFoto.setIcon(new ImageIcon(TelaTime.class.getResource("/Icones/camera.png")));
		lblFoto.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblFoto.setBounds(556, 26, 256, 256);
		contentPane.add(lblFoto);*/
		
		btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerTime();
			}
		});
		btnRemover.setBounds(405, 238, 102, 36);
		contentPane.add(btnRemover);
		
		

	}
}
