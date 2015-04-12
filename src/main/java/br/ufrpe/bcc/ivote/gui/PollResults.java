package br.ufrpe.bcc.ivote.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import br.ufrpe.bcc.ivote.data.Cache;
import br.ufrpe.bcc.ivote.data.Chapa;

@SuppressWarnings("serial")
public class PollResults extends JPanel {
	
	private static PollResults instance;
	
	private JLabel btretornar;
	private JLabel lbvotar;
	private JPanel pnResults;

	public PollResults() {
		initComponents();
		instance = this;
	}

	public static void updateResults() {
		instance.pnResults.removeAll();
		int totalVotos = Cache.getInstance().getTotalVotos();
		for (Chapa ch : Cache.getInstance().getChapas()) {
			JLabel lb = new JLabel(new StringBuilder().append(ch.getNome()).append(" - ")
					.append(totalVotos != 0 ? ch.getVotos() * 100 / totalVotos : 0).append("%, ").append(ch.getVotos())
					.append(" votos").toString());

			lb.setPreferredSize(new Dimension(310, 20));
			lb.setFont(new Font("Segoe UI Light", 0, 24));

			instance.pnResults.add(lb);
		}
		instance.pnResults.updateUI();
	}

	private void initComponents() {
		this.lbvotar = new JLabel();
		this.pnResults = new JPanel();
		this.btretornar = new JLabel();

		this.lbvotar.setBackground(new Color(51, 153, 255));
		this.lbvotar.setFont(new Font("Segoe UI Light", 0, 24));
		this.lbvotar.setForeground(new Color(255, 255, 255));
		this.lbvotar.setHorizontalAlignment(0);
		ResourceBundle bundle = ResourceBundle.getBundle("br/ufrpe/bcc/ivote/gui/Bundle");
		this.lbvotar.setText(bundle.getString("PollResults.lbvotar.text"));
		this.lbvotar.setMaximumSize(new Dimension(300, 50));
		this.lbvotar.setMinimumSize(new Dimension(300, 50));
		this.lbvotar.setOpaque(true);
		this.lbvotar.setPreferredSize(new Dimension(300, 50));

		this.pnResults.setMaximumSize(new Dimension(319, 32767));
		this.pnResults.setMinimumSize(new Dimension(319, 10));
		this.pnResults.setPreferredSize(new Dimension(319, 421));

		this.btretornar.setBackground(new Color(51, 153, 255));
		this.btretornar.setFont(new Font("Segoe UI Light", 0, 24));
		this.btretornar.setForeground(new Color(255, 255, 255));
		this.btretornar.setHorizontalAlignment(0);
		this.btretornar.setIcon(new ImageIcon(getClass().getResource("/home.png")));
		this.btretornar.setText(bundle.getString("PollResults.btretornar.text"));
		this.btretornar.setMaximumSize(new Dimension(300, 50));
		this.btretornar.setMinimumSize(new Dimension(300, 50));
		this.btretornar.setOpaque(true);
		this.btretornar.setPreferredSize(new Dimension(300, 50));
		this.btretornar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				PollResults.this.btretornarMouseClicked(evt);
			}
		});
		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
								.addComponent(this.pnResults, -1, -1, 32767).addComponent(this.lbvotar, -1, -1, 32767))
				.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 578, 32767)
				.addComponent(this.btretornar, -2, 39, -2).addContainerGap()));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(this.lbvotar, -2, 41, -2).addComponent(this.btretornar, -2, 41, -2))
				.addGap(18, 18, 18).addComponent(this.pnResults, -1, -1, 32767).addContainerGap()));
	}

	private void btretornarMouseClicked(MouseEvent evt) {
		iVoteFrame.show("PRESENTATION");
	}
}