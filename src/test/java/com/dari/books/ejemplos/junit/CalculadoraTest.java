package com.dari.books.ejemplos.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculadoraTest {

	Calculadora calcula;
	
	@BeforeAll
	public static void primero() {
		System.out.println("primero");
	}
	
	@AfterAll
	public static void ultimo() {
		
		System.out.println("ultimo");
	}
	
	@BeforeEach
	public void instaciaObjeto() {
	 calcula = new Calculadora();
	 System.out.println("BeforeEach");
	}

	@AfterEach
	public void despuesTest() {
		System.out.println("AfterEach");
	}
	
	
	@Test
	@DisplayName("prueba que ocupa assetEqual")
	@Disabled("esta prueb NO EJECUTA")
	
	public  void calculadoraAssertEqualTest() {
		
		
		assertEquals(2, calcula.sumar(1, 1));
		assertEquals(2, calcula.restar(4, 2));
		assertEquals(2, calcula.dividir(8, 4));
		assertEquals(32, calcula.multiplicar(8, 4));
		System.out.println("calculadoraAssertEqualTest");
	} 
	
	@Test
	public void calculadoraTrueFalse() {
		
		
		assertTrue(calcula.sumar(1, 1)==2);
		assertTrue(calcula.restar(3, 2)==1);
		assertTrue(calcula.multiplicar(4, 3)==12);
		assertTrue(calcula.dividir(12, 4)==3);
	
		System.out.println("calculadoraTrueFalse");
	}
		

	

}
