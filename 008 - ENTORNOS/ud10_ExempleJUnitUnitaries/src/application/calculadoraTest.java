package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class calculadoraTest {

     // Definim difrents objectes per a utilitzar-los alas tests
 
    static calculadora calc1;
    static calculadora calc2;
    
    //Aquest m�tode s'executaria el primer
    @BeforeAll
    public static void inici() {
 
        //Creem els objectes
 
        calc1=new calculadora(23,34);
        calc2=new calculadora(40,20);
 
    }
    
    //Test per al m�tode sumar
    @Test
    public void sumaTest(){
         
        //Tornar� true
        Assert.assertEquals(57,calc1.suma());
 
        //Tornar� false, la suma ha de ser 60
        Assert.assertEquals(60,calc2.suma());
 
    }
    
    //Test per al m�tode restar
    @Test
    public void restaTest(){
         
        //Tornar� true
        Assert.assertEquals(57,calc1.resta());
 
        //Tornar� false, la suma ha de ser 60
        Assert.assertEquals(20,calc2.resta());
 
    }

}
