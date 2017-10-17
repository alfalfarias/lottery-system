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
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class Apuesta extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3692865805718441767L;
	private JTable table;
	private JTextField cedulaTextField;
	private JTextField nombresTextField;
	private JTextField apellidosTextField;
	private JSpinner montoSpinner;
	private JPanel panel;
	private JPanel panel_1;
	private JComboBox idComboBox;
	private JButton btnNewButton_1;
	private JLabel lblAnimalito;
	private JComboBox comboBox;
	private JLabel lblGanancia;
	private JTextField gananciaTextField;
	private JTextField nombreTextField;

	/**
	 * Create the panel.
	 */
	public Apuesta() {
		
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
		
		JButton btnNewButton = new JButton("Generar Ticket");
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
					defaultTableModel.setValueAt(comboBox.getSelectedItem().toString(), table.getRowCount()-1, 1);
					defaultTableModel.setValueAt(cedulaTextField.getText().toString(), table.getRowCount()-1, 2);
					defaultTableModel.setValueAt(nombresTextField.getText().toString(), table.getRowCount()-1, 3);
					defaultTableModel.setValueAt(apellidosTextField.getText().toString(), table.getRowCount()-1, 4);
					defaultTableModel.setValueAt(montoSpinner.getValue().toString(), table.getRowCount()-1, 5);
					defaultTableModel.setValueAt(montoSpinner.getValue().toString(), table.getRowCount()-1, 6);
					table.setModel(defaultTableModel);
					idComboBox.addItem(id+"");
					cedulaTextField.setText("");
					nombresTextField.setText("");
					apellidosTextField.setText("");
					//montoSpinner.setValue(1+"");
				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(Apuesta.class.getResource("/apuesta/invoice.png")));
		
		montoSpinner = new JSpinner();
		montoSpinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {

				gananciaTextField.setText(montoSpinner.getValue().toString());
			}
		});
		montoSpinner.setModel(new SpinnerNumberModel(new Float(1), new Float(1), null, new Float(1)));
		
		lblAnimalito = new JLabel("Selecci\u00F3n:");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"1111"}));
		
		lblGanancia = new JLabel("Ganancia:");
		
		gananciaTextField = new JTextField(montoSpinner.getValue().toString());
		gananciaTextField.setHorizontalAlignment(SwingConstants.RIGHT);
		gananciaTextField.setEditable(false);
		gananciaTextField.setColumns(10);
		
		nombreTextField = new JTextField();
		nombreTextField.setText("nombre");
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
		
		idComboBox = new JComboBox();
		
		btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.setIcon(new ImageIcon(Apuesta.class.getResource("/apuesta/delete.png")));
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
}
