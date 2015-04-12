package br.ufrpe.bcc.ivote.gui;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.GroupLayout;
import javax.swing.JFrame;

import org.apache.derby.impl.drda.NetworkServerControlImpl;

import br.ufrpe.bcc.ivote.data.Cache;

@SuppressWarnings("serial")
public class iVoteFrame extends JFrame {
	
	public static iVoteFrame instance;
	
	public static final String PRESENTATION = "PRESENTATION";
	public static final String USERAUTH = "USERAUTH";
	public static final String VOTE = "VOTE";
	public static final String POLLRESULTS = "POLLRESULTS";

	public static void show(String panel) {
		if (panel.equals("POLLRESULTS"))
			PollResults.updateResults();
		instance.exibir(panel);
	}

	public static void quit() {
		try {
			Cache.getInstance().save();

			System.out.println("Encerrando banco de dados e desligando JVM");
			new NetworkServerControlImpl().shutdown();
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			System.exit(0);
		}
	}

	public iVoteFrame() {
		initComponents();
		setExtendedState(6);

		instance = this;

		getContentPane().setLayout(new CardLayout());

		getContentPane().add(new Presentation(), "PRESENTATION");
		getContentPane().add(new UserAuth(), "USERAUTH");
		getContentPane().add(new Vote(), "VOTE");
		getContentPane().add(new PollResults(), "POLLRESULTS");
	}

	private void exibir(String panel) {
		CardLayout cl = (CardLayout) getContentPane().getLayout();
		cl.show(getContentPane(), panel);
	}

	private void initComponents() {
		setDefaultCloseOperation(3);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				iVoteFrame.this.formWindowClosing(evt);
			}
		});
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 400, 32767));

		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 300, 32767));

		pack();
	}

	private void formWindowClosing(WindowEvent evt) {
		quit();
	}
}