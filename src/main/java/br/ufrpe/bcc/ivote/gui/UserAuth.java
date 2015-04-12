package br.ufrpe.bcc.ivote.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UserAuth extends JPanel {
	
	private JPanel jPanel1;
	private JLabel lbstatus;
	private JTextField txtcpf;

	public UserAuth() {
		initComponents();
	}

	private void initComponents() {
		this.jPanel1 = new JPanel();
		this.txtcpf = new JTextField();
		this.lbstatus = new JLabel();

		this.jPanel1.setLayout(new FlowLayout(1, 5, 160));

		this.txtcpf.setFont(new Font("Segoe UI Light", 0, 24));
		ResourceBundle bundle = ResourceBundle.getBundle("br/ufrpe/bcc/ivote/gui/Bundle");
		this.txtcpf.setText(bundle.getString("UserAuth.txtcpf.text"));
		this.txtcpf.setMaximumSize(new Dimension(380, 50));
		this.txtcpf.setMinimumSize(new Dimension(380, 50));
		this.txtcpf.setPreferredSize(new Dimension(380, 50));
		this.txtcpf.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent evt) {
				UserAuth.this.txtcpfFocusGained(evt);
			}
		});
		this.txtcpf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				UserAuth.this.txtcpfKeyReleased(evt);
			}
		});
		this.jPanel1.add(this.txtcpf);

		this.lbstatus.setFont(new Font("Segoe UI Light", 0, 24));
		this.lbstatus.setText(bundle.getString("UserAuth.lbstatus.text"));

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(this.lbstatus, -1, -1, 32767).addComponent(this.jPanel1, -1, 962, 32767))
						.addContainerGap()));

		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addComponent(this.jPanel1, -2, 368, -2).addGap(18, 18, 18)
								.addComponent(this.lbstatus, -1, 40, 32767).addContainerGap()));
	}

	private void txtcpfFocusGained(FocusEvent evt) {
		if (this.txtcpf.getText().trim().equals("Insira CPF"))
			this.txtcpf.setText(null);
	}

	private void txtcpfKeyReleased(KeyEvent evt) {
		if (evt.getKeyCode() == 10) {
			iVoteFrame.show("VOTE");
			this.txtcpf.setText("Insira CPF");
		}
	}
}