package br.ufrpe.bcc.ivote.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ufrpe.bcc.continuous.LinkEffectHandler;

@SuppressWarnings("serial")
public class Presentation extends JPanel {
	
	private JLabel jLabel1;
	private JPanel jPanel1;
	private JLabel lbresultados;
	private JLabel lbvotar;

	public Presentation() {
		initComponents();

		this.lbresultados.addMouseListener(new LinkEffectHandler());
		this.lbvotar.addMouseListener(new LinkEffectHandler());
	}

	private void initComponents() {
		this.jLabel1 = new JLabel();
		this.jPanel1 = new JPanel();
		this.lbvotar = new JLabel();
		this.lbresultados = new JLabel();

		this.jLabel1.setHorizontalAlignment(0);
		this.jLabel1.setIcon(new ImageIcon(getClass().getResource("/logo.png")));
		ResourceBundle bundle = ResourceBundle.getBundle("br/ufrpe/bcc/ivote/gui/Bundle");
		this.jLabel1.setText(bundle.getString("Presentation.jLabel1.text"));

		this.jPanel1.setLayout(new FlowLayout(1, 30, 100));

		this.lbvotar.setBackground(new Color(51, 153, 255));
		this.lbvotar.setFont(new Font("Segoe UI Light", 0, 24));
		this.lbvotar.setHorizontalAlignment(0);
		this.lbvotar.setText(bundle.getString("Presentation.lbvotar.text"));
		this.lbvotar.setMaximumSize(new Dimension(300, 50));
		this.lbvotar.setMinimumSize(new Dimension(300, 50));
		this.lbvotar.setPreferredSize(new Dimension(300, 50));
		this.lbvotar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				Presentation.this.lbvotarMouseClicked(evt);
			}
		});
		this.jPanel1.add(this.lbvotar);

		this.lbresultados.setBackground(new Color(51, 153, 255));
		this.lbresultados.setFont(new Font("Segoe UI Light", 0, 24));
		this.lbresultados.setHorizontalAlignment(0);
		this.lbresultados.setText(bundle.getString("Presentation.lbresultados.text"));
		this.lbresultados.setMaximumSize(new Dimension(300, 50));
		this.lbresultados.setMinimumSize(new Dimension(300, 50));
		this.lbresultados.setPreferredSize(new Dimension(300, 50));
		this.lbresultados.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				Presentation.this.lbresultadosMouseClicked(evt);
			}
		});
		this.jPanel1.add(this.lbresultados);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(this.jLabel1, -1, -1, 32767).addComponent(this.jPanel1, -1, 936, 32767))
				.addContainerGap()));

		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap()
								.addComponent(this.jLabel1, -1, 298, 32767).addGap(18, 18, 18)
								.addComponent(this.jPanel1, -2, -1, -2).addGap(13, 13, 13)));
	}

	private void lbvotarMouseClicked(MouseEvent evt) {
		iVoteFrame.show("USERAUTH");
	}

	private void lbresultadosMouseClicked(MouseEvent evt) {
		iVoteFrame.show("POLLRESULTS");
	}
}