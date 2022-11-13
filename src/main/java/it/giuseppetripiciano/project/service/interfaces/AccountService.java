package it.giuseppetripiciano.project.service.interfaces;

import java.util.List;

public interface AccountService {
	public byte registerCustomer(String firstname, String lastname, String email, String psw);
	public List<Object> userLogin(String email, String psw);
	public List<Object> machineLogin(int id, String psw);
	public void logout(Object account);
}
