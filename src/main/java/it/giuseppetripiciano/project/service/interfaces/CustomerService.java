package it.giuseppetripiciano.project.service.interfaces;

public interface CustomerService {
	 public byte connect(Object user, int idMachine);
	 public byte disconnect(Object user);
	 public byte recharge(Object user, String addedCredit);
	 public void checkCredit(Object user);
	 public byte changeEmail(Object user, String psw, String email);
	 public byte changePassword(Object user, String oldPsw, String psw);
}
