package JDBC1;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ProductInformation extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JFrame frame1;
	private static ProductInformation frame;
	private Product product;
	private ProductDAO dao;
	private String user="scott";
	private String password="tiger";
	private JLabel lblNewLabel ;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ProductInformation();
					frame.setVisible(true);
					frame.setTitle("Product Information");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProductInformation()
	{
		initialize();
	}
	private void initialize() 
	{
		frame1 = new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblProductInformation = new JLabel("PRODUCT  INFORMATION");
		lblProductInformation.setFont(new Font("Century", Font.BOLD, 21));
		lblProductInformation.setForeground(new Color(0, 0, 128));
		lblProductInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductInformation.setBounds(328, 24, 357, 44);
		contentPane.add(lblProductInformation);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(362, 104, 165, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblId.setBounds(287, 104, 93, 32);
		contentPane.add(lblId);
		
		JComboBox comboBox = new JComboBox(new ProductDAOImpl(user,password).getID());
		
		comboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				 if (comboBox.getSelectedIndex() != -1) {
			            String value = String.valueOf(comboBox.getSelectedItem());
			            product = new ProductDAOImpl(user, password).fillFields(value);
			            textField.setText(product.getID());
			            textField_1.setText(product.getDescription());
			            textField_2.setText(String.valueOf(product.getPrice()));
			            textField_3.setText(String.valueOf(product.getQuantity()));
			        }
			}
			
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setBounds(539, 104, 128, 34);
		contentPane.add(comboBox);
		
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.setBounds(362, 171, 305, 34);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblDescription = new JLabel("DESCRIPTION");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblDescription.setBounds(193, 171, 165, 34);
		contentPane.add(lblDescription);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setBounds(362, 237, 305, 34);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblPrice.setBounds(234, 237, 157, 34);
		contentPane.add(lblPrice);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_3.setBounds(362, 304, 305, 34);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblQuantity = new JLabel("QUANTITY");
		lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuantity.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblQuantity.setBounds(220, 304, 143, 34);
		contentPane.add(lblQuantity);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String id=textField.getText();
				String description=textField_1.getText();
				double price=Double.parseDouble(textField_2.getText());
				int quantity=Integer.parseInt(textField_3.getText());
				product=new Product(id,description,price,quantity);
				dao=new ProductDAOImpl(user,password);
				
				if(ProductDAOImpl.getConnection()!=null)
				{
					int result=dao.insertProduct(product);
					
					if(result>0)
					{
						lblNewLabel.setText("Row inserted successfully !"); 
						//showMessage();
						comboBox.addItem(id);
						lblNewLabel.setForeground(Color.BLUE);
						
					}
					else
					{
						lblNewLabel.setText("ERROR! NO row inserted"); 
						//showMessage();
						lblNewLabel.setForeground(Color.RED);
					}
				}
				else
				{
					lblNewLabel.setText("Connection Error!"); 
					//showMessage();
					lblNewLabel.setForeground(Color.RED);
				}
				
			}
		});
		btnInsert.setFont(new Font("Verdana", Font.BOLD, 21));
		btnInsert.setBounds(38, 421, 143, 44);
		contentPane.add(btnInsert);
		
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						String id=textField.getText();
						String description=textField_1.getText();
						double price=Double.parseDouble(textField_2.getText());
						int quantity=Integer.parseInt(textField_3.getText());
						product=new Product(id,description,price,quantity);
						dao=new ProductDAOImpl(user,password);
						if(ProductDAOImpl.getConnection()!=null)
						{
							int result=dao.updateProduct(product);
							
							if(result>0)
							{
								lblNewLabel.setText("Row updated successfully !"); 
								lblNewLabel.setForeground(Color.BLUE);
								
							}
							else
							{
								lblNewLabel.setText("No update has been performed"); 
								lblNewLabel.setForeground(Color.RED);
							}
						}
						else
						{
							lblNewLabel.setText("Connection Error!"); 
							lblNewLabel.setForeground(Color.RED);
						}
						
						
					}
					
				});
		btnUpdate.setFont(new Font("Verdana", Font.BOLD, 21));
		btnUpdate.setBounds(220, 421, 143, 44);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						String id=textField.getText();
						String description=textField_1.getText();
						double price=Double.parseDouble(textField_2.getText());
						int quantity=Integer.parseInt(textField_3.getText());
						product=new Product(id,description,price,quantity);
						dao=new ProductDAOImpl(user,password);
						if(ProductDAOImpl.getConnection()!=null)
						{
							int result=dao.deleteProduct(id);
							
							if(result>0)
							{
								lblNewLabel.setText("Row deleted successfully !"); 
								comboBox.removeItem(id);
								lblNewLabel.setForeground(Color.BLUE);
								
							}
							else
							{
								lblNewLabel.setText("No deletion has been performed"); 
								lblNewLabel.setForeground(Color.RED);
							}
						}
						else
						{
							lblNewLabel.setText("Connection Error!"); 
							lblNewLabel.setForeground(Color.RED);
						}
						
					}
					
				});
		btnDelete.setFont(new Font("Verdana", Font.BOLD, 21));
		btnDelete.setBounds(406, 421, 143, 44);
		contentPane.add(btnDelete);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				lblNewLabel.setText("");
				comboBox.setSelectedIndex(-1);
				
			}
		});
		btnClear.setFont(new Font("Verdana", Font.BOLD, 21));
		btnClear.setBounds(596, 421, 143, 44);
		contentPane.add(btnClear);
		
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//frame.setVisible(false);
				frame.dispose();
			}
		});
		btnExit.setFont(new Font("Verdana", Font.BOLD, 21));
		btnExit.setBounds(786, 421, 143, 44);
		contentPane.add(btnExit);
		
		 lblNewLabel = new JLabel("");
		 lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(362, 369, 305, 22);
		contentPane.add(lblNewLabel);
	}
	
}
