package it.giuseppetripiciano.project.service.interfaces;

public interface AdminService {
	public byte registerTechnician(Object user, String firstname, String lastname, String email, String psw);
	public byte registerMachine(Object user, String psw);
}
