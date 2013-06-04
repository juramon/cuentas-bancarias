package edu.tallerweb.cuentas;

import org.junit.Assert;
import org.junit.Test;

public class CuentaTests {

	@Test
	public void queVerifiqueLaConsigna() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(4000.0);

		Assert.assertEquals(
				"al depositar $ 4000.0 en una cuenta vacía, tiene $ 4000.0",
				4000.0, cuenta.getSaldo(), 0.0);

		cuenta.extraer(500.0);
	
		Assert.assertEquals(
				"al extraer $ 500.0 de una cuenta con $ 4000.0 se obtienen $ 3500.0",
				3500.0, cuenta.getSaldo(), 0.0);
	}

	@Test(expected=CuentaBancariaException.class)
	public void queVerifiqueLaConsignaException() {
		CuentaSueldo cuenta = new CuentaSueldo();
		cuenta.depositar(3500.0);
		cuenta.extraer(4000.0);
	}
	@Test
	public void queVerifiqueLaConsignaCajaAhorros() {
		CajaAhorros cuenta = new CajaAhorros();
		cuenta.depositar(4000.0);

		Assert.assertEquals(
				"al depositar $ 4000.0 en una cuenta vacía, tiene $ 4000.0",
				4000.0, cuenta.getSaldo(), 0.0);
		cuenta.extraer(500.0);
		cuenta.extraer(100.0);
		cuenta.extraer(100.0);
		cuenta.extraer(100.0);
		cuenta.extraer(100.0);
		cuenta.extraer(100.0);
		cuenta.extraer(100.0);
		Assert.assertEquals(
				"al extraer despues de la quinta extraccion el saldo debe ser",
				2888.0, cuenta.getSaldo(), 0.0);
	}
	@Test(expected=CuentaBancariaException.class)
	public void queVerifiqueLaConsignaCuentaCorriente() {
		CuentaCorriente cuenta = new CuentaCorriente(new Double (600));
		cuenta.depositar(4000.0);

		Assert.assertEquals(
				"al depositar $ 4000.0 en una cuenta vacía, tiene $ 4000.0",
				4000.0, cuenta.getSaldo(), 0.0);

		cuenta.extraer(500.0);
		cuenta.extraer(4000.0);
		cuenta.extraer(100.0);
		
		Assert.assertEquals(
				"al extraer $ 4000.0 de una cuenta con $ 3500.0 se obtienen $ 100 de saldo descubierto",
				75.0, cuenta.getDescubierto(), 0.0);
	}
}
