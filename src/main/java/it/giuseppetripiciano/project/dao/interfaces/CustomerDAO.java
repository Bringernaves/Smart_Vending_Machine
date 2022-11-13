package it.giuseppetripiciano.project.dao.interfaces;

import java.util.List;

public interface CustomerDAO {
	public byte connect(int idUser, int idMachine);
	public byte disconnect(int idUser, int idMachine);
	public byte recharge(int idUser, String credit);
	public List<List<String>> checkCredit(int idUser);
	public byte changeEmail(int idUser, String psw, String email);
	public byte changePassword(int idUser, String oldPsw, String psw);
}
