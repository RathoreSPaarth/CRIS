package try2;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class CrisProject {
	static Connection connection = null;
	static String databasename = "passenger1";
	static String url = "jdbc:mysql://localhost:3306/"+databasename+"?useSSL=false";
	static String username = "root";
	static String password = "0000";
	int count = 0;
	int k = 0,grey=0;

	private JFrame frame;
	JCheckBox[] check;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					CrisProject window = new CrisProject();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CrisProject() {
		check = new JCheckBox[73];
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		//frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 689, 685);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnRed = new JButton("Reserved");
		btnRed.setBounds(10, 11, 104, 23);
		btnRed.setBackground(Color.RED);
		
		JButton btnGreen = new JButton("Checked");
		btnGreen.setBounds(120, 11, 89, 23);
		btnGreen.setBackground(Color.green);
		
		JButton btnGrey = new JButton("Vacant");
		btnGrey.setBounds(219, 11, 89, 23);
		btnGrey.setBackground(Color.GRAY);
		
		JPanel panel_info = new JPanel();
		panel_info.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Passenger Information", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_info.setBounds(302, 66, 322, 246);
		frame.getContentPane().add(panel_info);
		panel_info.setLayout(null);
		panel_info.setBackground(new Color(255, 250, 250, 50));
		
		JTextArea name = new JTextArea();
		name.setBounds(176, 26, 109, 22);
		panel_info.add(name);
		name.setBackground(new Color(255, 250, 250, 100));
		
		JTextArea age = new JTextArea();
		age.setBounds(176, 59, 109, 22);
		panel_info.add(age);
		age.setBackground(new Color(255, 250, 250, 100));
		
		JTextArea gender = new JTextArea();
		gender.setBounds(176, 92, 109, 22);
		panel_info.add(gender);
		gender.setBackground(new Color(255, 250, 250, 100));
		
		JTextArea src = new JTextArea();
		src.setBounds(176, 125, 109, 22);
		panel_info.add(src);
		src.setBackground(new Color(255, 250, 250, 100));
		
		JTextArea dest = new JTextArea();
		dest.setBounds(176, 158, 109, 22);
		panel_info.add(dest);
		dest.setBackground(new Color(255, 250, 250, 100));
		
		JLabel lblPassengerName = new JLabel("Passenger name");
		lblPassengerName.setBounds(10, 31, 104, 14);
		panel_info.add(lblPassengerName);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(10, 64, 104, 14);
		panel_info.add(lblAge);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 97, 104, 14);
		panel_info.add(lblGender);
		
		JLabel lblBoarding = new JLabel("Boarding");
		lblBoarding.setBounds(10, 130, 104, 14);
		panel_info.add(lblBoarding);
		
		JLabel lblNewLabel_1 = new JLabel("Deboarding\r\n");
		lblNewLabel_1.setBounds(10, 163, 104, 14);
		panel_info.add(lblNewLabel_1);
		
		JButton btnSync = new JButton("sync");
		btnSync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//use button once only
				JButton btnSync = (JButton) e.getSource();
				btnSync.setEnabled(false);
				try {
					Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection( url , username , password);
				Statement st = connection.createStatement();
				for(int i=1;i<73;i++)
				{
		        ResultSet rs = st.executeQuery("Select * from details where seat = "+i);   
		        if(rs.next()) {
		           //set color to red
		        	check[i].setBackground(Color.red);
		        	count++;
		        	btnRed.setText(Integer.toString(count));
		        	
		        	
		        	//System.out.println(rs.getString("passengerName"));
		        }
		        else 
		        	//set color to grey
		        	{
		        	check[i].setBackground(Color.gray);
		        	grey++;
		        	}
				}
				
				
				btnGrey.setText(Integer.toString(grey));
		        st.close();
		       // rs.close();
		        connection.close();
		        /*33*/
				
		} catch (Exception e1) {
		        //e1.printStackTrace();
			System.out.println("empty!");
		}
			}
		});
		
		frame.getContentPane().add(btnSync);
		btnSync.setBounds(302, 443, 175, 23);
		
		JLabel lblPassengerAttendanceSystem = new JLabel("Passenger Attendance System");
		lblPassengerAttendanceSystem.setFont(new Font("Sitka Subheading", Font.BOLD, 18));
		lblPassengerAttendanceSystem.setBounds(198, 11, 265, 23);
		frame.getContentPane().add(lblPassengerAttendanceSystem);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(198, 32, 265, 2);
		frame.getContentPane().add(separator);
		
		JButton btnPassengerReported = new JButton("Passenger Reported");
		btnPassengerReported.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					for(int i=1;i<73;i++)
				
		        {
						
		        	if(check[i].isSelected())
		        		{
		        		check[i].setBackground(Color.green);
		        		k++;
		        		btnRed.setText(Integer.toString(count-k));
		        		btnGreen.setText(Integer.toString(k));
		        		//btnGrey.setText(Integer.toString(grey-k));
		            }
						
		        }
					k=0; //yesss!!
		}
				catch(Exception e2)
				{
					System.out.println("null point exception!");
					}
				}
			
		});
		btnPassengerReported.setBounds(302, 477, 175, 23);
		frame.getContentPane().add(btnPassengerReported);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 55, 130, 66);
		frame.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		//panel.setOpaque();
		panel.setBackground(new Color(0,0,0,30));
		
		
		check[1] = new JCheckBox("1");
		GridBagConstraints gbc_checkBox = new GridBagConstraints();
		gbc_checkBox.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox.gridx = 0;
		gbc_checkBox.gridy = 0;
		panel.add(check[1], gbc_checkBox);
		
		check[2] = new JCheckBox("2");
		GridBagConstraints gbc_checkBox_1 = new GridBagConstraints();
		gbc_checkBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_1.gridx = 1;
		gbc_checkBox_1.gridy = 0;
		panel.add(check[2], gbc_checkBox_1);
		
		check[3] = new JCheckBox("3");
		GridBagConstraints gbc_checkBox_2 = new GridBagConstraints();
		gbc_checkBox_2.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_2.gridx = 2;
		gbc_checkBox_2.gridy = 0;
		panel.add(check[3], gbc_checkBox_2);
		
		check[4] = new JCheckBox("4");
		GridBagConstraints gbc_checkBox_3 = new GridBagConstraints();
		gbc_checkBox_3.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_3.gridx = 0;
		gbc_checkBox_3.gridy = 1;
		panel.add(check[4], gbc_checkBox_3);
		//check[4].setBackground(new Color(0,0,0,30));
		
		check[5] = new JCheckBox("5");
		GridBagConstraints gbc_checkBox_4 = new GridBagConstraints();
		gbc_checkBox_4.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_4.gridx = 1;
		gbc_checkBox_4.gridy = 1;
		panel.add(check[5], gbc_checkBox_4);
		
		check[6] = new JCheckBox("6");
		GridBagConstraints gbc_checkBox_5 = new GridBagConstraints();
		gbc_checkBox_5.gridx = 2;
		gbc_checkBox_5.gridy = 1;
		panel.add(check[6], gbc_checkBox_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 117, 130, 66);
		frame.getContentPane().add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		check[9] = new JCheckBox("9");
		GridBagConstraints gbc_checkBox_6 = new GridBagConstraints();
		gbc_checkBox_6.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_6.gridx = 0;
		gbc_checkBox_6.gridy = 0;
		panel_1.add(check[9], gbc_checkBox_6);
		
		check[10] = new JCheckBox("10");		
		GridBagConstraints gbc_checkBox_7 = new GridBagConstraints();
		gbc_checkBox_7.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_7.gridx = 1;
		gbc_checkBox_7.gridy = 0;
		panel_1.add(check[10], gbc_checkBox_7);
		
		check[11] = new JCheckBox("11");
		GridBagConstraints gbc_checkBox_8 = new GridBagConstraints();
		gbc_checkBox_8.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_8.gridx = 2;
		gbc_checkBox_8.gridy = 0;
		panel_1.add(check[11], gbc_checkBox_8);
		
		check[12] = new JCheckBox("12");
		GridBagConstraints gbc_checkBox_9 = new GridBagConstraints();
		gbc_checkBox_9.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_9.gridx = 0;
		gbc_checkBox_9.gridy = 1;
		panel_1.add(check[12], gbc_checkBox_9);
		
		check[13] = new JCheckBox("13");
		GridBagConstraints gbc_checkBox_10 = new GridBagConstraints();
		gbc_checkBox_10.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_10.gridx = 1;
		gbc_checkBox_10.gridy = 1;
		panel_1.add(check[13], gbc_checkBox_10);
		
		check[14] = new JCheckBox("14");
		GridBagConstraints gbc_checkBox_11 = new GridBagConstraints();
		gbc_checkBox_11.gridx = 2;
		gbc_checkBox_11.gridy = 1;
		panel_1.add(check[14], gbc_checkBox_11);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 182, 130, 66);
		frame.getContentPane().add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		panel_2.setBackground(new Color(0,0,0,30));
		panel_1.setBackground(new Color(0,0,0,30));
		
		check[17] = new JCheckBox("17");
		GridBagConstraints gbc_checkBox_12 = new GridBagConstraints();
		gbc_checkBox_12.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_12.gridx = 0;
		gbc_checkBox_12.gridy = 0;
		panel_2.add(check[17], gbc_checkBox_12);
		
		check[18] = new JCheckBox("18");
		GridBagConstraints gbc_checkBox_13 = new GridBagConstraints();
		gbc_checkBox_13.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_13.gridx = 1;
		gbc_checkBox_13.gridy = 0;
		panel_2.add(check[18], gbc_checkBox_13);
		
		check[19] = new JCheckBox("19");
		GridBagConstraints gbc_checkBox_14 = new GridBagConstraints();
		gbc_checkBox_14.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_14.gridx = 2;
		gbc_checkBox_14.gridy = 0;
		panel_2.add(check[19], gbc_checkBox_14);
		
		check[20] = new JCheckBox("20");
		GridBagConstraints gbc_checkBox_15 = new GridBagConstraints();
		gbc_checkBox_15.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_15.gridx = 0;
		gbc_checkBox_15.gridy = 1;
		panel_2.add(check[20], gbc_checkBox_15);
		
		check[21] = new JCheckBox("21");
		GridBagConstraints gbc_checkBox_16 = new GridBagConstraints();
		gbc_checkBox_16.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_16.gridx = 1;
		gbc_checkBox_16.gridy = 1;
		panel_2.add(check[21], gbc_checkBox_16);
		
		check[22] = new JCheckBox("22");
		GridBagConstraints gbc_checkBox_17 = new GridBagConstraints();
		gbc_checkBox_17.gridx = 2;
		gbc_checkBox_17.gridy = 1;
		panel_2.add(check[22], gbc_checkBox_17);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 246, 130, 66);
		frame.getContentPane().add(panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		panel_3.setBackground(new Color(0,0,0,30));
		
		check[25] = new JCheckBox("25");
		GridBagConstraints gbc_checkBox_18 = new GridBagConstraints();
		gbc_checkBox_18.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_18.gridx = 0;
		gbc_checkBox_18.gridy = 0;
		panel_3.add(check[25], gbc_checkBox_18);
		
		check[26] = new JCheckBox("26");
		GridBagConstraints gbc_checkBox_19 = new GridBagConstraints();
		gbc_checkBox_19.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_19.gridx = 1;
		gbc_checkBox_19.gridy = 0;
		panel_3.add(check[26], gbc_checkBox_19);
		
		check[27] = new JCheckBox("27");
		GridBagConstraints gbc_checkBox_20 = new GridBagConstraints();
		gbc_checkBox_20.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_20.gridx = 2;
		gbc_checkBox_20.gridy = 0;
		panel_3.add(check[27], gbc_checkBox_20);
		
		check[28] = new JCheckBox("28");
		GridBagConstraints gbc_checkBox_21 = new GridBagConstraints();
		gbc_checkBox_21.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_21.gridx = 0;
		gbc_checkBox_21.gridy = 1;
		panel_3.add(check[28], gbc_checkBox_21);
		
		check[29] = new JCheckBox("29");
		GridBagConstraints gbc_checkBox_22 = new GridBagConstraints();
		gbc_checkBox_22.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_22.gridx = 1;
		gbc_checkBox_22.gridy = 1;
		panel_3.add(check[29], gbc_checkBox_22);
		
		check[30] = new JCheckBox("30");
		GridBagConstraints gbc_checkBox_23 = new GridBagConstraints();
		gbc_checkBox_23.gridx = 2;
		gbc_checkBox_23.gridy = 1;
		panel_3.add(check[30], gbc_checkBox_23);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 310, 130, 66);
		frame.getContentPane().add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_4.rowHeights = new int[]{0, 0, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		panel_4.setBackground(new Color(0,0,0,30));
		
		check[33] = new JCheckBox("33");
		GridBagConstraints gbc_checkBox_24 = new GridBagConstraints();
		gbc_checkBox_24.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_24.gridx = 0;
		gbc_checkBox_24.gridy = 0;
		panel_4.add(check[33], gbc_checkBox_24);
		
		check[34] = new JCheckBox("34");
		GridBagConstraints gbc_checkBox_25 = new GridBagConstraints();
		gbc_checkBox_25.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_25.gridx = 1;
		gbc_checkBox_25.gridy = 0;
		panel_4.add(check[34], gbc_checkBox_25);
		
		check[35] = new JCheckBox("35");
		GridBagConstraints gbc_checkBox_26 = new GridBagConstraints();
		gbc_checkBox_26.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_26.gridx = 2;
		gbc_checkBox_26.gridy = 0;
		panel_4.add(check[35], gbc_checkBox_26);
		
		check[36] = new JCheckBox("36");
		GridBagConstraints gbc_checkBox_27 = new GridBagConstraints();
		gbc_checkBox_27.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_27.gridx = 0;
		gbc_checkBox_27.gridy = 1;
		panel_4.add(check[36], gbc_checkBox_27);
		
		check[37] = new JCheckBox("37");
		GridBagConstraints gbc_checkBox_28 = new GridBagConstraints();
		gbc_checkBox_28.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_28.gridx = 1;
		gbc_checkBox_28.gridy = 1;
		panel_4.add(check[37], gbc_checkBox_28);
		
		check[38] = new JCheckBox("38");
		GridBagConstraints gbc_checkBox_29 = new GridBagConstraints();
		gbc_checkBox_29.gridx = 2;
		gbc_checkBox_29.gridy = 1;
		panel_4.add(check[38], gbc_checkBox_29);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 376, 130, 66);
		frame.getContentPane().add(panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_5.rowHeights = new int[]{0, 0, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		panel_5.setBackground(new Color(0,0,0,30));
		
		check[41] = new JCheckBox("41");
		GridBagConstraints gbc_checkBox_30 = new GridBagConstraints();
		gbc_checkBox_30.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_30.gridx = 0;
		gbc_checkBox_30.gridy = 0;
		panel_5.add(check[41], gbc_checkBox_30);
		
		check[42] = new JCheckBox("42");
		GridBagConstraints gbc_checkBox_31 = new GridBagConstraints();
		gbc_checkBox_31.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_31.gridx = 1;
		gbc_checkBox_31.gridy = 0;
		panel_5.add(check[42], gbc_checkBox_31);
		
		check[43] = new JCheckBox("43");
		GridBagConstraints gbc_checkBox_32 = new GridBagConstraints();
		gbc_checkBox_32.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_32.gridx = 2;
		gbc_checkBox_32.gridy = 0;
		panel_5.add(check[43], gbc_checkBox_32);
		
		check[44] = new JCheckBox("44");
		GridBagConstraints gbc_checkBox_33 = new GridBagConstraints();
		gbc_checkBox_33.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_33.gridx = 0;
		gbc_checkBox_33.gridy = 1;
		panel_5.add(check[44], gbc_checkBox_33);
		
		check[45] = new JCheckBox("45");
		GridBagConstraints gbc_checkBox_34 = new GridBagConstraints();
		gbc_checkBox_34.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_34.gridx = 1;
		gbc_checkBox_34.gridy = 1;
		panel_5.add(check[45], gbc_checkBox_34);
		
		check[46] = new JCheckBox("46");
		GridBagConstraints gbc_checkBox_35 = new GridBagConstraints();
		gbc_checkBox_35.gridx = 2;
		gbc_checkBox_35.gridy = 1;
		panel_5.add(check[46], gbc_checkBox_35);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 443, 130, 66);
		frame.getContentPane().add(panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		panel_6.setBackground(new Color(0,0,0,30));
		
		check[49] = new JCheckBox("49");
		GridBagConstraints gbc_checkBox_36 = new GridBagConstraints();
		gbc_checkBox_36.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_36.gridx = 0;
		gbc_checkBox_36.gridy = 0;
		panel_6.add(check[49], gbc_checkBox_36);
		//checkBox_36.setOpaque(false);
		
		check[50] = new JCheckBox("50");
		GridBagConstraints gbc_checkBox_37 = new GridBagConstraints();
		gbc_checkBox_37.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_37.gridx = 1;
		gbc_checkBox_37.gridy = 0;
		panel_6.add(check[50], gbc_checkBox_37);
		
		check[51] = new JCheckBox("51");
		GridBagConstraints gbc_checkBox_38 = new GridBagConstraints();
		gbc_checkBox_38.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_38.gridx = 2;
		gbc_checkBox_38.gridy = 0;
		panel_6.add(check[51], gbc_checkBox_38);
		
		check[52] = new JCheckBox("52");
		GridBagConstraints gbc_checkBox_39 = new GridBagConstraints();
		gbc_checkBox_39.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_39.gridx = 0;
		gbc_checkBox_39.gridy = 1;
		panel_6.add(check[52], gbc_checkBox_39);
		
		check[53] = new JCheckBox("53");
		GridBagConstraints gbc_checkBox_40 = new GridBagConstraints();
		gbc_checkBox_40.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_40.gridx = 1;
		gbc_checkBox_40.gridy = 1;
		panel_6.add(check[53], gbc_checkBox_40);
		
		check[54] = new JCheckBox("54");
		GridBagConstraints gbc_checkBox_41 = new GridBagConstraints();
		gbc_checkBox_41.gridx = 2;
		gbc_checkBox_41.gridy = 1;
		panel_6.add(check[54], gbc_checkBox_41);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBounds(10, 509, 130, 66);
		frame.getContentPane().add(panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_7.rowHeights = new int[]{0, 0, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		panel_7.setBackground(new Color(0,0,0,30));
		
		check[57] = new JCheckBox("57");
		GridBagConstraints gbc_checkBox_42 = new GridBagConstraints();
		gbc_checkBox_42.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_42.gridx = 0;
		gbc_checkBox_42.gridy = 0;
		panel_7.add(check[57], gbc_checkBox_42);
		
		check[58] = new JCheckBox("58");
		GridBagConstraints gbc_checkBox_43 = new GridBagConstraints();
		gbc_checkBox_43.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_43.gridx = 1;
		gbc_checkBox_43.gridy = 0;
		panel_7.add(check[58], gbc_checkBox_43);
		
		check[59] = new JCheckBox("59");
		GridBagConstraints gbc_checkBox_44 = new GridBagConstraints();
		gbc_checkBox_44.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_44.gridx = 2;
		gbc_checkBox_44.gridy = 0;
		panel_7.add(check[59], gbc_checkBox_44);
		
		check[60] = new JCheckBox("60");
		GridBagConstraints gbc_checkBox_45 = new GridBagConstraints();
		gbc_checkBox_45.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_45.gridx = 0;
		gbc_checkBox_45.gridy = 1;
		panel_7.add(check[60], gbc_checkBox_45);
		
		check[61] = new JCheckBox("61");
		GridBagConstraints gbc_checkBox_46 = new GridBagConstraints();
		gbc_checkBox_46.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_46.gridx = 1;
		gbc_checkBox_46.gridy = 1;
		panel_7.add(check[61], gbc_checkBox_46);
		
		check[62] = new JCheckBox("62");
		GridBagConstraints gbc_checkBox_47 = new GridBagConstraints();
		gbc_checkBox_47.gridx = 2;
		gbc_checkBox_47.gridy = 1;
		panel_7.add(check[62], gbc_checkBox_47);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(10, 575, 130, 66);
		frame.getContentPane().add(panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_8.rowHeights = new int[]{0, 0, 0};
		gbl_panel_8.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		panel_8.setBackground(new Color(0,0,0,30));
		
		check[65] = new JCheckBox("65");
		GridBagConstraints gbc_checkBox_48 = new GridBagConstraints();
		gbc_checkBox_48.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_48.gridx = 0;
		gbc_checkBox_48.gridy = 0;
		panel_8.add(check[65], gbc_checkBox_48);
		
		check[66] = new JCheckBox("66");
		GridBagConstraints gbc_checkBox_49 = new GridBagConstraints();
		gbc_checkBox_49.insets = new Insets(0, 0, 5, 5);
		gbc_checkBox_49.gridx = 1;
		gbc_checkBox_49.gridy = 0;
		panel_8.add(check[66], gbc_checkBox_49);
		
		check[67] = new JCheckBox("67");
		GridBagConstraints gbc_checkBox_50 = new GridBagConstraints();
		gbc_checkBox_50.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_50.gridx = 2;
		gbc_checkBox_50.gridy = 0;
		panel_8.add(check[67], gbc_checkBox_50);
		
		check[68] = new JCheckBox("68");
		GridBagConstraints gbc_checkBox_51 = new GridBagConstraints();
		gbc_checkBox_51.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_51.gridx = 0;
		gbc_checkBox_51.gridy = 1;
		panel_8.add(check[68], gbc_checkBox_51);
		
		check[69] = new JCheckBox("69");
		GridBagConstraints gbc_checkBox_52 = new GridBagConstraints();
		gbc_checkBox_52.insets = new Insets(0, 0, 0, 5);
		gbc_checkBox_52.gridx = 1;
		gbc_checkBox_52.gridy = 1;
		panel_8.add(check[69], gbc_checkBox_52);
		
		check[70] = new JCheckBox("70");
		GridBagConstraints gbc_checkBox_53 = new GridBagConstraints();
		gbc_checkBox_53.gridx = 2;
		gbc_checkBox_53.gridy = 1;
		panel_8.add(check[70], gbc_checkBox_53);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBounds(178, 55, 47, 66);
		frame.getContentPane().add(panel_9);
		GridBagLayout gbl_panel_9 = new GridBagLayout();
		gbl_panel_9.columnWidths = new int[]{0, 0};
		gbl_panel_9.rowHeights = new int[]{0, 0, 0};
		gbl_panel_9.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_9.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_9.setLayout(gbl_panel_9);
		panel_9.setBackground(new Color(0,0,0,30));
		
		check[7] = new JCheckBox("7");
		GridBagConstraints gbc_checkBox_54 = new GridBagConstraints();
		gbc_checkBox_54.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_54.gridx = 0;
		gbc_checkBox_54.gridy = 0;
		panel_9.add(check[7], gbc_checkBox_54);
		
		check[8] = new JCheckBox("8");
		GridBagConstraints gbc_checkBox_55 = new GridBagConstraints();
		gbc_checkBox_55.gridx = 0;
		gbc_checkBox_55.gridy = 1;
		panel_9.add(check[8], gbc_checkBox_55);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBounds(178, 117, 47, 66);
		frame.getContentPane().add(panel_10);
		GridBagLayout gbl_panel_10 = new GridBagLayout();
		gbl_panel_10.columnWidths = new int[]{0, 0};
		gbl_panel_10.rowHeights = new int[]{0, 0, 0};
		gbl_panel_10.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_10.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_10.setLayout(gbl_panel_10);
		panel_10.setBackground(new Color(0,0,0,30));
		
		check[15] = new JCheckBox("15");
		GridBagConstraints gbc_checkBox_56 = new GridBagConstraints();
		gbc_checkBox_56.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_56.gridx = 0;
		gbc_checkBox_56.gridy = 0;
		panel_10.add(check[15], gbc_checkBox_56);
		
		check[16] = new JCheckBox("16");
		GridBagConstraints gbc_checkBox_57 = new GridBagConstraints();
		gbc_checkBox_57.gridx = 0;
		gbc_checkBox_57.gridy = 1;
		panel_10.add(check[16], gbc_checkBox_57);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBounds(178, 182, 47, 66);
		frame.getContentPane().add(panel_11);
		GridBagLayout gbl_panel_11 = new GridBagLayout();
		gbl_panel_11.columnWidths = new int[]{0, 0};
		gbl_panel_11.rowHeights = new int[]{0, 0, 0};
		gbl_panel_11.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_11.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_11.setLayout(gbl_panel_11);
		panel_11.setBackground(new Color(0,0,0,30));
		
		check[23] = new JCheckBox("23");
		GridBagConstraints gbc_checkBox_58 = new GridBagConstraints();
		gbc_checkBox_58.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_58.gridx = 0;
		gbc_checkBox_58.gridy = 0;
		panel_11.add(check[23], gbc_checkBox_58);
		
		check[24] = new JCheckBox("24");
		GridBagConstraints gbc_checkBox_59 = new GridBagConstraints();
		gbc_checkBox_59.gridx = 0;
		gbc_checkBox_59.gridy = 1;
		panel_11.add(check[24], gbc_checkBox_59);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBounds(178, 246, 47, 66);
		frame.getContentPane().add(panel_12);
		GridBagLayout gbl_panel_12 = new GridBagLayout();
		gbl_panel_12.columnWidths = new int[]{0, 0};
		gbl_panel_12.rowHeights = new int[]{0, 0, 0};
		gbl_panel_12.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_12.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_12.setLayout(gbl_panel_12);
		panel_12.setBackground(new Color(0,0,0,30));
		
		check[31] = new JCheckBox("31");
		GridBagConstraints gbc_checkBox_60 = new GridBagConstraints();
		gbc_checkBox_60.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_60.gridx = 0;
		gbc_checkBox_60.gridy = 0;
		panel_12.add(check[31], gbc_checkBox_60);
		
		check[32] = new JCheckBox("32");
		GridBagConstraints gbc_checkBox_61 = new GridBagConstraints();
		gbc_checkBox_61.gridx = 0;
		gbc_checkBox_61.gridy = 1;
		panel_12.add(check[32], gbc_checkBox_61);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBounds(178, 310, 47, 66);
		frame.getContentPane().add(panel_13);
		GridBagLayout gbl_panel_13 = new GridBagLayout();
		gbl_panel_13.columnWidths = new int[]{0, 0};
		gbl_panel_13.rowHeights = new int[]{0, 0, 0};
		gbl_panel_13.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_13.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_13.setLayout(gbl_panel_13);
		panel_13.setBackground(new Color(0,0,0,30));
		
		check[39] = new JCheckBox("39");
		GridBagConstraints gbc_checkBox_62 = new GridBagConstraints();
		gbc_checkBox_62.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_62.gridx = 0;
		gbc_checkBox_62.gridy = 0;
		panel_13.add(check[39], gbc_checkBox_62);
		
		check[40] = new JCheckBox("40");
		GridBagConstraints gbc_checkBox_63 = new GridBagConstraints();
		gbc_checkBox_63.gridx = 0;
		gbc_checkBox_63.gridy = 1;
		panel_13.add(check[40], gbc_checkBox_63);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBounds(178, 376, 47, 66);
		frame.getContentPane().add(panel_14);
		GridBagLayout gbl_panel_14 = new GridBagLayout();
		gbl_panel_14.columnWidths = new int[]{0, 0};
		gbl_panel_14.rowHeights = new int[]{0, 0, 0};
		gbl_panel_14.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_14.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_14.setLayout(gbl_panel_14);
		panel_14.setBackground(new Color(0,0,0,30));
	
		check[47] = new JCheckBox("47");
		GridBagConstraints gbc_checkBox_64 = new GridBagConstraints();
		gbc_checkBox_64.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_64.gridx = 0;
		gbc_checkBox_64.gridy = 0;
		panel_14.add(check[47], gbc_checkBox_64);
		
		check[48] = new JCheckBox("48");
		GridBagConstraints gbc_checkBox_65 = new GridBagConstraints();
		gbc_checkBox_65.gridx = 0;
		gbc_checkBox_65.gridy = 1;
		panel_14.add(check[48], gbc_checkBox_65);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBounds(178, 443, 47, 66);
		frame.getContentPane().add(panel_15);
		GridBagLayout gbl_panel_15 = new GridBagLayout();
		gbl_panel_15.columnWidths = new int[]{0, 0};
		gbl_panel_15.rowHeights = new int[]{0, 0, 0};
		gbl_panel_15.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_15.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_15.setLayout(gbl_panel_15);
		panel_15.setBackground(new Color(0,0,0,30));
		
		check[55] = new JCheckBox("55");
		GridBagConstraints gbc_checkBox_66 = new GridBagConstraints();
		gbc_checkBox_66.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_66.gridx = 0;
		gbc_checkBox_66.gridy = 0;
		panel_15.add(check[55], gbc_checkBox_66);
		
		check[56] = new JCheckBox("56");
		GridBagConstraints gbc_checkBox_67 = new GridBagConstraints();
		gbc_checkBox_67.gridx = 0;
		gbc_checkBox_67.gridy = 1;
		panel_15.add(check[56], gbc_checkBox_67);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBounds(178, 509, 47, 66);
		frame.getContentPane().add(panel_16);
		GridBagLayout gbl_panel_16 = new GridBagLayout();
		gbl_panel_16.columnWidths = new int[]{0, 0};
		gbl_panel_16.rowHeights = new int[]{0, 0, 0};
		gbl_panel_16.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_16.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_16.setLayout(gbl_panel_16);
		panel_16.setBackground(new Color(0,0,0,30));
		
		check[63] = new JCheckBox("63");
		GridBagConstraints gbc_checkBox_68 = new GridBagConstraints();
		gbc_checkBox_68.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_68.gridx = 0;
		gbc_checkBox_68.gridy = 0;
		panel_16.add(check[63], gbc_checkBox_68);
		
		check[64] = new JCheckBox("64");
		GridBagConstraints gbc_checkBox_69 = new GridBagConstraints();
		gbc_checkBox_69.gridx = 0;
		gbc_checkBox_69.gridy = 1;
		panel_16.add(check[64], gbc_checkBox_69);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBounds(178, 575, 47, 66);
		frame.getContentPane().add(panel_17);
		GridBagLayout gbl_panel_17 = new GridBagLayout();
		gbl_panel_17.columnWidths = new int[]{0, 0};
		gbl_panel_17.rowHeights = new int[]{0, 0, 0};
		gbl_panel_17.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_17.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel_17.setLayout(gbl_panel_17);
		panel_17.setBackground(new Color(0,0,0,30));
		
		check[71] = new JCheckBox("71");
		GridBagConstraints gbc_checkBox_70 = new GridBagConstraints();
		gbc_checkBox_70.insets = new Insets(0, 0, 5, 0);
		gbc_checkBox_70.gridx = 0;
		gbc_checkBox_70.gridy = 0;
		panel_17.add(check[71], gbc_checkBox_70);
		
		check[72] = new JCheckBox("72");
		GridBagConstraints gbc_checkBox_71 = new GridBagConstraints();
		gbc_checkBox_71.gridx = 0;
		gbc_checkBox_71.gridy = 1;
		panel_17.add(check[72], gbc_checkBox_71);
		
		JTextArea vacant = new JTextArea();
		vacant.setBounds(540, 510, 83, 21);
		frame.getContentPane().add(vacant);
		vacant.setBackground(new Color(255, 250, 250, 100));
		
		JButton btnVacant = new JButton("Passenger not turned up");
		btnVacant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					for(int i=1;i<73;i++)
				
		        {
						
		        	if(check[i].isSelected())
		        		{
		        		k++;
		        		
		        		vacant.setText(Integer.toString(count-k));
		            }
						
		        }
					k=0; //yesss!!
		}
				catch(Exception e2)
				{
					System.out.println("null point exception!");
					}
				//System.out.println(count-k);
			}
		});
		btnVacant.setBounds(302, 511, 175, 23);
		frame.getContentPane().add(btnVacant);
		
		JPanel panel_18 = new JPanel();
		panel_18.setBackground(Color.PINK);
		panel_18.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_18.setBounds(302, 583, 322, 52);
		frame.getContentPane().add(panel_18);
		panel_18.setBackground(new Color(255, 250, 250, 50));
		panel_18.setLayout(null);
		panel_18.add(btnRed);
		panel_18.add(btnGreen);
		panel_18.add(btnGrey);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Paarth S Rathore\\Desktop\\rsz_1rsz_1criscris.png"));
		lblNewLabel_2.setBounds(46, 3, 107, 41);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Paarth S Rathore\\Desktop\\rsz_1rsz_rail.png"));
		lblNewLabel_3.setBounds(568, 0, 84, 66);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Paarth S Rathore\\Desktop\\bgcris3.jpg"));
		lblNewLabel.setBounds(0, 0, 673, 646);
		frame.getContentPane().add(lblNewLabel);
		
		for(int i = 1 ; i<73 ; i++)
		{
			check[i].setBackground(new Color(0,0,0,0));
			
		}
		// end
	}
}
