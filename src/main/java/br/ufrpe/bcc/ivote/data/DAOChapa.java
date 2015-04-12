package br.ufrpe.bcc.ivote.data;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import br.ufrpe.bcc.database.dao.DAO;

public class DAOChapa extends DAO<Chapa> {
	
	private Properties pp;

	public DAOChapa(Properties pp) {
		this.pp = pp;
	}

	public void add(Chapa u) throws SQLException {
		open(this.pp);

		String query = "insert into chapa (nome) values (?)";
		PreparedStatement ps = this.con.prepareStatement(query);
		Throwable localThrowable2 = null;
		try {
			ps.setString(1, u.getNome());
			ps.execute();
		} catch (Throwable localThrowable1) {
			localThrowable2 = localThrowable1;
			throw localThrowable1;
		} finally {
			if (ps != null)
				if (localThrowable2 != null)
					try {
						ps.close();
					} catch (Throwable x2) {
						localThrowable2.addSuppressed(x2);
					}
				else
					ps.close();
		}
		close();
		notifica();
	}

	public void remove(Serializable id) throws SQLException {
		open(this.pp);

		String query = "delete from chapa";
		PreparedStatement ps = this.con.prepareStatement(query);
		Throwable localThrowable2 = null;
		try {
			ps.execute();
		} catch (Throwable localThrowable1) {
			localThrowable2 = localThrowable1;
			throw localThrowable1;
		} finally {
			if (ps != null)
				if (localThrowable2 != null)
					try {
						ps.close();
					} catch (Throwable x2) {
						localThrowable2.addSuppressed(x2);
					}
				else
					ps.close();
		}
		close();
		notifica();
	}

	public void update(Chapa u) throws SQLException {
		open(this.pp);

		String query = "update eleicao set votos = ? where chapa_id = ?";
		PreparedStatement ps = this.con.prepareStatement(query);
		Throwable localThrowable2 = null;
		try {
			ps.setInt(1, u.getVotos());
			ps.setInt(2, u.getId());
			ps.execute();
		} catch (Throwable localThrowable1) {
			localThrowable2 = localThrowable1;
			throw localThrowable1;
		} finally {
			if (ps != null)
				if (localThrowable2 != null)
					try {
						ps.close();
					} catch (Throwable x2) {
						localThrowable2.addSuppressed(x2);
					}
				else
					ps.close();
		}
		close();
		notifica();
	}

	public Chapa get(Serializable id) throws SQLException {
		open(this.pp);

		Chapa ch = new Chapa();

		String query = "select c.id, c.nome, e.votos from chapa as c inner join eleicao as e on c.id = e.chapa_id where c.id = ?";
		PreparedStatement ps = this.con.prepareStatement(query);
		Throwable localThrowable2 = null;
		try {
			ps.setInt(1, ((Integer) id).intValue());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ch.setId(rs.getInt("id"));
				ch.setNome(rs.getString("nome"));
				ch.setVotos(rs.getInt("votos"));
			}
		} catch (Throwable localThrowable1) {
			localThrowable2 = localThrowable1;
			throw localThrowable1;
		} finally {
			if (ps != null)
				if (localThrowable2 != null)
					try {
						ps.close();
					} catch (Throwable x2) {
						localThrowable2.addSuppressed(x2);
					}
				else
					ps.close();
		}
		close();
		return ch;
	}

	public List<Chapa> get() throws SQLException {
		open(this.pp);

		List<Chapa> chs = new ArrayList<Chapa>();

		String query = "select c.id, c.nome, e.votos from chapa as c inner join eleicao as e on c.id = e.chapa_id";
		PreparedStatement ps = this.con.prepareStatement(query);
		Throwable localThrowable2 = null;
		try {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Chapa ch = new Chapa();
				ch.setId(rs.getInt("id"));
				ch.setNome(rs.getString("nome"));
				ch.setVotos(rs.getInt("votos"));
				chs.add(ch);
			}
		} catch (Throwable localThrowable1) {
			localThrowable2 = localThrowable1;
			throw localThrowable1;
		} finally {
			if (ps != null)
				if (localThrowable2 != null)
					try {
						ps.close();
					} catch (Throwable x2) {
						localThrowable2.addSuppressed(x2);
					}
				else
					ps.close();
		}
		close();
		return chs;
	}
}