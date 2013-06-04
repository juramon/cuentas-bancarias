package edu.tallerweb.cuentas;

/**
 * La más compleja de las cuentas, ésta permite establecer una
 * cantidad de dinero a girar en descubierto. Es por ello que
 * cada vez que se desee extraer dinero, no sólo se considera
 * el que se posee, sino el límite adicional que el banco
 * estará brindando.
 *
 * Por supuesto esto no es gratis, ya que el banco nos cobrará
 * un 5% como comisión sobre todo el monto en descubierto
 * consumido en la operación.
 *
 * Por ejemplo, si tuviéramos $ 100 en la cuenta, y quisiéramos
 * retirar $ 200 (con un descubierto de $ 150), podremos hacerlo.
 * Pasaremos a deberle al banco $ 105 en total: los $ 100 que
 * nos cubrió, más el 5% adicional sobre el descubierto otorgado.
 */
public class CuentaCorriente extends AbstractCuenta{
		private Double saldoDescubierto;
		private Double descubiertoTotal;
		public CuentaCorriente(){
			super.setSaldo(new Double("0"));
			saldoDescubierto=new Double(150);
			this.descubiertoTotal=new Double (150);
		}
	/**
	 * Toda cuenta corriente se inicia con un límite total
	 * para el descubierto.
	 * @param descubiertoTotal
	 */
	public CuentaCorriente(final Double descubiertoTotal) {
		super.setSaldo(new Double(0));
		this.saldoDescubierto=descubiertoTotal;
		this.descubiertoTotal=descubiertoTotal;
	}
	/**
	 * Todo depósito deberá cubrir primero el descubierto,
	 * si lo hubiera, y luego contar para el saldo de la
	 * cuenta.
	 * @param monto a depositar
	 */
	public void depositar(final Double monto) {
		if(monto<0){
			throw new CuentaBancariaException("CuentaSueldo No se puede depositar un monto negativo");
		}
		if(getSaldo().intValue()>0){
			this.setSaldo(getSaldo()+monto);
		
		} else {
			Double depositoDescubierto=descubiertoTotal-saldoDescubierto;
			if(monto>depositoDescubierto){
				saldoDescubierto=descubiertoTotal;
				this.setSaldo(monto-depositoDescubierto);
			}else{
				this.setSaldo(new Double (0));
				saldoDescubierto+=monto;
			}
		}
		
		System.out.println("CuentaCorriente Deposito "+monto+" -> El saldo es:"+getSaldo() + " el saldo descubierto es:"+ saldoDescubierto);
	}
	/**
	 * Se cobrará el 5% de comisión sobre el monto girado
	 * en descubierto.
	 * Por supuesto, no puede extraerse más que el total
	 * de la cuenta, más el descubierto (comisión incluída)
	 * @param monto a extraer
	 */
	public void extraer(final Double monto) {
		if(monto<0){
			throw new CuentaBancariaException("CuentaCorriente No se puede extraer un monto negativo");
		}
		if(getSaldo()<monto){
			Double importeDescubierto=(monto-getSaldo())+((monto-getSaldo())*0.05);
			if(saldoDescubierto>importeDescubierto){
				saldoDescubierto-=importeDescubierto;
				this.setSaldo(new Double(0));
				}
			else{
				System.out.println("CuentaCorriente Extracci�n Fallida "+monto+" -> El saldo es:"+getSaldo() + " el disponible descubierto es: "+getDescubierto());
				throw new CuentaBancariaException("La operacion no puede realizarse, el saldo es insuficiente");
			}
		}
		else {
			setSaldo(getSaldo()-monto);
		}
		System.out.println("CuentaCorriente Extracci�n "+monto+" -> El saldo es:"+getSaldo());		
	}

	/**
	 * Permite saber el saldo de la cuenta
	 * @return el saldo de la cuenta
	 */
	public Double getSaldo() {
		return super.getSaldo();
	}
	
	/**
	 * Permite saber el saldo en descubierto
	 * @return el descubierto de la cuenta
	 */
	public Double getDescubierto() {
		return saldoDescubierto;
	}

}
