package service;
import java.util.ArrayList;

import dao.PaisDao;
import model.Pais;

public class PaisService {

	public PaisDao dao = new PaisDao();

	public void criar(Pais pais) {
		dao.criar(pais);
	}
	
	public void atualizar(Pais pais) {
		dao.atualizar(pais);
	}

	public void excluir(Pais pais) {
		dao.excluir(pais);
	}

	public void carregar(int id) {
		dao.carregar(id);
	}
	
	public ArrayList<Pais> carregarMultiplosPaises(int quantidadePaises) {
		return dao.carregarMultiplosPaises(quantidadePaises);
	}
	
	public Pais carregarPaisMaiorNumeroHabitantes() {
		return dao.carregarPaisMaiorNumeroHabitantes();
	}
	
	public Pais carregarPaisMenorArea() {
		return dao.carregarPaisMenorArea();
	}
}
