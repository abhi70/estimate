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
public class FlatMarkupTest {
	public Type type;
	public double basePrice; 
	public double expectedFlatCharges;
    public MarkupRatesEnum inputMarkupEnum;    	
	public FlatMarkupTest(Type t, double inPrice,MarkupRatesEnum inMarkEnum, double expected){
		type = t; 
		basePrice = inPrice;
		inputMarkupEnum = inMarkEnum;
		expectedFlatCharges = expected;
	}
	enum Type {VALIDBASERATEVALUE, NEGATIVEBASERATEVALUE, ZEROBASERATEVALUE}; 
	
	
	@Parameters 
	public static Collection<Object[]> data(){
		Object[][] dataList = new Object[][]{{Type.VALIDBASERATEVALUE, 1501.0099,MarkupRatesEnum.FLAT ,1576.060395 },{Type.ZEROBASERATEVALUE,0,MarkupRatesEnum.FLAT,0}, {Type.NEGATIVEBASERATEVALUE,-1.0,MarkupRatesEnum.FLAT,-1 },{Type.VALIDBASERATEVALUE,.0099 ,MarkupRatesEnum.FLAT,0.010395}};
		return Arrays.asList(dataList);				
	
	}
	
	
	@Test
	public void testBaseRateChargesValidValues() {
		Assume.assumeTrue(type==Type.VALIDBASERATEVALUE);
		assertEquals("Expected flat rate charge does not match actual flat rate charge",expectedFlatCharges,JobCostEstimator.calculateFlatCharges(basePrice),0.0001);	
	}
	
	@Test
	public void testNegativeBaseRateCharges() {
		Assume.assumeTrue(type==Type.NEGATIVEBASERATEVALUE);
		assertEquals("Expected flat rate charge does not match actual flat rate charge",expectedFlatCharges,JobCostEstimator.calculateFlatCharges(basePrice),0.0);
	}
	
	
	@Test
	public void testZeroBaseRateCharges() {
		Assume.assumeTrue(type==Type.ZEROBASERATEVALUE);
		assertEquals("Expected flat rate charge does not match actual flat rate charge",expectedFlatCharges,JobCostEstimator.calculateFlatCharges(basePrice),0.0);	
	}
	

	
}




