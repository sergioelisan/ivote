package br.ufrpe.bcc.ivote.data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

import javax.swing.Timer;

import br.ufrpe.bcc.database.dao.DAO;
import br.ufrpe.bcc.observador.Observador;

public class Cache implements Observador {
	
	private static Cache instance = new Cache();
	private List<Chapa> chapas;
	private Properties pp;
	private DAO<Chapa> dao;
	
	public static Cache getInstance() {
		return instance;
	}

	private Cache() {
		this.pp = new Properties();
		this.pp.setProperty("user", "dacc");
		this.pp.setProperty("passwd", "dacc");
		this.pp.setProperty("url", "jdbc:derby://localhost:1527/ivote");
		this.pp.setProperty("driver", "org.apache.derby.jdbc.ClientDriver");
		try {
			this.dao = new DAOChapa(this.pp);
		} catch (Exception ex) {
			System.out.println(ex + "\n" + ex.getMessage());
		}

		this.dao.registra(this);
		update();

		int delay = 600000;
		Timer autosave = new Timer(delay, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cache.this.save();
			}
		});
		autosave.start();
	}

	public List<Chapa> getChapas() {
		return this.chapas;
	}

	public int getTotalVotos() {
		int total = 0;
		for (Chapa ch : this.chapas) {
			total += ch.getVotos();
		}

		return total;
	}

	public void update() {
		try {
			this.chapas = this.dao.get();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void save() {
		try {
			for (Chapa ch : this.chapas)
				this.dao.update(ch);
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
}