package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CircleDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCoorX;
	private JTextField txtCoorY;
	private int x;
	private int y;
	private int radius;
	private Color line;
	private Color area;
	private JTextField txtRadius;
	private JButton btnAreaColor;
	private JButton btnLineColor;

	/**
	 * Create the dialog.
	 */
	public CircleDlg(int x, int y, Color line, Color area) {
		this.x = x;
		this.y = y;
		this.line = line;
		this.area = area;
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Center", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 190, 90);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCoordinateX = new JLabel("Coordinate X:");
		lblCoordinateX.setBounds(10, 25, 86, 14);
		panel.add(lblCoordinateX);
		
		JLabel lblCoordinateY = new JLabel("Coordinate Y:");
		lblCoordinateY.setBounds(10, 59, 86, 14);
		panel.add(lblCoordinateY);
		
		txtCoorX = new JTextField();
		txtCoorX.setBounds(106, 22, 66, 20);
		panel.add(txtCoorX);
		txtCoorX.setText(String.valueOf(getX()));
		txtCoorX.setColumns(10);
		
		txtCoorY = new JTextField();
		txtCoorY.setBounds(106, 53, 66, 20);
		panel.add(txtCoorY);
		txtCoorY.setText(String.valueOf(getY()));
		txtCoorY.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Color", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(210, 11, 214, 90);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblLineColor = new JLabel("Line color:");
		lblLineColor.setBounds(10, 25, 60, 14);
		panel_1.add(lblLineColor);
		
		JLabel lblAreaColor = new JLabel("Area color:");
		lblAreaColor.setBounds(10, 50, 70, 14);
		panel_1.add(lblAreaColor);
		
		btnAreaColor = new JButton("Area color");
		btnAreaColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setArea(JColorChooser.showDialog(null, "Izaberi boju", getArea()));
				if(getArea() != null){
					setArea(getArea());
					btnAreaColor.setBackground(getArea());
				}
			}
		});
		btnAreaColor.setBounds(90, 46, 100, 23);
		btnAreaColor.setBackground(getArea());
		panel_1.add(btnAreaColor);
		
		btnLineColor = new JButton("Line color");
		btnLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setLine(JColorChooser.showDialog(null, "Izaberi boju", getLine()));
				if(getLine() != null){
					setLine(getLine());
					btnLineColor.setBackground(getLine());
				}
			}
		});
		btnLineColor.setBounds(90, 21, 100, 23);
		btnLineColor.setBackground(getLine());
		panel_1.add(btnLineColor);
		
		JLabel lblRadius = new JLabel("Radius:");
		lblRadius.setBounds(10, 112, 46, 14);
		contentPanel.add(lblRadius);
		
		txtRadius = new JTextField();
		txtRadius.setBounds(66, 112, 86, 20);
		contentPanel.add(txtRadius);
		txtRadius.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(txtCoorX.getText().length() < 1){

							JOptionPane.showMessageDialog(null, "Please insert X coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(txtCoorX.getText());
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "X coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						// Y koordinata
						if(txtCoorY.getText().length() < 1){
							JOptionPane.showMessageDialog(null, "Please insert X coordinate.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(txtCoorY.getText());
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Y coordinate must be number!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						
						if(txtRadius.getText().length() < 1){
							JOptionPane.showMessageDialog(null, "Unesite poluprecnik.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(txtRadius.getText());
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Poluprecnik mora biti broj!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						try {
							Integer.parseInt(txtCoorX.getText());
							setX(Integer.parseInt(txtCoorX.getText()));
							Integer.parseInt(txtCoorY.getText());
							setY(Integer.parseInt(txtCoorY.getText()));
							Integer.parseInt(txtRadius.getText());
							setRadius(Integer.parseInt(txtRadius.getText()));
						}
						catch (Exception e2) {
							// TODO: handle exception
						}
						if(getX() < 0) {
							JOptionPane.showMessageDialog(null, "X koordinata mora biti veca od 0!", "Error", JOptionPane.ERROR_MESSAGE);
						}else if(getY() < 0) {
							JOptionPane.showMessageDialog(null, "Y koordinata mora biti veca od 0!", "Error", JOptionPane.ERROR_MESSAGE);
						}else if(getRadius() < 0){
							JOptionPane.showMessageDialog(null, "Poluprecnik mora biti veci od 0!", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							try {
								Integer.parseInt(txtCoorX.getText());
								setX(Integer.parseInt(txtCoorX.getText()));
								Integer.parseInt(txtCoorY.getText());
								setY(Integer.parseInt(txtCoorY.getText()));
								setVisible(false);
							}
							catch (Exception e2) {
								// TODO: handle exception
							}
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getLine() {
		return line;
	}

	public void setLine(Color line) {
		this.line = line;
	}

	public Color getArea() {
		return area;
	}

	public void setArea(Color area) {
		this.area = area;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	
}
