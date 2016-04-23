package baza;

import java.sql.*;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

import oracle.jdbc.pool.OracleDataSource;

public class Baza
{
	private Connection conn;
	private ArrayList<Uczen> uczList;
	private ArrayList<Nauczyciel> nauList;

	public class Uczen
	{
		private int id;
		private String imie;
		private String nazwisko;

		public Uczen(int _id, String _imie, String _nazwisko)
		{
			id = _id;
			imie = _imie;
			nazwisko = _nazwisko;
		}

		public int getId()
		{
			return id;
		}

		public String getImie()
		{
			return imie;
		}

		public String getNazwisko()
		{
			return nazwisko;
		}
	}

	public class Nauczyciel
	{
		private int id;
		private String nazwisko;

		public Nauczyciel(int _id, String _n)
		{
			id = _id;
			nazwisko = _n;
		}

		public int getId()
		{
			return id;
		}

		public void setId(int id)
		{
			this.id = id;
		}

		public String getNazwisko()
		{
			return nazwisko;
		}

		public void setNazwisko(String nazwisko)
		{
			this.nazwisko = nazwisko;
		}
	}

	public Baza() throws SQLException
	{
		OracleDataSource ods = new OracleDataSource();
		ods.setURL("jdbc:oracle:thin:mbyczkow/mbyczkow@ora3.elka.pw.edu.pl:1521:ora3inf");

		conn = ods.getConnection();

		conn.setAutoCommit(false);

		DatabaseMetaData meta = conn.getMetaData();

		System.out.println("JDBC driver version id " + meta.getDriverVersion());
	}

	public void ShowStudents() throws SQLException
	{
		Statement stmt = null;
		String selectString = "SELECT id_ucznia, imie, nazwisko FROM uczen ";
		Savepoint sp = conn.setSavepoint();
		uczList = new ArrayList<Uczen>();
		
		try
		{
			stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(selectString);

			while (rset.next())
			{
				int Id_ucznia = rset.getInt(1);
				String Imie = rset.getString(2);
				String Nazwisko = rset.getString(3);
				uczList.add(new Uczen(Id_ucznia, Imie, Nazwisko));
			}

		} catch (SQLException ex)
		{
			ex.printStackTrace();
			System.out.println("Transaction is being rolled back");
			conn.rollback(sp);
		} finally
		{
			conn.commit();
		}
	}

	public void SelectTeacher() throws SQLException
	{
		Statement stmt = null;
		String selectString = "SELECT id_nauczyciela, nazwisko FROM nauczyciel ";
		Savepoint sp = conn.setSavepoint();
		nauList = new ArrayList<Nauczyciel>();

		try
		{
			stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(selectString);

			while (rset.next())
			{
				int Id_nauczyciela = rset.getInt(1);
				String Nazwisko = rset.getString(2);
				nauList.add(new Nauczyciel(Id_nauczyciela, Nazwisko));
			}

		} catch (SQLException ex)
		{
			ex.printStackTrace();
			System.out.println("Transaction is being rolled back");
			conn.rollback(sp);
		} finally
		{
			conn.commit();
		}
	}

	public void EraseStudent(int ID) throws SQLException
	{

		PreparedStatement insert = null;

		String insertString = ("DELETE FROM uczen WHERE id_ucznia= ?");
		Savepoint sp = conn.setSavepoint();

		try
		{
			insert = conn.prepareStatement(insertString);
			insert.setInt(1, ID);
			insert.executeUpdate();

		} catch (SQLException ex)
		{
			ex.printStackTrace();
			System.out.println("Transaction is being rolled back");
			conn.rollback(sp);
		} finally
		{
			conn.commit();
			insert.close();
			System.out.println("\n\nUczen zostal usuniety z bazy danych.\n");
		}
	}

	public int StudentCounter() throws SQLException
	{
		Statement stmt = null;
		String selectString = "SELECT COUNT(id_ucznia) FROM UCZEN";
		Savepoint sp = conn.setSavepoint();
		int counter = 0;

		try
		{
			stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(selectString);

			while (rset.next())
			{
				counter = rset.getInt(1);
			}

		} catch (SQLException ex)
		{
			ex.printStackTrace();
			System.out.println("Transaction is being rolled back");
			conn.rollback(sp);
		} finally
		{
			conn.commit();
		}
		return counter;
	}

	public int TeacherCounter() throws SQLException
	{
		Statement stmt = null;
		String selectString = "SELECT COUNT(id_nauczyciela) FROM NAUCZYCIEL";
		Savepoint sp = conn.setSavepoint();
		int counter = 0;

		try
		{
			stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(selectString);

			while (rset.next())
			{
				counter = rset.getInt(1);
			}

		} catch (SQLException ex)
		{
			ex.printStackTrace();
			System.out.println("Transaction is being rolled back");
			conn.rollback(sp);
		} finally
		{
			conn.commit();
		}
		return counter;
	}

	public void UpdateStudent(int ID) throws SQLException
	{
		PreparedStatement insert = null;
		String insertString = "UPDATE uczen SET nazwisko = 'Kowalski' WHERE id_ucznia= ?";
		Savepoint sp = conn.setSavepoint();

		try
		{
			insert = conn.prepareStatement(insertString);
			insert.setInt(1, ID);
			insert.executeUpdate();
		} catch (SQLException ex)
		{
			ex.printStackTrace();
			System.out.println("Transaction is being rolled back");
			conn.rollback(sp);
		} finally
		{
			conn.commit();
			insert.close();
		}
	}

	public void AddStudent(String imie, String nazwisko, String plec,
			String pesel) throws SQLException
	{
		PreparedStatement insert = null;
		String insertString = "INSERT INTO uczen VALUES (seq_uczen.nextval, ?, ?, ?, ?)";
		Savepoint sp = conn.setSavepoint();
		Scanner input;
		input = new Scanner(System.in);

		try
		{
			insert = conn.prepareCall(insertString);

			insert.setString(1, imie);
			insert.setString(2, nazwisko);
			insert.setString(3, plec);
			insert.setString(4, pesel);

			insert.executeUpdate();
		} catch (SQLException ex)
		{
			ex.printStackTrace();
			System.out.println("Transaction is being rolled back");
			conn.rollback(sp);
		} finally
		{
			conn.commit();
			insert.close();
			System.out.println("\n\n Uczen zostal dodany do bazy danych.\n");
		}
	}

	public ArrayList<Uczen> getUczList()
	{
		return uczList;
	}

	public ArrayList<Nauczyciel> getNauList()
	{
		return nauList;
	}
}
