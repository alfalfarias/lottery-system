package views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import models.Data;
import models.Ficha;
import models.Loteria;
import controllers.DataController;

import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class Configuracion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1144088891661838598L;
	/**
	 * 
	 */
	private final JPanel contentPanel = new JPanel();
	private JTextField nombreTextField;
	private JTable table;
	private JSpinner numeroSpinner;
	
	Data data = new Data();
	private JSpinner spinner;
	private JSpinner spinner_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Configuracion dialog = new Configuracion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Configuracion(Data data){
		this();
		this.data = data;
		refresh(data);
	}
	public Configuracion() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Configuracion.class.getResource("/configuracion/configuration.png")));
		setTitle("Sorteo - Configuraci\u00F3n");
		setBounds(100, 100, 510, 319);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Relaci\u00F3n de ganancia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Loter\u00EDa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Datos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 240, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 58, Short.MAX_VALUE))
		);
		
		JButton btnNewButton = new JButton("Exportar");
		btnNewButton.setIcon(new ImageIcon(Configuracion.class.getResource("/configuracion/export.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				//Filtro
				FileNameExtensionFilter filter = new FileNameExtensionFilter("DATA", "data");
				fileChooser.setFileFilter(filter);
				//Mostrar solo archivos y directorios
				fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int seleccion = fileChooser.showOpenDialog(contentPanel);
				if (seleccion == JFileChooser.APPROVE_OPTION) {
				   if (fileChooser.getCurrentDirectory().isDirectory()){
					   try {
						   DataController dataController = new DataController();
						   dataController.exportar(fileChooser.getSelectedFile().toString(), getData());
						   JOptionPane.showMessageDialog(
								   contentPanel,
								   "Data guardada con éxito.");
					      }
					   catch(IOException i) {
						   JOptionPane.showMessageDialog(
								   contentPanel,
								   "Error: "+i+".");
					   }
				   }
				   else{
					   JOptionPane.showMessageDialog(
							   contentPanel,
							   "El directorio no existe.");
				   }
				}
			}
		});
		
		JButton btnImportar = new JButton("Importar");
		btnImportar.setIcon(new ImageIcon(Configuracion.class.getResource("/configuracion/import.png")));
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				//Filtro
				fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("DATA", "data");
				fileChooser.setFileFilter(filter);
				int seleccion = fileChooser.showSaveDialog(contentPanel);
				if (seleccion == JFileChooser.APPROVE_OPTION)
				{
				   if (fileChooser.getSelectedFile().exists()){
					   try {
						   DataController dataController = new DataController();
						   refresh(dataController.importar(fileChooser.getSelectedFile().toString()));
						   numeroSpinner.setValue(Integer.parseInt((table.getValueAt(table.getRowCount()-1, 0).toString()))+1);
					   }catch(IOException i) {
						   JOptionPane.showMessageDialog(
								   contentPanel,
								   "Error: "+i.getMessage());
					   }catch(ClassNotFoundException c) {

						   JOptionPane.showMessageDialog(
								   contentPanel,
								   "Error: "+c.getMessage());
					   }
				   }
				   else{
					   JOptionPane.showMessageDialog(
							   contentPanel,
							   "El archivo no existe.");
				   }
				}
			}
		});
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(2)
					.addComponent(btnImportar, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
				.addComponent(btnImportar, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel lblUnidad = new JLabel("Apuesta:");
		
		JLabel lblGanancia = new JLabel("Ganancia:");
		
		spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		
		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(new Float(1), new Float(1), null, new Float(1)));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblGanancia)
						.addComponent(lblUnidad))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(spinner_1, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
						.addComponent(spinner, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUnidad)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGanancia)
						.addComponent(spinner_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Nuevo n\u00FAmero", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Lista de n\u00FAmeros", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
		);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_4.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "Nombre"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5861811070260572442L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				true, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(0).setMinWidth(25);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setMinWidth(50);
		scrollPane.setViewportView(table);
		
		JLabel lblNmero = new JLabel("N\u00FAmero:");
		
		numeroSpinner = new JSpinner();
		numeroSpinner.setToolTipText("");
		
		JLabel lblNombre = new JLabel("Nombre:");
		
		nombreTextField = new JTextField();
		nombreTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		nombreTextField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Cargar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numero_cont=0, nombre_cont=0;
				DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
				for (int i = 0; i < table.getRowCount(); i++){
					if (table.getValueAt(i, 0).toString().equals(numeroSpinner.getValue().toString()))
						numero_cont++;
					if (table.getValueAt(i, 1).toString().equals(nombreTextField.getText().toString()))
						nombre_cont++;
				}
				if (numero_cont>0){
					numeroSpinner.setValue((Integer.parseInt(defaultTableModel.getValueAt(table.getRowCount()-1, 0).toString())+1));
					numeroSpinner.requestFocus();
					JOptionPane.showMessageDialog(
							contentPanel,
							"El número ya ha sido agragado");
				}
				else if (nombre_cont>0){
					nombreTextField.requestFocus();
					JOptionPane.showMessageDialog(
							contentPanel,
							"El nombre ya ha sido agragado");
				}
				else if (nombreTextField.getText().toString().length() == 0) {
					nombreTextField.requestFocus();
					JOptionPane.showMessageDialog(
							contentPanel,
							"El campo -Nombre- está vacío.");
				}
				else{
					defaultTableModel.addRow(new Object[1]);
					defaultTableModel.setValueAt(numeroSpinner.getValue().toString(), table.getRowCount()-1, 0);
					defaultTableModel.setValueAt(nombreTextField.getText().toString(), table.getRowCount()-1, 1);
					table.setModel(defaultTableModel);
					nombreTextField.setText("");
					numeroSpinner.setValue((Integer.parseInt(defaultTableModel.getValueAt(table.getRowCount()-1, 0).toString())+1));
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(Configuracion.class.getResource("/configuracion/plus.png")));
		
		JButton btnNewButton_2 = new JButton("Borrar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getRowCount() > 0){
					((DefaultTableModel) table.getModel()).removeRow(table.getRowCount()-1);
				}
				else{
					nombreTextField.requestFocus();
					JOptionPane.showMessageDialog(
							   contentPanel,
							   "No hay números registrados.");
				}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(Configuracion.class.getResource("/configuracion/negative.png")));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblNmero)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(numeroSpinner, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nombreTextField, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 90, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNmero)
						.addComponent(numeroSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(nombreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)))
		);
		panel_3.setLayout(gl_panel_3);
		panel_1.setLayout(gl_panel_1);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Guardar cambios");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Loteria loteria = new Loteria();
						loteria.setGanancia(Double.parseDouble(spinner_1.getValue().toString()));
						DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
						ArrayList<Ficha> fichas = new ArrayList<Ficha>();
						Ficha ficha;
						for (int i=0; i < defaultTableModel.getRowCount(); i++){
							ficha = new Ficha();
							ficha.setNumero(Integer.parseInt(defaultTableModel.getValueAt(i, 0).toString()));
							ficha.setNombre(defaultTableModel.getValueAt(i, 1).toString());
							fichas.add(ficha);
							//System.out.print("id: "+fichas.get(i).getNumero()+" \t nombre: "+fichas.get(i).getNombre()+"\n"); 
						}
						loteria.setFichas(fichas);
						loteria.setGanancia(Double.parseDouble(spinner_1.getValue().toString()));
						data.setLoteria(loteria);
						JOptionPane.showMessageDialog(
								   contentPanel,
								   "Cambios guardados exitosamente.");
					}
				});
				okButton.setIcon(new ImageIcon(Configuracion.class.getResource("/configuracion/save.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setIcon(new ImageIcon(Configuracion.class.getResource("/configuracion/cancel.png")));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void setData(Data data){
		this.data = data;
	}
	public Data getData(){
		return this.data;
	}
	public void refresh(Data data){
		spinner_1.setValue(data.getLoteria().getGanancia());
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		for (Ficha ficha: data.getLoteria().getFichas()){
			defaultTableModel.addRow(new Object[1]);
			defaultTableModel.setValueAt(ficha.getNumero(), defaultTableModel.getRowCount()-1, 0);
			defaultTableModel.setValueAt(ficha.getNombre(), defaultTableModel.getRowCount()-1, 1);
			//System.out.print("id: "+ficha.getNumero()+" \t nombre: "+ficha.getNombre()+"\n"); 
		}
		table.setModel(defaultTableModel);
		nombreTextField.setText("");
	}
}
