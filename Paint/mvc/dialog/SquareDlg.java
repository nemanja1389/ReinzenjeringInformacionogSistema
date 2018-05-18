package dialog;

import java.awt.BorderLayout;
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
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SquareDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtWidth;
	private JTextField txtCoorX;
	private JTextField txtCoorY;
	private JButton btnAreaColor;
	private JButton btnLineColor;
	private int x;
	private int y;
	private int sqwidth;
	private Color line;
	private Color area;

	/**
	 * Create the dialog.
	 */
	public SquareDlg(int x, int y, Color line, Color area) {
		this.x = x;
		this.y = y;
		this.line = line;
		this.area = area;
		setBounds(100, 100, 450, 250);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Up left corner", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(10, 11, 190, 90);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblCoordinateX = new JLabel("Coordinate X:");
				lblCoordinateX.setBounds(10, 23, 80, 14);
				panel.add(lblCoordinateX);
			}
			{
				JLabel lblCoordinateY = new JLabel("Coordinate Y:");
				lblCoordinateY.setBounds(10, 48, 80, 14);
				panel.add(lblCoordinateY);
			}
			{
				txtCoorX = new JTextField();
				txtCoorX.setBounds(100, 20, 65, 20);
				panel.add(txtCoorX);
				txtCoorX.setText(String.valueOf(getX()));
				txtCoorX.setColumns(10);
			}
			{
				txtCoorY = new JTextField();
				txtCoorY.setBounds(100, 45, 65, 20);
				panel.add(txtCoorY);
				txtCoorY.setText(String.valueOf(getY()));
				txtCoorY.setColumns(10);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Color", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(210, 11, 214, 90);
			contentPanel.add(panel);
			panel.setLayout(null);
			{
				JLabel lblLine = new JLabel("Line:");
				lblLine.setBounds(10, 23, 46, 14);
				panel.add(lblLine);
			}
			{
				JLabel lblArea = new JLabel("Area:");
				lblArea.setBounds(10, 48, 46, 14);
				panel.add(lblArea);
			}
			{
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
				btnLineColor.setBounds(66, 19, 115, 23);
				btnLineColor.setBackground(getLine());
				panel.add(btnLineColor);
			}
			{
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
				btnAreaColor.setBounds(66, 44, 115, 23);
				btnAreaColor.setBackground(getArea());
				panel.add(btnAreaColor);
			}
		}
		{
			JLabel lblWidth = new JLabel("Width:");
			lblWidth.setBounds(10, 112, 46, 14);
			contentPanel.add(lblWidth);
		}
		{
			txtWidth = new JTextField();
			txtWidth.setBounds(66, 109, 86, 20);
			contentPanel.add(txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new LineBorder(new Color(0, 0, 0)));
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
						
						if(txtWidth.getText().length() < 1){
							JOptionPane.showMessageDialog(null, "Unesite poluprecnik.", "Error", JOptionPane.ERROR_MESSAGE);
						}
						else
							try{
								Double.parseDouble(txtWidth.getText());
							}
						catch(NumberFormatException e1){
							JOptionPane.showMessageDialog(null, "Poluprecnik mora biti broj!", "Error", JOptionPane.ERROR_MESSAGE);
						}
						try {
							Integer.parseInt(txtCoorX.getText());
							setX(Integer.parseInt(txtCoorX.getText()));
							Integer.parseInt(txtCoorY.getText());
							setY(Integer.parseInt(txtCoorY.getText()));
							Integer.parseInt(txtWidth.getText());
							setSqwidth(Integer.parseInt(txtWidth.getText()));
						}
						catch (Exception e2) {
							// TODO: handle exception
						}
						if(getX() < 0) {
							JOptionPane.showMessageDialog(null, "X koordinata mora biti veca od 0!", "Error", JOptionPane.ERROR_MESSAGE);
						}else if(getY() < 0) {
							JOptionPane.showMessageDialog(null, "Y koordinata mora biti veca od 0!", "Error", JOptionPane.ERROR_MESSAGE);
						}else if(getSqwidth() < 0){
							JOptionPane.showMessageDialog(null, "Poluprecnik mora biti veci od 0!", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							try {
								Integer.parseInt(txtCoorX.getText());
								setX(Integer.parseInt(txtCoorX.getText()));
								Integer.parseInt(txtCoorY.getText());
								setY(Integer.parseInt(txtCoorY.getText()));
								Integer.parseInt(txtWidth.getText());
								setSqwidth(Integer.parseInt(txtWidth.getText()));
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
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

	public JButton getBtnAreaColor() {
		return btnAreaColor;
	}

	public void setBtnAreaColor(JButton btnAreaColor) {
		this.btnAreaColor = btnAreaColor;
	}

	public JButton getBtnLineColor() {
		return btnLineColor;
	}

	public void setBtnLineColor(JButton btnLineColor) {
		this.btnLineColor = btnLineColor;
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

	public int getSqwidth() {
		return sqwidth;
	}

	public void setSqwidth(int sqwidth) {
		this.sqwidth = sqwidth;
	}
	
}
