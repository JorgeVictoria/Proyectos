package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class alumnadoTest {

	//objetos para testeat el producto 
    static calculadora prod1;
    static calculadora prod2;
    static calculadora prod3;
    
    //objetos para pruebas de signo en las operaciones
    static calculadora signo1;
    static calculadora signo2;
    static calculadora signo3;
    static calculadora signo4;
    static calculadora signo5;
    static calculadora signo6;
    static calculadora signo7;
    static calculadora signo8;
    static calculadora signo9;
    static calculadora signo10;
    static calculadora signo11;
    static calculadora signo12;
    
    //Preparamos las pruebas
    @BeforeAll
    public static void inici() {

 
        prod1=new calculadora(10,20);
        prod2=new calculadora(5,7);
        prod3=new calculadora(100,5);
        
        signo1 = new calculadora(3,5);
        signo2 = new calculadora(-3,5);
        signo3 = new calculadora(-3,-5);
        signo4 = new calculadora(3,5);
        signo5 = new calculadora(-3,5);
        signo6 = new calculadora(-3,-5);
        signo7 = new calculadora(3,5);
        signo8 = new calculadora(-3,5);
        signo9 = new calculadora(-3,-5);
        signo10 = new calculadora(8,2);
        signo11 = new calculadora(-8,2);
        signo12 = new calculadora(-8,-2);
 
    }
    
  //Test para el metodo multiplica
    @Test
    public void multTest(){
        
    	//pruebas del metodo multiplica. Daran todas correcto
        Assert.assertEquals(200,prod1.multiplica());
        Assert.assertEquals(35,prod2.multiplica());
        Assert.assertEquals(500,prod3.multiplica());
 
    }
    
    //test para evaluar los signos
    @Test
    public void signoTest() {
    	
    	//pruebas para probar el funcionamiento del signo
    	//en los diferentes metodos
    	//3 purebas por metodo
    	Assert.assertEquals(8,signo1.suma());
        Assert.assertEquals(2,signo2.suma());
        Assert.assertEquals(-8,signo3.suma());
        Assert.assertEquals(-2,signo4.resta());
        Assert.assertEquals(-8,signo5.resta());
        Assert.assertEquals(2,signo6.resta());
        Assert.assertEquals(15,signo7.multiplica());
        Assert.assertEquals(-15,signo8.multiplica());
        Assert.assertEquals(15,signo9.multiplica());
        Assert.assertEquals(4,signo10.divide());
        Assert.assertEquals(-4,signo11.divide());
        Assert.assertEquals(4,signo12.divide());
    }

}
