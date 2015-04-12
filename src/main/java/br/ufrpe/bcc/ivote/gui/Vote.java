package br.ufrpe.bcc.ivote.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import br.ufrpe.bcc.continuous.LinkEffectHandler;
import br.ufrpe.bcc.ivote.data.Cache;
import br.ufrpe.bcc.ivote.data.Chapa;

@SuppressWarnings("serial")
public class Vote extends JPanel {

	private List<Chapa> chapas;
	private JPanel pnchapas;

	public Vote() {
		initComponents();
		this.chapas = Cache.getInstance().getChapas();
		renderChapaButton();
	}

	private void renderChapaButton() {
		for (Chapa ch : this.chapas) {
			JLabel lb = new JLabel(ch.getNome());
			lb.setPreferredSize(new Dimension(150, 80));
			lb.setHorizontalAlignment(0);
			lb.setVerticalAlignment(0);
			lb.addMouseListener(new LinkEffectHandler());
			lb.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
			lb.setFont(new Font("Segoe UI Light", 0, 24));
			lb.addMouseListener(new Action());

			this.pnchapas.add(lb);
		}

		this.pnchapas.updateUI();
	}

	private Chapa getChapa(String name) {
		for (Chapa ch : this.chapas) {
			if (ch.getNome().equals(name)) {
				return ch;
			}
		}

		return new Chapa();
	}

	private void initComponents() {
		this.pnchapas = new JPanel();

		this.pnchapas.setLayout(new FlowLayout(1, 5, 25));

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addContainerGap().addComponent(this.pnchapas, -1, 990, 32767).addContainerGap()));

		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup()
						.addContainerGap().addComponent(this.pnchapas, -1, 467, 32767).addContainerGap()));
	}

	private class Action extends MouseAdapter {
		private Action() {
		}

		public void mouseClicked(MouseEvent e) {
			JLabel lb = (JLabel) e.getSource();
			String chapa = lb.getText();
			Chapa ch = Vote.this.getChapa(chapa);
			ch.setVotos(ch.getVotos() + 1);

			iVoteFrame.show("POLLRESULTS");
		}
	}
}