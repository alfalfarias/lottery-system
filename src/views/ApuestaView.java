package views;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;

import models.Apuesta;
import models.Data;
import models.Ficha;
import models.Persona;

public class ApuestaView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3692865805718441767L;
	public JTable table;
	public JTextField cedulaTextField;
	public JTextField nombresTextField;
	public JTextField apellidosTextField;
	public JSpinner montoSpinner;
	public JPanel panel;
	public JPanel panel_1;
	public JComboBox<Integer> idComboBox;
	public JButton btnNewButton_1;
	public JLabel lblAnimalito;
	public JComboBox<Integer> comboBox;
	public JLabel lblGanancia;
	public JTextField gananciaTextField;
	public JTextField nombreTextField;
	
	public Data data = new Data();
	JButton btnNewButton;
	/**
	 * Create the panel.
	 */
	public ApuestaView(Data data){
		this();
		this.data = (data);
		refresh();
	}
	public ApuestaView() {
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registrar nuevo Ticket", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de Apuestas (Tickets)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))
					.addGap(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
					.addGap(8))
		);
		
		JLabel lblNombres = new JLabel("N\u00B0 C\u00E9dula:");
		
		cedulaTextField = new JTextField();
		cedulaTextField.setColumns(10);
		
		JLabel lblNombres_1 = new JLabel("Nombres:");
		
		nombresTextField = new JTextField();
		nombresTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Apellidos:");
		
		apellidosTextField = new JTextField();
		apellidosTextField.setColumns(10);
		
		JLabel lblMonto = new JLabel("Monto:");
		
		btnNewButton = new JButton("Generar Ticket");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (cedulaTextField.getText().toString().length() == 0){
					JOptionPane.showMessageDialog(
							panel_1,
							"El campo -Cédula- está vacío.");
				}
				else if (nombresTextField.getText().toString().length() == 0){
					JOptionPane.showMessageDialog(
							panel_1,
							"El campo -Nombres- está vacío.");
				}
				else if (apellidosTextField.getText().toString().length() == 0){
					JOptionPane.showMessageDialog(
							panel_1,
							"El campo -Apellidos- está vacío.");
				}
				else if (comboBox.getSelectedItem() == null){
					JOptionPane.showMessageDialog(
							panel_1,
							"No ha seleccionado ningún item a apostar.");
				}
				else {
					DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
					int id = table.getRowCount();
					if (table.getRowCount() == 0){
						id = 1;
					}
					else{
						id = Integer.parseInt(defaultTableModel.getValueAt(table.getRowCount()-1, 0).toString())+1;
					}
					defaultTableModel.addRow(new Object[1]);
					defaultTableModel.setValueAt(id+"", table.getRowCount()-1, 0);
					defaultTableModel.setValueAt(comboBox.getSelectedItem().toString()+"-"+nombreTextField.getText().toString(), table.getRowCount()-1, 1);
					defaultTableModel.setValueAt(cedulaTextField.getText().toString(), table.getRowCount()-1, 2);
					defaultTableModel.setValueAt(nombresTextField.getText().toString(), table.getRowCount()-1, 3);
					defaultTableModel.setValueAt(apellidosTextField.getText().toString(), table.getRowCount()-1, 4);
					defaultTableModel.setValueAt(montoSpinner.getValue().toString(), table.getRowCount()-1, 5);
					defaultTableModel.setValueAt(Double.parseDouble(montoSpinner.getValue().toString())*data.getLoteria().getGanancia(), table.getRowCount()-1, 6);
					table.setModel(defaultTableModel);
					idComboBox.addItem(id);
					cedulaTextField.setText("");
					nombresTextField.setText("");
					apellidosTextField.setText("");
					
					//Agregar a caché
					defaultTableModel = (DefaultTableModel) table.getModel();
					ArrayList<Apuesta> apuestas = new ArrayList<Apuesta>();
					Apuesta apuesta = new Apuesta();
					String ficha_aux;
					for (int i=0; i < defaultTableModel.getRowCount(); i++){
						apuesta = new Apuesta();
						apuesta.setId(Integer.parseInt(defaultTableModel.getValueAt(i, 0).toString()));
						ficha_aux = defaultTableModel.getValueAt(i, 1).toString();
						//System.out.print(ficha_aux);
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
		btnNewButton.setIcon(new ImageIcon(ApuestaView.class.getResource("/apuesta/invoice.png")));
		
		montoSpinner = new JSpinner();
		montoSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				gananciaTextField.setText(data.getLoteria().getGanancia()*Double.parseDouble(montoSpinner.getValue().toString())+"");
			}
		});
		montoSpinner.setModel(new SpinnerNumberModel(new Float(1), new Float(1), null, new Float(1)));
		
		lblAnimalito = new JLabel("Selecci\u00F3n:");
		
		comboBox = new JComboBox<Integer>();
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (Ficha ficha: data.getLoteria().getFichas()){
					if (ficha.getNumero() == Integer.parseInt(comboBox.getSelectedItem().toString())){
						nombreTextField.setText(ficha.getNombre());
					}
				}
			}
		});
		
		lblGanancia = new JLabel("Ganancia:");
		
		gananciaTextField = new JTextField(montoSpinner.getValue().toString());
		gananciaTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		gananciaTextField.setEditable(false);
		gananciaTextField.setColumns(10);
		
		nombreTextField = new JTextField();
		nombreTextField.setEditable(false);
		nombreTextField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(16)
							.addComponent(apellidosTextField, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNombres)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cedulaTextField, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNombres_1)
							.addGap(16)
							.addComponent(nombresTextField, GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblAnimalito)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, 0, 50, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(nombreTextField, GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblMonto)
							.addGap(18)
							.addComponent(montoSpinner, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblGanancia)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(gananciaTextField, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombres)
								.addComponent(cedulaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAnimalito)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(nombreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNombres_1)
								.addComponent(lblMonto)
								.addComponent(montoSpinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(nombresTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(apellidosTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel)
								.addComponent(lblGanancia)
								.addComponent(gananciaTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0 Ticket", "Ficha", "Cedula", "Nombres", "Apellidos", "Apuesta", "Ganancia"
			}
		));
		scrollPane.setViewportView(table);
		
		idComboBox = new JComboBox<Integer>();
		
		btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.setIcon(new ImageIcon(ApuestaView.class.getResource("/apuesta/delete.png")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (idComboBox.getSelectedItem() == null){
					JOptionPane.showMessageDialog(
							panel_1,
							"No hay ningún ticket seleccionado.");
				}
				else{
					for (int i=0; i<table.getRowCount(); i++){
						if (((DefaultTableModel) table.getModel()).getValueAt(i, 0).toString().equals(idComboBox.getSelectedItem().toString())){
							((DefaultTableModel) table.getModel()).removeRow(i);
							idComboBox.removeItem(idComboBox.getSelectedItem());
						}
					}
				}
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(idComboBox, 0, 371, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(idComboBox, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		setLayout(groupLayout);

	}
	public Data getData() {
		return this.data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public void refresh(){
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		defaultTableModel.setRowCount(0);
		for (models.Apuesta apuesta: getData().getApuestas()){
			defaultTableModel.addRow(new Object[1]);
			defaultTableModel.setValueAt(apuesta.getId(), defaultTableModel.getRowCount()-1, 0);
			defaultTableModel.setValueAt(apuesta.getFicha().getNumero()+"-"+apuesta.getFicha().getNombre(), defaultTableModel.getRowCount()-1, 1);
			defaultTableModel.setValueAt(apuesta.getPersona().getCedula(), defaultTableModel.getRowCount()-1, 2);
			defaultTableModel.setValueAt(apuesta.getPersona().getNombres(), defaultTableModel.getRowCount()-1, 3);
			defaultTableModel.setValueAt(apuesta.getPersona().getApellidos(), defaultTableModel.getRowCount()-1, 4);
			defaultTableModel.setValueAt(apuesta.getMonto(), defaultTableModel.getRowCount()-1, 5);
			defaultTableModel.setValueAt((apuesta.getMonto()*getData().getLoteria().getGanancia()), defaultTableModel.getRowCount()-1, 6);
			idComboBox.addItem(apuesta.getId());
		}
		table.setModel(defaultTableModel);
		for (Ficha ficha: getData().getLoteria().getFichas()){
			comboBox.addItem(ficha.getNumero());
		}
		for (Ficha ficha: data.getLoteria().getFichas()){
			if (ficha.getNumero() == Integer.parseInt(comboBox.getSelectedItem().toString())){
				nombreTextField.setText(ficha.getNombre());
			}
		}
		gananciaTextField.setText(data.getLoteria().getGanancia()+"");
	}
	
}
