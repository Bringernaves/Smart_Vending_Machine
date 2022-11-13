package it.giuseppetripiciano.project.dao.interfaces;

public interface AdminDAO {
	public byte registerTechnician(String firstname, String lastname, String email, String psw);
	public byte registerMachine(String psw);
}
