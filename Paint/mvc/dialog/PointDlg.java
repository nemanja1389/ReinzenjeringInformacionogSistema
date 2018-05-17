package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.management.StringValueExp;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PointDlg extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCoorX;
	private JTextField txtCoorY;
	private Color pointColor;
	private int x;
	private int y;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Color c = Color.BLACK;
			int x = 0;
			int y = 0;
			PointDlg dialog = new PointDlg(c, x, y);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Create the dialog.
	 */
	public PointDlg(Color pointColor, int x, int y) {
		this.pointColor = pointColor;
		this.x = x;
		this.y = y;
		setTitle("Point details");
		setBounds(100, 100, 260, 210);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCoordinataX = new JLabel("Coordinate X:");
		lblCoordinataX.setBounds(10, 11, 82, 14);
		contentPanel.add(lblCoordinataX);
		
		txtCoorX = new JTextField();
		txtCoorX.setBounds(102, 8, 86, 20);
		contentPanel.add(txtCoorX);
		txtCoorX.setColumns(10);
		txtCoorX.setText(String.valueOf(getX()));
		
		JLabel lblCoordinataY = new JLabel("Coordinate Y:");
		lblCoordinataY.setBounds(10, 36, 82, 14);
		contentPanel.add(lblCoordinataY);
		
		txtCoorY = new JTextField();
		txtCoorY.setBounds(102, 33, 86, 20);
		contentPanel.add(txtCoorY);
		txtCoorY.setColumns(10);
		txtCoorY.setText(String.valueOf(getY()));
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(10, 75, 46, 14);
		contentPanel.add(lblColor);
		
		JButton btnColor = new JButton("Color");
		btnColor.setBackground(pointColor);
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 setPointColor(JColorChooser.showDialog(null, "Izaberi boju", pointColor));
				if(getPointColor() != null){
					setPointColor(getPointColor());
					btnColor.setBackground(getPointColor());
				}
			}
		});
		btnColor.setBounds(99, 71, 89, 23);
		contentPanel.add(btnColor);
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

	public Color getPointColor() {
		return pointColor;
	}

	public void setPointColor(Color pointColor) {
		this.pointColor = pointColor;
	}
}
