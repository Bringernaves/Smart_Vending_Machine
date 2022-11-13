package it.giuseppetripiciano.project.dao.interfaces;

import java.util.List;

public interface AccountDAO {
	public byte registerCustomer(String firstname, String lastname, String email, String psw);
	public List<List<String>> userLogin(String email, String psw);
	public List<List<String>> machineLogin(int id, String psw);
}
