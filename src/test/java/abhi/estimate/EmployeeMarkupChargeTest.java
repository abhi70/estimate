package abhi.estimate;

import static org.junit.Assert.*;
import org.junit.Assume;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class EmployeeMarkupChargeTest {
	public Type type;
	public double basePrice; 
	public double expectedEmployeeCharges;
    public MarkupRatesEnum employeeRate;    	
    public int numEmployees;
	public EmployeeMarkupChargeTest(Type t, double inPrice,MarkupRatesEnum inEmployeeMarkupRate, int inNumEmployees,double expected){
		type = t; 
		numEmployees = inNumEmployees;
		basePrice = inPrice;
		employeeRate = inEmployeeMarkupRate;
		expectedEmployeeCharges = expected;
	}
	enum Type {VALIDBASERATEVALUE, NEGATIVEBASERATEVALUE, ZEROBASERATEVALUE, ZEROEMPLOYEES}; 
	
	
	@Parameters 
	public static Collection<Object[]> data(){
		Object[][] dataList = new Object[][]{
			{Type.VALIDBASERATEVALUE,1341 ,MarkupRatesEnum.PERSON,41,659.772},
			{Type.VALIDBASERATEVALUE,4200.321 ,MarkupRatesEnum.PERSON,800,40323.0816},
			{Type.ZEROBASERATEVALUE,0,MarkupRatesEnum.PERSON,100, 0}, 
			{Type.ZEROEMPLOYEES,23110.313,MarkupRatesEnum.PERSON,0, 0}
		};
			return Arrays.asList(dataList);				
		}
	
	
	@Test
	public void testCategoryChargesWithValidFlatRateValues() {
		Assume.assumeTrue(type==Type.VALIDBASERATEVALUE);
		assertEquals("Expected flat rate charge does not match actual flat rate charge",expectedEmployeeCharges,JobCostEstimator.calculateEmployeeCharges(basePrice, numEmployees),0.0001);	
	}
	
	@Test
	public void testCategoryChargesWithNegativeFlatRateCharges() {
		Assume.assumeTrue(type==Type.NEGATIVEBASERATEVALUE);
		assertEquals("Expected flat rate charge does not match actual flat rate charge",expectedEmployeeCharges,JobCostEstimator.calculateEmployeeCharges(basePrice, numEmployees),0.0);
	}
	
	
	@Test
	public void testCategoryChargesWithZeroFlatRateCharges() {
		Assume.assumeTrue(type==Type.ZEROBASERATEVALUE);
		assertEquals("Expected flat rate charge does not match actual flat rate charge",expectedEmployeeCharges,JobCostEstimator.calculateEmployeeCharges(basePrice, numEmployees),0.0);	
	}
		
	@Test 
	public void testEmployeeChargeWithZeroEmployees(){
		Assume.assumeTrue(type==Type.ZEROEMPLOYEES);
		assertEquals("Expected flat rate charge does not match actual flat rate charge",expectedEmployeeCharges,JobCostEstimator.calculateEmployeeCharges(basePrice, numEmployees),0.0);	
	
	}
	
}




