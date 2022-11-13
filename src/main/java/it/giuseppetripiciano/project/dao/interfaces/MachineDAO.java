package it.giuseppetripiciano.project.dao.interfaces;

import java.util.List;

public interface MachineDAO {
	public List<List<String>> checkStatus (int idMachine);
	public List<List<String>> checkStock (int idMachine);
	public byte sellProduct(int idMachine, int idUser, int idProduct);
}
