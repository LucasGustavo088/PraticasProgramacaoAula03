package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Pais;

public class PaisDao {
	
	
	//CRUD  
	public void criar(Pais pais) {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?, ?, ?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					pais.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void atualizar(Pais pais) {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, pais.getNome());
			stm.setLong(2, pais.getPopulacao());
			stm.setDouble(3, pais.getArea());
			stm.setInt(4, pais.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void excluir(Pais pais) {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, pais.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Pais carregar(int id) {
		Pais pais = new Pais();
		pais.setId(id);
	    String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.id = ?";
	    // usando o try with resources do Java 7, que fecha o que abriu
	    try (Connection conn = ConnectionFactory.obtemConexao();
	        PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
	      stm.setInt(1, pais.getId());
	      try (ResultSet rs = stm.executeQuery();) {
	        if (rs.next()) {
	        	stm.setString(1, pais.getNome());
				stm.setLong(2, pais.getPopulacao());
				stm.setDouble(3, pais.getArea());
	        } else {
	          pais.setId(-1);
	          stm.setString(1, null);
	          stm.setLong(2, (Long) null);
	          stm.setDouble(3, (Double) null);
	        }
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
	    } catch (SQLException e1) {
	      System.out.print(e1.getStackTrace());
	    }
	    
	    return pais;
	}
	
	public ArrayList<Pais> carregarMultiplosPaises(int quantidadePaises) {
		ArrayList<Pais> paises = new ArrayList<Pais>();
		String sqlSelect = "SELECT * FROM pais LIMIT ?";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, quantidadePaises);
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					long populacao = rs.getLong("populacao");
					double area = rs.getDouble("area");
					
					paises.add(new Pais(id, nome, populacao, area));
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return paises;
	}
	
	public Pais carregarPaisMaiorNumeroHabitantes() {
		Pais PaisMaiorNumeroHabitantes = new Pais();
		String sqlSelect = "SELECT * FROM pais ORDER BY populacao DESC LIMIT 1";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					long populacao = rs.getLong("populacao");
					double area = rs.getDouble("area");
					
					PaisMaiorNumeroHabitantes = new Pais(id, nome, populacao, area);
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return PaisMaiorNumeroHabitantes;
	}
	
	public Pais carregarPaisMenorArea() {
		Pais paisMenorArea = new Pais();
		String sqlSelect = "SELECT * FROM pais ORDER BY area asc LIMIT 1";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String nome = rs.getString("nome");
					long populacao = rs.getLong("populacao");
					double area = rs.getDouble("area");
					
					paisMenorArea = new Pais(id, nome, populacao, area);
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		
		return paisMenorArea;
	}

}
