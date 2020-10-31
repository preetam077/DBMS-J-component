package Insurance;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

public class RegistrationForms implements ActionListener {
	private JLabel jl_adminid, jl_admin_password, jl_userid, jl_user_password, jl_title, jl_warning1, jl_warning2;
	private JTextField jt_adminid, jt_admin_password, jt_userid, jt_user_password;
	private JButton jb_Admin, jb_user, jb_signup, jb_admin_submit, jb_user_submit, jb_back_admin, jb_back_user,
			jb_policy_details,jb_yourdetail, jb_youragentdetail, jb_yoursalesdetail, jb_usr_logout, jb_usrdetail,
			jb_allagentdetail, jb_allsalesdetail, jb_claiminfo, jb_adm_logout, jb_goBack;
	private JFrame f, frame1, frame2, frame3, frame4, frame5, frame6, lastFrame;
	private JRadioButton jr_agreement_admin, jr_agreement_user;
	private Container container1, container2, container3, container4, container5;
	private ImageIcon icon1, icon2, icon3, icon4;
	private JTable j;
	private String userid;

	public RegistrationForms() {
		Font myFont = new Font("Serif", Font.BOLD, 40);
		Font myFont2 = new Font("Serif", Font.BOLD, 16);
		frame1 = new JFrame();
		frame1.setSize(800, 750);
		frame1.setLayout(null);
		frame1.setTitle("Web Insurance Services");
		container1 = frame1.getContentPane();

		jl_title = new JLabel();
		jl_title.setFont(myFont);
		jl_title.setBounds(180, 40, 350, 45);
		jl_title.setText("Insurance Services:");
		container1.add(jl_title);

		jb_Admin = new JButton("Admin");
		jb_Admin.setBounds(190, 430, 100, 30);
		container1.add(jb_Admin);
		jb_Admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2 = new JFrame();
				container2 = frame2.getContentPane();
				frame2.setTitle("Admin Login Form: ");

				jl_title = new JLabel("ADMIN LOGIN: ");
				jl_title.setBounds(100, 40, 100, 25);
				container2.add(jl_title);

				jl_adminid = new JLabel("Admin ID: ");
				jl_adminid.setBounds(80, 70, 100, 25);
				container2.add(jl_adminid);

				jl_admin_password = new JLabel("Password: ");
				jl_admin_password.setBounds(80, 110, 100, 25);
				container2.add(jl_admin_password);

				jt_adminid = new JTextField();
				jt_adminid.setBounds(180, 70, 200, 25);
				container2.add(jt_adminid);

				jt_admin_password = new JTextField();
				jt_admin_password.setBounds(180, 110, 200, 25);
				container2.add(jt_admin_password);

				jr_agreement_admin = new JRadioButton("Click to Agree to our Terms and Conditions");
				jr_agreement_admin.setBounds(110, 150, 300, 25);
				container2.add(jr_agreement_admin);

				jb_admin_submit = new JButton("Log in");
				jb_admin_submit.setBounds(130, 190, 100, 25);
				container2.add(jb_admin_submit);
				jb_admin_submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (jr_agreement_admin.isSelected() == false) {
							jl_warning1 = new JLabel("Can't proceed without agreeing our conditions");
							jl_warning1.setBounds(110, 230, 300, 25);
							container2.add(jl_warning1);
							frame2.repaint();
							frame2.setVisible(true);
						} else {
							frame2.setVisible(false);
							frame4 = new JFrame();
							container4 = frame4.getContentPane();
							frame4.setTitle("ADMIN Page");

							JLabel jl_adm = new JLabel("SELECT WHICH INFO YOU WISH TO DISPLAY: ");
							jl_adm.setBounds(150, 80, 300, 30);
							container4.add(jl_adm);

							jb_usrdetail = new JButton("All Users Details ");
							jb_usrdetail.setBounds(180, 120, 200, 30);
							container4.add(jb_usrdetail);
							jb_usrdetail.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									frame4.setVisible(false);
									DefaultTableModel model = new DefaultTableModel();
									j = new JTable(model);
									j.setBounds(30, 90, 200, 300);
									model.addColumn("User ID");
									model.addColumn("Name");
									model.addColumn("Gender");
									model.addColumn("DOB");
									model.addColumn("Address");
									model.addColumn("Phone");
									f = new JFrame();

									f.setTitle("USER DETAILS");
									try {
										OracleDataSource ods = new OracleDataSource();
										String url = "jdbc:oracle:thin:@//192.168.43.94:1521/xe";
										String userId = "system";
										String password = "Astroy2018";
										ods.setURL(url);
										ods.setUser(userId);
										ods.setPassword(password);
										Connection connection = ods.getConnection();
										Statement statement = connection.createStatement();
										String query = "select c.claimant_id,name,gender,DOB,Address,e.Phone from claimant1 c join claimant2 e on c.claimant_id=e.claimant_id";
										ResultSet rs = statement.executeQuery(query);

										while (rs.next()) {
											model.addRow(new Object[] { rs.getString(1), rs.getString(2),
													rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6) });
										}
										connection.close();
									} catch (SQLException se) {
										System.out.println(se);
									}
									// adding it to JScrollPane
									JScrollPane sp = new JScrollPane(j);
									jb_goBack=new JButton("Back");
									jb_goBack.setBounds(190,300,150,45);
									jb_goBack.setFont(myFont2);
									f.add(jb_goBack);
									jb_goBack.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											f.dispose();
											frame4.setVisible(true);
										}
									});
									f.add(sp);
									// Frame Size
									f.setSize(750, 500);
									// Frame Visible = true
									f.setVisible(true);
								}
							});

							jb_allagentdetail = new JButton("All Agents Details ");
							jb_allagentdetail.setBounds(180, 160, 200, 30);
							container4.add(jb_allagentdetail);
							jb_allagentdetail.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									DefaultTableModel model = new DefaultTableModel();
									frame4.setVisible(false);
									j = new JTable(model);
									j.setBounds(30, 40, 200, 300);
									model.addColumn("Agent ID");
									model.addColumn("Name");
									model.addColumn("Gender");
									model.addColumn("DOB");
									model.addColumn("Address");
									model.addColumn("Phone");
									f = new JFrame();

									f.setTitle("ALL AGENTS DETAILS");
									try {
										OracleDataSource ods = new OracleDataSource();
										String url = "jdbc:oracle:thin:@//192.168.43.94:1521/xe";
										String userId = "system";
										String password = "Astroy2018";
										ods.setURL(url);
										ods.setUser(userId);
										ods.setPassword(password);
										Connection connection = ods.getConnection();
										Statement statement = connection.createStatement();
										String query = "select a.agentid,agent_name,gender,DOB,Address,e.Phone from agent1 a join agent2 e on a.agentid=e.agentid";
										ResultSet rs = statement.executeQuery(query);

										while (rs.next()) {
											model.addRow(new Object[] { rs.getString(1), rs.getString(2),
													rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6)});
										}
										connection.close();
									} catch (SQLException se) {
										System.out.println(se);
									}				
									JScrollPane sp = new JScrollPane(j);
									jb_goBack=new JButton("Back");
									jb_goBack.setBounds(190,300,150,45);
									jb_goBack.setFont(myFont2);
									f.add(jb_goBack);
									jb_goBack.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											f.dispose();
											frame4.setVisible(true);
										}
									});
									f.add(sp);
									f.setSize(750, 500);
									f.setVisible(true);
								

								}
							});

							jb_allsalesdetail = new JButton("All Sales Info ");
							jb_allsalesdetail.setBounds(180, 200, 200, 30);
							container4.add(jb_allsalesdetail);
							jb_allsalesdetail.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									frame4.setVisible(false);
									DefaultTableModel model = new DefaultTableModel();
									j = new JTable(model);
									j.setBounds(30, 40, 200, 300);
									model.addColumn("Policy Key");
									model.addColumn("PHolder ID");
									model.addColumn("Pholder Name");
									model.addColumn("Agent ID");
									model.addColumn("Agent Name");
									model.addColumn("Claimant ID");
									model.addColumn("Claimant Name");
									model.addColumn("Commission");
									f = new JFrame();

									f.setTitle("ALL SALES INFO");
									try {
										OracleDataSource ods = new OracleDataSource();
										String url = "jdbc:oracle:thin:@//192.168.43.94:1521/xe";
										String userId = "system";
										String password = "Astroy2018";
										ods.setURL(url);
										ods.setUser(userId);
										ods.setPassword(password);
										Connection connection = ods.getConnection();
										Statement statement = connection.createStatement();
										String query = "select s.policy_key,i.pholder_id,p.name, s.agent_id, a.agent_name, c.claimant_id, c.name, s.commission from sales s join agent1 a on s.agent_id=a.agentid join claimant1 c on c.claimant_id=s.claimant_id join insured_by1 i on i.policy_key=s.policy_key join policy_holder1 p on p.pholder_id=i.pholder_id";
										ResultSet rs = statement.executeQuery(query);

										while (rs.next()) {
											model.addRow(new Object[] { rs.getString(1), rs.getString(2),
													rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8) });
										}
										connection.close();
									} catch (SQLException se) {
										System.out.println(se);
									}
									// adding it to JScrollPane
									JScrollPane sp = new JScrollPane(j);
									jb_goBack=new JButton("Back");
									jb_goBack.setBounds(190,300,150,45);
									jb_goBack.setFont(myFont2);
									f.add(jb_goBack);
									jb_goBack.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											f.dispose();
											frame4.setVisible(true);
										}
									});
									f.add(sp);
									// Frame Size
									f.setSize(750, 500);
									// Frame Visible = true
									f.setVisible(true);

								}
							});

							jb_claiminfo = new JButton("Claimed Insurance Info ");
							jb_claiminfo.setBounds(180, 240, 200, 30);
							container4.add(jb_claiminfo);
							jb_claiminfo.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									frame4.setVisible(false);
									DefaultTableModel model = new DefaultTableModel();
									j = new JTable(model);
									j.setBounds(30, 40, 200, 300);
									model.addColumn("Policy Key");
									model.addColumn("Claimed by");
									model.addColumn("Claimant Name");
									model.addColumn("Claimed From");
									model.addColumn("Policy holder Name");
									model.addColumn("On Date");
									model.addColumn("Reason");
									f = new JFrame();

									f.setTitle("Claimed Insurance Details");
									try {
										OracleDataSource ods = new OracleDataSource();
										String url = "jdbc:oracle:thin:@//192.168.43.94:1521/xe";
										String userId = "system";
										String password = "Astroy2018";
										ods.setURL(url);
										ods.setUser(userId);
										ods.setPassword(password);
										Connection connection = ods.getConnection();
										Statement statement = connection.createStatement();
										String query = "select c.policy_key,c.claimant_id,d.name,p.pholder_id,p.name,c.claimed_date,c.reason from claimed_by c join claimant1 d on c.claimant_id=d.claimant_id join insured_by1 i on i.policy_key=c.policy_key join policy_holder1 p on i.pholder_id=p.pholder_id";
										ResultSet rs = statement.executeQuery(query);

										while (rs.next()) {
											model.addRow(new Object[] { rs.getString(1), rs.getString(2),
													rs.getString(3), rs.getString(4), rs.getString(5), rs.getDate(6), rs.getString(7)});
										}
										connection.close();
									} catch (SQLException se) {
										System.out.println(se);
									}
									// adding it to JScrollPane
									JScrollPane sp = new JScrollPane(j);
									jb_goBack=new JButton("Back");
									jb_goBack.setBounds(190,300,150,45);
									jb_goBack.setFont(myFont2);
									f.add(jb_goBack);
									jb_goBack.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											f.dispose();
											frame4.setVisible(true);
										}
									});
									f.add(sp);
	
									f.setSize(750, 500);
	
									f.setVisible(true);

								}
							});
							
							jb_policy_details = new JButton("Policy Details");
							jb_policy_details.setBounds(180, 280, 200, 30);
							container4.add(jb_policy_details);
							jb_policy_details.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									frame4.setVisible(false);
									DefaultTableModel model = new DefaultTableModel();
									j = new JTable(model);
									j.setBounds(30, 40, 200, 300);
									model.addColumn("Policy Key");
									model.addColumn("Insurance Name");
									model.addColumn("Insured Date");
									model.addColumn("Expiry Date");
									f = new JFrame();
									f.setTitle("ALL POLICY DETAILS");
									try {
										OracleDataSource ods = new OracleDataSource();
										String url = "jdbc:oracle:thin:@//192.168.43.94:1521/xe";
										String userId = "system";
										String password = "Astroy2018";
										ods.setURL(url);
										ods.setUser(userId);
										ods.setPassword(password);
										Connection connection = ods.getConnection();
										Statement statement = connection.createStatement();
										String query = "select * from Policy";
										ResultSet rs = statement.executeQuery(query);

										while (rs.next()) {
											model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getDate(3), rs.getDate(4)});
										}
										connection.close();
									} catch (SQLException se) {
										System.out.println(se);
									}
								
									JScrollPane sp = new JScrollPane(j);
									jb_goBack=new JButton("Back");
									jb_goBack.setBounds(190,300,150,45);
									jb_goBack.setFont(myFont2);
									f.add(jb_goBack);
									jb_goBack.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											f.dispose();
											frame4.setVisible(true);
										}
									});
									f.add(sp);
									f.setSize(750, 500);
									f.setVisible(true);

								}
							});

							jb_adm_logout = new JButton("Sign out ");
							jb_adm_logout.setBounds(180, 340, 200, 30);
							container4.add(jb_adm_logout);
							jb_adm_logout.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									frame4.dispose();
									frame1.setVisible(true);
								}
							});

							frame4.setLayout(null);
							frame4.setSize(600, 550);
							frame4.setVisible(true);
							frame4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						}

					}

				});

				jb_back_admin = new JButton("Back");
				jb_back_admin.setBounds(250, 190, 100, 25);
				container2.add(jb_back_admin);
				jb_back_admin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame2.setVisible(false);
						frame1.setVisible(true);
					}
				});

				frame1.setVisible(false);
				frame2.setSize(450, 400);
				frame2.setLayout(null);
				frame2.setVisible(true);
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

		icon1 = new ImageIcon("D://admin.jpg");
		JLabel adminicon = new JLabel(icon1);
		adminicon.setBounds(190, 300, 100, 108);
		container1.add(adminicon);

		icon2 = new ImageIcon("D://user.png");
		JLabel usericon = new JLabel(icon2);
		usericon.setBounds(400, 300, 100, 108);
		container1.add(usericon);

		icon3 = new ImageIcon("D://aboutus.jpg");
		JLabel aboutusicon = new JLabel(icon3);
		aboutusicon.setBounds(90, 490, 260, 200);
		container1.add(aboutusicon);

		JTextArea jta = new JTextArea();
		jta.setBounds(360, 490, 270, 200);
		container1.add(jta);
		jta.setText( "\n----------------------------------------------------------\nAbout Us:\n----------------------------------------------------------\nBased in VIT.\nOur Company was started in 2010.\nAim:To provide Best Insurance Services\nContact Info: 9789600775\n----------------------------------------------------------\n");
		jta.setFont(myFont2);

		jb_user = new JButton("User");
		jb_user.setBounds(400, 430, 100, 30);
		container1.add(jb_user);
		jb_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame3 = new JFrame();
				container3 = frame3.getContentPane();
				frame3.setTitle("User Login Form: ");

				jl_title = new JLabel("USER LOGIN: ");
				jl_title.setBounds(100, 40, 100, 25);
				container3.add(jl_title);

				jl_userid = new JLabel("USER ID: ");
				jl_userid.setBounds(80, 70, 100, 25);
				container3.add(jl_userid);

				jl_user_password = new JLabel("Password: ");
				jl_user_password.setBounds(80, 110, 100, 25);
				container3.add(jl_user_password);

				jt_userid = new JTextField();
				jt_userid.setBounds(180, 70, 200, 25);
				container3.add(jt_userid);

				jt_user_password = new JTextField();
				jt_user_password.setBounds(180, 110, 200, 25);
				container3.add(jt_user_password);

				jr_agreement_user = new JRadioButton("Click to Agree to our Terms and Conditions");
				jr_agreement_user.setBounds(110, 150, 300, 25);
				container3.add(jr_agreement_user);

				jb_user_submit = new JButton("Submit");
				jb_user_submit.setBounds(130, 190, 100, 25);
				container3.add(jb_user_submit);
				
				userid=jt_userid.getText();
				
				jb_user_submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (jr_agreement_user.isSelected() == false) {
							jl_warning2 = new JLabel("Can't proceed without agreeing our conditions");
							jl_warning2.setBounds(110, 270, 300, 25);
							container3.add(jl_warning2);
							frame3.repaint();
							frame3.setVisible(true);
						} else {
							frame3.setVisible(false);
							frame5 = new JFrame();
							container5 = frame5.getContentPane();
							frame5.setTitle("USER Page");

							JLabel jl_usr = new JLabel("SELECT WHICH INFO YOU WISH TO DISPLAY: ");
							jl_usr.setBounds(150, 80, 300, 30);
							container5.add(jl_usr);

							jb_yourdetail = new JButton("Personal Info ");
							jb_yourdetail.setBounds(180, 120, 200, 30);
							container5.add(jb_yourdetail);
							jb_yourdetail.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									userid=jt_userid.getText();
									frame5.setVisible(false);
									f = new JFrame();
									f.setSize(600, 550);
									f.setTitle("YOUR DETAILS");

									JLabel jl_title = new JLabel("Personal Info: ");
									jl_title.setBounds(80, 40, 150, 25);
									jl_title.setFont(myFont2);
									f.add(jl_title);
									
									JLabel jl_userid = new JLabel("User ID: ");
									jl_userid.setBounds(80, 80, 150, 25);
									f.add(jl_userid);
									
									JTextField jtf_userid = new JTextField();
									jtf_userid.setBounds(200, 80, 170, 25);
									f.add(jtf_userid);
									
									JLabel jl_name = new JLabel("Name: ");
									jl_name.setBounds(80, 120, 150, 25);
									f.add(jl_name);
									
									JTextField jtf_name = new JTextField();
									jtf_name.setBounds(200, 120, 170, 25);
									f.add(jtf_name);
									
									JLabel jl_gender = new JLabel("Gender: ");
									jl_gender.setBounds(80, 160, 150, 25);
									f.add(jl_gender);
									
									JTextField jtf_gender = new JTextField();
									jtf_gender.setBounds(200, 160, 170, 25);
									f.add(jtf_gender);
									
									JLabel jl_address = new JLabel("Address: ");
									jl_address.setBounds(80, 200, 150, 25);
									f.add(jl_address);
									
									JTextField jtf_address = new JTextField();
									jtf_address.setBounds(200, 200, 170, 25);
									f.add(jtf_address);
									
									JLabel jl_dob = new JLabel("Date of Birth: ");
									jl_dob.setBounds(80, 240, 100, 25);
									f.add(jl_dob);
									
									JTextField jtf_dob = new JTextField();
									jtf_dob.setBounds(200, 240, 170, 25);
									f.add(jtf_dob);
									
									
									JLabel jl_Phone = new JLabel("Contact no: ");
									jl_Phone.setBounds(80, 280, 100, 25);
									f.add(jl_Phone);
									
									JTextField jtf_phone = new JTextField();
									jtf_phone.setBounds(200, 280, 170, 25);
									f.add(jtf_phone);
									
									try {
										OracleDataSource ods = new OracleDataSource();
										String url = "jdbc:oracle:thin:@//192.168.43.94:1521/xe";
										String userId = "system";
										String password = "Astroy2018";
										ods.setURL(url);
										ods.setUser(userId);
										ods.setPassword(password);
										Connection connection = ods.getConnection();
										Statement statement = connection.createStatement();
										String query = "select c.claimant_id,name,gender,DOB,Address,e.Phone from claimant1 c join claimant2 e on c.claimant_id=e.claimant_id where c.claimant_id='"+userid+"'";
										ResultSet rs = statement.executeQuery(query);

										while (rs.next()) {
											jtf_userid.setText(rs.getString(1));
											jtf_name.setText(rs.getString(2));
											jtf_gender.setText(rs.getString(3));
											jtf_dob.setText(rs.getDate(4).toString());
											jtf_address.setText(rs.getString(5));
											jtf_phone.setText(rs.getString(6));
										}
										connection.close();
									} catch (SQLException se) {
										System.out.println(se);
									}
							
									jb_goBack=new JButton("Back");
									jb_goBack.setBounds(190,330,150,30);
									jb_goBack.setFont(myFont2);
									f.add(jb_goBack);
									f.repaint();
									jb_goBack.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											f.dispose();
											frame5.setVisible(true);
										}
									});
									
									f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									f.setLayout(null);
									f.setVisible(true);
									
								}
							});

							jb_youragentdetail = new JButton("Your Agent Info ");
							jb_youragentdetail.setBounds(180, 160, 200, 30);
							container5.add(jb_youragentdetail);
							jb_youragentdetail.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									userid=jt_userid.getText();
									frame5.setVisible(false);
									f = new JFrame();
									f.setSize(600, 550);
									f.setTitle("YOUR AGENT DETAIL");
								
									JLabel jl_title = new JLabel("Agent Info: ");
									jl_title.setBounds(80, 40, 150, 25);
									jl_title.setFont(myFont2);
									f.add(jl_title);
									
									JLabel jl_userid = new JLabel("Agent ID: ");
									jl_userid.setBounds(80, 80, 150, 25);
									f.add(jl_userid);
									
									JTextField jtf_userid = new JTextField();
									jtf_userid.setBounds(200, 80, 170, 25);
									f.add(jtf_userid);
									
									JLabel jl_name = new JLabel("Agent Name: ");
									jl_name.setBounds(80, 120, 150, 25);
									f.add(jl_name);
									
									JTextField jtf_name = new JTextField();
									jtf_name.setBounds(200, 120, 170, 25);
									f.add(jtf_name);
									
									JLabel jl_gender = new JLabel("Gender: ");
									jl_gender.setBounds(80, 160, 150, 25);
									f.add(jl_gender);
									
									JTextField jtf_gender = new JTextField();
									jtf_gender.setBounds(200, 160, 170, 25);
									f.add(jtf_gender);
									
									JLabel jl_address = new JLabel("Address: ");
									jl_address.setBounds(80, 200, 150, 25);
									f.add(jl_address);
									
									JTextField jtf_address = new JTextField();
									jtf_address.setBounds(200, 200, 170, 25);
									f.add(jtf_address);
									
									JLabel jl_dob = new JLabel("Date of Birth: ");
									jl_dob.setBounds(80, 240, 100, 25);
									f.add(jl_dob);
									
									JTextField jtf_dob = new JTextField();
									jtf_dob.setBounds(200, 240, 170, 25);
									f.add(jtf_dob);
									
									
									JLabel jl_Phone = new JLabel("Contact no: ");
									jl_Phone.setBounds(80, 280, 100, 25);
									f.add(jl_Phone);
									
									JTextField jtf_phone = new JTextField();
									jtf_phone.setBounds(200, 280, 170, 25);
									f.add(jtf_phone);
									
									try {
										OracleDataSource ods = new OracleDataSource();
										String url = "jdbc:oracle:thin:@//192.168.43.94:1521/xe";
										String userId = "system";
										String password = "Astroy2018";
										ods.setURL(url);
										ods.setUser(userId);
										ods.setPassword(password);
										Connection connection = ods.getConnection();
										Statement statement = connection.createStatement();
										String query = "select a.agentid,a.agent_name,a.gender,a.DOB,a.Address,b.Phone from agent1 a join agent2 b on a.agentid=b.agentid join sales s on s.agent_id=a.agentid where s.claimant_id='"+userid+"'";
										ResultSet rs = statement.executeQuery(query);

										while (rs.next()) {
											jtf_userid.setText(rs.getString(1));
											jtf_name.setText(rs.getString(2));
											jtf_gender.setText(rs.getString(3));
											jtf_dob.setText(rs.getDate(4).toString());
											jtf_address.setText(rs.getString(5));
											jtf_phone.setText(rs.getString(6));
										}
										connection.close();
									} catch (SQLException se) {
										System.out.println(se);
									}
							
									jb_goBack=new JButton("Back");
									jb_goBack.setBounds(190,330,150,30);
									jb_goBack.setFont(myFont2);
									f.add(jb_goBack);
									f.repaint();
									jb_goBack.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											f.dispose();
											frame5.setVisible(true);
										}
									});
									
									f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									f.setLayout(null);
									f.setVisible(true);
									
								}
							});

							jb_yoursalesdetail = new JButton("Your Insurance Details ");
							jb_yoursalesdetail.setBounds(180, 200, 200, 30);
							container5.add(jb_yoursalesdetail);
							jb_yoursalesdetail.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									userid=jt_userid.getText();
									frame5.setVisible(false);
									f = new JFrame();
									f.setSize(600, 550);
									f.setTitle("INSURANCE DETAILS");
									
									JLabel jl_title = new JLabel("Insurance Details: ");
									jl_title.setBounds(80, 40, 150, 25);
									jl_title.setFont(myFont2);
									f.add(jl_title);
									
									JLabel jl_userid = new JLabel("Policy No: ");
									jl_userid.setBounds(80, 80, 150, 25);
									f.add(jl_userid);
									
									JTextField jtf_userid = new JTextField();
									jtf_userid.setBounds(200, 80, 170, 25);
									f.add(jtf_userid);
									
									JLabel jl_name = new JLabel("Insurance Name: ");
									jl_name.setBounds(80, 120, 150, 25);
									f.add(jl_name);
									
									JTextField jtf_name = new JTextField();
									jtf_name.setBounds(200, 120, 170, 25);
									f.add(jtf_name);
									
									JLabel jl_gender = new JLabel("Insured By AgentID: ");
									jl_gender.setBounds(80, 160, 150, 25);
									f.add(jl_gender);
									
									JTextField jtf_gender = new JTextField();
									jtf_gender.setBounds(200, 160, 170, 25);
									f.add(jtf_gender);
									
									JLabel jl_address = new JLabel("Policy Holder ID: ");
									jl_address.setBounds(80, 200, 150, 25);
									f.add(jl_address);
									
									JTextField jtf_address = new JTextField();
									jtf_address.setBounds(200, 200, 170, 25);
									f.add(jtf_address);
									
									JLabel jl_dob = new JLabel("Insured Date: ");
									jl_dob.setBounds(80, 240, 100, 25);
									f.add(jl_dob);
									
									JTextField jtf_dob = new JTextField();
									jtf_dob.setBounds(200, 240, 170, 25);
									f.add(jtf_dob);
									
									
									JLabel jl_Phone = new JLabel("Expiry Date: ");
									jl_Phone.setBounds(80, 280, 100, 25);
									f.add(jl_Phone);
									
									JTextField jtf_phone = new JTextField();
									jtf_phone.setBounds(200, 280, 170, 25);
									f.add(jtf_phone);
									
									try {
										OracleDataSource ods = new OracleDataSource();
										String url = "jdbc:oracle:thin:@//192.168.43.94:1521/xe";
										String userId = "system";
										String password = "Astroy2018";
										ods.setURL(url);
										ods.setUser(userId);
										ods.setPassword(password);
										Connection connection = ods.getConnection();
										Statement statement = connection.createStatement();
										String query = "select p.policy_no,p.insurance_name,s.agent_id,i.pholder_id,p.insured_date,p.expiry_date from policy p join sales s on s.policy_key=p.policy_no join insured_by1 i on i.agent_id=s.agent_id  where s.claimant_id='"+userid+"'";
										ResultSet rs = statement.executeQuery(query);

										while (rs.next()) {
											jtf_userid.setText(rs.getString(1));
											jtf_name.setText(rs.getString(2));
											jtf_gender.setText(rs.getString(3));
											jtf_address.setText(rs.getString(4));
											jtf_dob.setText(rs.getDate(5).toString());
											jtf_phone.setText(rs.getDate(6).toString());
										}
										connection.close();
									} catch (SQLException se) {
										System.out.println(se);
									}
							
									jb_goBack=new JButton("Back");
									jb_goBack.setBounds(190,330,150,30);
									jb_goBack.setFont(myFont2);
									f.add(jb_goBack);
									f.repaint();
									jb_goBack.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											f.dispose();
											frame5.setVisible(true);
										}
									});
									
									f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									f.setLayout(null);
									f.setVisible(true);
									
								}
							});

							jb_usr_logout = new JButton("Sign out ");
							jb_usr_logout.setBounds(180, 240, 200, 30);
							container5.add(jb_usr_logout);
							jb_usr_logout.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									frame5.dispose();
									frame1.setVisible(true);
								}
							});
							frame5.setLayout(null);
							frame5.setSize(600, 550);
							frame5.setVisible(true);
							frame5.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						}
					}
				});

				jb_back_user = new JButton("Back");
				jb_back_user.setBounds(250, 190, 100, 25);
				container3.add(jb_back_user);
				jb_back_user.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame3.setVisible(false);
						frame1.setVisible(true);
					}
				});

				jb_signup = new JButton("Sign Up");
				jb_signup.setBounds(190, 230, 100, 25);
				container3.add(jb_signup);
				jb_signup.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ae) {
						Font myFont1 = new Font("Serif", Font.BOLD, 24);

						frame3.setVisible(false);
						frame6 = new JFrame();
						frame6.setTitle("Registration Form");

						JLabel title = new JLabel("SIGN UP FORM");
						title.setBounds(200, 30, 200, 30);
						title.setFont(myFont1);
						frame6.add(title);

						JLabel jl_useridsup = new JLabel("User Id: ");
						jl_useridsup.setBounds(180, 90, 100, 30);
						frame6.add(jl_useridsup);

						JTextField jtf_useridsup = new JTextField();
						jtf_useridsup.setBounds(300, 90, 200, 30);
						frame6.add(jtf_useridsup);

						JLabel jl_email = new JLabel("Email: ");
						jl_email.setBounds(180, 130, 110, 30);
						frame6.add(jl_email);

						JTextField jtf_email = new JTextField();
						jtf_email.setBounds(300, 130, 200, 30);
						frame6.add(jtf_email);

						JLabel jl_passwordsup = new JLabel("Password: ");
						jl_passwordsup.setBounds(180, 170, 100, 30);
						frame6.add(jl_passwordsup);

						JTextField jtf_passwordsup = new JTextField();
						jtf_passwordsup.setBounds(300, 170, 200, 30);
						frame6.add(jtf_passwordsup);

						JButton jb_register = new JButton("Register");
						jb_register.setBounds(180, 210, 100, 30);
						frame6.add(jb_register);
						jb_register.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent Ae) {
								String str1 = jtf_useridsup.getText();
								String str2 = jtf_passwordsup.getText();
								String str3 = jtf_email.getText();

								try {
									OracleDataSource ods = new OracleDataSource();
									String url = "jdbc:oracle:thin:@//192.168.43.94:1521/xe";
									String userId = "system";
									String password = "Astroy2018";
									ods.setURL(url);
									ods.setUser(userId);
									ods.setPassword(password);
									Connection connection = ods.getConnection();
									Statement statement = connection.createStatement();
									String query = "insert into userlogin values('" + str1 + "','" + str2 + "','" + str3
											+ "')";
									statement.executeQuery(query);
									connection.close();
								} catch (SQLException se) {
									System.out.println(se);
								}
								JLabel mssg = new JLabel("Registered Successfully!");
								mssg.setBounds(220, 240, 300, 30);
								frame6.add(mssg);
								frame6.repaint();
							}

						});

						JButton jb_Back = new JButton("Back");
						jb_Back.setBounds(340, 210, 100, 30);
						frame6.add(jb_Back);
						jb_Back.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								frame6.dispose();
								frame1.setVisible(true);
							}
						});

						frame6.setSize(650, 500);
						frame6.setLayout(null);
						frame6.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						frame6.setVisible(true);
					}
				});

				frame1.setVisible(false);
				frame3.setSize(450, 400);
				frame3.setLayout(null);
				frame3.setVisible(true);
				frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			}
		});

		icon4 = new ImageIcon("D://insurance.jpg");
		JLabel ins = new JLabel(icon4);
		ins.setBounds(190, 90, 300, 157);
		container1.add(ins);
		
		JButton jb_exit = new JButton("Exit");
		jb_exit.setBounds(640,600,80,50);
		container1.add(jb_exit);
		jb_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				lastFrame = new JFrame();
				lastFrame.setSize(500,450);
				lastFrame.setTitle("Thank You Page");
				JLabel thankyou=new JLabel("Thank You");
				thankyou.setFont(myFont);
				thankyou.setBounds(130,70,200,50);
				lastFrame.add(thankyou);
				JTextArea jta = new JTextArea();
				jta.setBounds(100, 160, 290, 220);
				lastFrame.add(jta);
				jta.setText( "----------------------------------------------------------\nContribution:\n----------------------------------------------------------\nDatabase part: Managed by \nRishi Srikaanth\nPreetam Chaurasia.\nCoding part: Done by \nTenzin Khorlo \nLhendup Dorji.\n----------------------------------------------------------\n");
				jta.setFont(myFont2);
				
				lastFrame.setLayout(null);
				frame1.dispose();
				lastFrame.setVisible(true);
				
				
			}
		});

		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setVisible(true);

	}
	public void actionPerformed(ActionEvent ae) {
	
	}

	public static void main(String args[]) {
		EventQueue.invokeLater(() -> {
			new RegistrationForms();
		});

	}

}