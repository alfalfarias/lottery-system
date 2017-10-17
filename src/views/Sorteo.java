package views;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sorteo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8148669044197962896L;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public Sorteo() {
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Lista de apuestas (Ordenadas por Ficha y Ganancias)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Detalles del Sorteo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 520, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 246, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.addGap(20))
		);
		
		JLabel lblSorteoGanador = new JLabel("Sorteo ganador:");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setColumns(10);
		
		JLabel lblNDeGanadores = new JLabel("N\u00B0 de Tickets premiados: ");
		
		JSpinner spinner = new JSpinner();
		
		JLabel lblTotalAPagar = new JLabel("Total a Pagar:");
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setColumns(10);
		
		JLabel lblGanancia = new JLabel("Ganancia:");
		lblGanancia.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Ganadores");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Premiacion premiacion=new Premiacion();
				premiacion.setLocationRelativeTo(panel);
				premiacion.setModal(true);
				premiacion.setVisible(true);
			}
		});
		btnNewButton.setIcon(new ImageIcon(Sorteo.class.getResource("/sorteo/businessman.png")));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblSorteoGanador)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblTotalAPagar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblNDeGanadores)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblGanancia)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSorteoGanador)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTotalAPagar)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNDeGanadores)
								.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblGanancia)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnOrdenarAleatoriamente = new JButton("Sorteo Aleatorio");
		btnOrdenarAleatoriamente.setIcon(new ImageIcon(Sorteo.class.getResource("/sorteo/shuffle.png")));
		
		JButton btnOrdenarPorMenor = new JButton("Sorteo por maximixar ganancias");
		btnOrdenarPorMenor.setIcon(new ImageIcon(Sorteo.class.getResource("/sorteo/graph.png")));
		
		JLabel lblNewLabel = new JLabel("Realiza el Sorteo de manera aleatoria.");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblNewLabel_1 = new JLabel("Selecciona la ficha que genere m\u00E1s ganancias a la empresa.");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 545, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnOrdenarAleatoriamente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnOrdenarPorMenor, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel))))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOrdenarAleatoriamente)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOrdenarPorMenor)
						.addComponent(lblNewLabel_1)))
		);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00FAmero", "Ficha", "Tickets", "Apostado", "Pago Total"
			}
		));
		scrollPane.setViewportView(table);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);

	}

}
