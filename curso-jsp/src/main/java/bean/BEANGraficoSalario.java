package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BEANGraficoSalario implements Serializable {

	private static final long serialVersionUID = 1L;

	List<Double> salarioList = new ArrayList<Double>();
	List<String> cargoList = new ArrayList<String>();
	public List<Double> getSalarioList() {
		return salarioList;
	}
	public void setSalarioList(List<Double> salarioList) {
		this.salarioList = salarioList;
	}
	public List<String> getCargoList() {
		return cargoList;
	}
	public void setCargoList(List<String> cargoList) {
		this.cargoList = cargoList;
	}
	
	
}
