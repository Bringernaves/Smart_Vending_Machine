package it.giuseppetripiciano.project.service.interfaces;

public interface MachineService {
	public byte checkStatus(Object machine);
	public void resetStatus(Object machine);
	public byte sellProduct(Object machine, int idProduct);
}
