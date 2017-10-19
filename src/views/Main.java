package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import models.Data;
import models.Apuesta;
import models.Ficha;
import models.Persona;

import java.awt.Toolkit;
import java.util.ArrayList;

public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4537834553572450874L;
	private JPanel contentPane;
	private JPanel panel;
	private Data data = new Data();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,
							e.getMessage(), //Mensaje
							"Mensaje de Error", //Título
							JOptionPane.ERROR_MESSAGE); //Tipo de mensaje
					;
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/main/change.png")));
		setTitle("Sistema de Sorteos de Loter\u00EDa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 450);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmImportarData = new JMenuItem("Configuraci\u00F3n");
		mntmImportarData.setIcon(new ImageIcon(Main.class.getResource("/configuracion/configuration.png")));
		mntmImportarData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Configuracion configuracion=new Configuracion(getData());
				configuracion.setLocationRelativeTo(contentPane);
				configuracion.setModal(true);
				configuracion.setVisible(true);
				setData(configuracion.getData());
				//System.out.print(getData().getLoteria().getGanancia());
				printHome();
			}
		});
		mnArchivo.add(mntmImportarData);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.setIcon(new ImageIcon(Main.class.getResource("/main/sign-out-option.png")));
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		mnArchivo.add(mntmSalir);
		
		JMenu mnSobreNosotros = new JMenu("Ayuda");
		menuBar.add(mnSobreNosotros);
		
		JMenuItem mntmInformacin = new JMenuItem("Informaci\u00F3n");
		mntmInformacin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Informacion informacion=new Informacion();
				informacion.setLocationRelativeTo(contentPane);
				informacion.setModal(true);
				informacion.setVisible(true);
			}
		});
		
		JMenuItem mntmHelp = new JMenuItem("Help");
		mntmHelp.setIcon(new ImageIcon(Main.class.getResource("/main/question.png")));
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Ayuda ayuda=new Ayuda();
				ayuda.setLocationRelativeTo(contentPane);
				ayuda.setModal(true);
				ayuda.setVisible(true);
			}
		});
		mnSobreNosotros.add(mntmHelp);
		mntmInformacin.setIcon(new ImageIcon(Main.class.getResource("/main/graduate-hat.png")));
		mnSobreNosotros.add(mntmInformacin);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		JButton btnHome = new JButton("Home");
		btnHome.setIcon(new ImageIcon(Main.class.getResource("/main/house.png")));
		btnHome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				printHome();
			}
		});
		toolBar.add(btnHome);
		
		JButton btnApuestas = new JButton("Tickets de apuestas");
		btnApuestas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				printApuesta();
			}
		});
		btnApuestas.setIcon(new ImageIcon(Main.class.getResource("/main/price-tag.png")));
		toolBar.add(btnApuestas);
		
		JButton btnRealizarSorteo = new JButton("Realizar sorteo");
		btnRealizarSorteo.setIcon(new ImageIcon(Main.class.getResource("/main/gambler.png")));
		btnRealizarSorteo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				printRealizarSorteo();
			}
		});
		toolBar.add(btnRealizarSorteo);
		
		JButton btnConteoEstadstico = new JButton("Conteo estad\u00EDstico (pr\u00F3ximamente)");
		btnConteoEstadstico.setEnabled(false);
		btnConteoEstadstico.setIcon(new ImageIcon(Main.class.getResource("/main/bar-chart.png")));
		toolBar.add(btnConteoEstadstico);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		//Inicialización
		printHome();
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	void printHome(){
		panel.removeAll();
		panel.setLayout(new BorderLayout(0, 0));
		Home home = new Home();
		panel.add(home);
		panel.revalidate();
		panel.repaint();
	}
	void printRealizarSorteo(){
		panel.removeAll();
		Sorteo sorteo = new Sorteo();
		panel.add(sorteo);
		panel.revalidate();
		panel.repaint();
	}
	void printApuesta(){
		panel.removeAll();
		final ApuestaView apuestaView = new ApuestaView(data);
		apuestaView.btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (apuestaView.cedulaTextField.getText().toString().length() == 0){
					JOptionPane.showMessageDialog(
							contentPane,
							"El campo -Cédula- está vacío.");
				}
				else if (apuestaView.nombresTextField.getText().toString().length() == 0){
					JOptionPane.showMessageDialog(
							contentPane,
							"El campo -Nombres- está vacío.");
				}
				else if (apuestaView.apellidosTextField.getText().toString().length() == 0){
					JOptionPane.showMessageDialog(
							contentPane,
							"El campo -Apellidos- está vacío.");
				}
				else if (apuestaView.comboBox.getSelectedItem() == null){
					JOptionPane.showMessageDialog(
							contentPane,
							"No ha seleccionado ningún item a apostar.");
				}
				else {
					DefaultTableModel defaultTableModel = (DefaultTableModel) apuestaView.table.getModel();
					int id = apuestaView.table.getRowCount();
					if (apuestaView.table.getRowCount() == 0){
						id = 1;
					}
					else{
						id = Integer.parseInt(defaultTableModel.getValueAt(apuestaView.table.getRowCount()-1, 0).toString())+1;
					}
					defaultTableModel.addRow(new Object[1]);
					defaultTableModel.setValueAt(id+"", apuestaView.table.getRowCount()-1, 0);
					defaultTableModel.setValueAt(apuestaView.comboBox.getSelectedItem().toString()+"-"+apuestaView.nombreTextField.getText().toString(), apuestaView.table.getRowCount()-1, 1);
					defaultTableModel.setValueAt(apuestaView.cedulaTextField.getText().toString(), apuestaView.table.getRowCount()-1, 2);
					defaultTableModel.setValueAt(apuestaView.nombresTextField.getText().toString(), apuestaView.table.getRowCount()-1, 3);
					defaultTableModel.setValueAt(apuestaView.apellidosTextField.getText().toString(), apuestaView.table.getRowCount()-1, 4);
					defaultTableModel.setValueAt(apuestaView.montoSpinner.getValue().toString(), apuestaView.table.getRowCount()-1, 5);
					defaultTableModel.setValueAt(Double.parseDouble(apuestaView.montoSpinner.getValue().toString())*data.getLoteria().getGanancia(), apuestaView.table.getRowCount()-1, 6);
					apuestaView.table.setModel(defaultTableModel);
					apuestaView.idComboBox.addItem(id);
					apuestaView.cedulaTextField.setText("");
					apuestaView.nombresTextField.setText("");
					apuestaView.apellidosTextField.setText("");
					
					//Agregar a caché
					defaultTableModel = (DefaultTableModel) apuestaView.table.getModel();
					ArrayList<Apuesta> apuestas = new ArrayList<Apuesta>();
					Apuesta apuesta = new Apuesta();
					String ficha_aux;
					for (int i=0; i < defaultTableModel.getRowCount(); i++){
						apuesta = new Apuesta();
						apuesta.setId(Integer.parseInt(defaultTableModel.getValueAt(i, 0).toString()));
						ficha_aux = defaultTableModel.getValueAt(i, 1).toString();
						System.out.print(ficha_aux);
						apuesta.setFicha(new Ficha(Integer.parseInt(ficha_aux.substring(0,ficha_aux.indexOf("-"))),
								ficha_aux.substring(ficha_aux.indexOf("-") + 1)));
						apuesta.setPersona(new Persona(defaultTableModel.getValueAt(i, 2).toString().toString(),
								defaultTableModel.getValueAt(i, 3).toString().toString(),
								defaultTableModel.getValueAt(i, 4).toString().toString()));
						apuesta.setMonto(Double.parseDouble(defaultTableModel.getValueAt(i, 5).toString().toString()));
						apuesta.setEstado("pendiente");
						apuestas.add(apuesta);
					}
					data.setApuestas(apuestas);
				}
			}
		});
		panel.add(apuestaView);
		panel.revalidate();
		panel.repaint();
	}
	void printEstadistica(){
		panel.removeAll();
		Estadistica estadistica = new Estadistica();
		panel.add(estadistica);
		panel.revalidate();
		panel.repaint();
	}

}
