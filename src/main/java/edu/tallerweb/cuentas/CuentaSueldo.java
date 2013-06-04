package edu.tallerweb.cuentas;

/**
 * Es el tipo de cuenta más simple, ya que se rige por la premisa
 * de que en tanto y en cuanto se tenga tanto o más dinero en
 * cuenta del que se quiere extraer, la operación se debe efectuar
 * correctamente.
 */
public class CuentaSueldo extends AbstractCuenta {

	public CuentaSueldo(){
		super.setSaldo(new Double("0"));
	}
	/**
	 * No hay reglas adicionales para el depósito
	 * @param monto a depositar
	 */
	public void depositar(final Double monto) {
		this.setSaldo(getSaldo()+monto);
		System.out.println("Deposito "+monto+" -> El saldo es:"+getSaldo());
	}
	/**
	 * No hay reglas adicionales para la extracción
	 * @param monto a extraer
	 */
	public void extraer(final Double monto) {
		Double saldoActual=getSaldo();
		if (saldoActual<monto){
			System.out.println("Extracci�n Fallida "+monto+" -> El saldo es:"+getSaldo());
			throw new CuentaBancariaException("La operacion no puede realizarse, el saldo es insuficiente");
		}
		else {
			saldoActual -= monto;
			this.setSaldo(saldoActual);
		}
		System.out.println("Extracci�n "+monto+" -> El saldo es:"+getSaldo());
		}

	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */
	public Double getSaldo() {
		return super.getSaldo();
	}

}
