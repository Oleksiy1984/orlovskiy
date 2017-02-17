import HW4.Calculator.Calculator;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author Alexey.
 */
public class CalculatorTest {

   private Calculator calc;

    //Will be run before any test in the class
    //Creation of object which will be used in all the test cases
    @Before
    public void setUp() throws Exception {
        calc = new Calculator();
        System.out.println("Before");
    }

    //Execute this method after executing
    //all the test cases. Include all clean up logics here
    @After
    public void tearDown() throws Exception {
        calc = null;
        System.out.println("After");
    }

    //This is a test case to be executed
    @Test
    public void testAdd() {
        double result = calc.add(10.5, 20.32);
        double expected=10.5+20.32;
        //This assert statement is used to verify the result
        //of the add method with the expected value
        assertEquals("Ошибка!",expected, result, 0);
    }

    //cause a test method to fail if it
    // takes longer than that number of milliseconds.
    @Test(timeout = 10)
    public void testDivide100() {
        for (int i = 1; i <1000 ; i++) {
            double expected=100/(double)i;
            double result = calc.divide(100, i);
            assertEquals(expected, result, 0.0001);
        }
    }

    @Test
    public void testSubtract() {
        double result = calc.subtract(100.5, 100);
        assertEquals(0.5, result, 0);
    }

    @Test
    public void testMultiply() {
        double result = calc.multiply(2.5, 100);
        assertEquals(250, result, 0);
    }

    @Test
    public void testDivide() {
        double result = calc.divide(100, 10);
        assertEquals(10, result, 0.001);

    }

    //The test case is expecting a
    //exception of type Arithmeticexception.
    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        calc.divide(15.5, 0);
    }

    @Ignore("This test is not ready yet")
    @Test
    public void testDivideByZero2() {
        calc.divide(3.5,2);
    }
}

