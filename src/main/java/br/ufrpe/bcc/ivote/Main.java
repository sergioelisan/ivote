package br.ufrpe.bcc.ivote;

import java.awt.Dimension;
import java.io.File;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.apache.derby.impl.drda.NetworkServerControlImpl;

import br.ufrpe.bcc.continuous.WallpaperPanel;
import br.ufrpe.bcc.ivote.data.Cache;
import br.ufrpe.bcc.ivote.gui.iVoteFrame;

public class Main {
	
	private JFrame splash;
	private JFrame ivoteFrame;

	public static void main(String[] args) {
		new Main().init();
	}

	public void init() {
		try {
			loadSplash();
			loadDatabase();
			loadCache();
			loadUI();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	private void loadCache() {
		Cache.getInstance();
	}

	private void loadSplash() {
		this.splash = new JFrame();
		this.splash.getContentPane().add(new WallpaperPanel("/splash.png"));
		this.splash.setSize(new Dimension(1366, 768));
		this.splash.setLocationRelativeTo(null);
		this.splash.setUndecorated(true);
		this.splash.setVisible(true);
	}

	private void loadDatabase() throws Exception {
		String userDir = System.getProperty("user.dir");
		System.setProperty("derby.system.home", userDir + File.separator + "db");
		new NetworkServerControlImpl().start(new PrintWriter(System.out));
	}

	private void loadUI() {
		this.splash.dispose();
		this.splash = null;

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main.this.ivoteFrame = new iVoteFrame();
				Main.this.ivoteFrame.setVisible(true);
			}
		});
	}
}