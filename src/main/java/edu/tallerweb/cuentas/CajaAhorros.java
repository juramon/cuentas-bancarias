package edu.tallerweb.cuentas;

/**
 * Similar a la CuentaSueldo, pero se pide que luego de la
 * quinta extracci贸n de dinero se cobre un costo adicional
 * por extracci贸n de $ 6
 */
public class CajaAhorros extends AbstractCuenta{
	private int cantidad;
	public CajaAhorros(){
		super.setSaldo(new Double("0"));
		cantidad=0;
	}
	/**
	 * No hay reglas adicionales para el dep贸sito
	 * @param monto a depositar
	 */
	public void depositar(final Double monto) {
		this.setSaldo(getSaldo()+monto);
		System.out.println("Deposito "+monto+" -> El saldo es:"+getSaldo());
	}

	/**
	 * Se cobran $6 adicionales por cada extracci贸n luego de
	 * la quinta.
	 * @param monto a extraer
	 */
	public void extraer(final Double monto) {
		Double saldoActual=getSaldo();
		Double montoActual=monto;
		cantidad++;
		if(cantidad>5){
			montoActual +=6;
		}
		if (saldoActual<montoActual){
			System.out.println("Extracci髇 Fallida "+montoActual+" -> El saldo es:"+getSaldo());
			throw new CuentaBancariaException("La operacion no puede realizarse, el saldo es insuficiente");
		}
		else {
			saldoActual -= montoActual;
			this.setSaldo(saldoActual);
		}
		System.out.println("Extracci髇 "+montoActual+" -> El saldo es:"+getSaldo());
		}
	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */
	public Double getSaldo() {
		return super.getSaldo();
	}

}
