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
public class CategoryMarkupChargeTest {
	public Type type;
	public double basePlusFlatPrice; 
	public double expectedCategoryCharges;
    public MarkupRatesEnum inputCategory;    	
	public CategoryMarkupChargeTest(Type t, double inbasePlusFlatPrice,MarkupRatesEnum inMarkEnum, double expected){
		type = t; 
		basePlusFlatPrice = inbasePlusFlatPrice;
		inputCategory = inMarkEnum;
		expectedCategoryCharges = expected;
	}
	enum Type {VALIDBASERATEVALUE, NEGATIVEBASERATEVALUE, ZEROBASERATEVALUE}; 
	
	
	@Parameters 
	public static Collection<Object[]> data(){
		Object[][] dataList = new Object[][]{
			{Type.VALIDBASERATEVALUE,.0994200 ,MarkupRatesEnum.ELECTRONICS,0.0019984},
			{Type.VALIDBASERATEVALUE,4200 ,MarkupRatesEnum.ELECTRONICS,84},
			{Type.ZEROBASERATEVALUE,0,MarkupRatesEnum.ELECTRONICS,0}, 
			{Type.NEGATIVEBASERATEVALUE,-1.0,MarkupRatesEnum.ELECTRONICS,-1 },
			{Type.VALIDBASERATEVALUE,0100000000.213 ,MarkupRatesEnum.FOOD,13000000.02769},
			{Type.VALIDBASERATEVALUE,0.7678 ,MarkupRatesEnum.FOOD,0.0998414},
			{Type.ZEROBASERATEVALUE,0,MarkupRatesEnum.FOOD,0}, 
			{Type.NEGATIVEBASERATEVALUE,-1.0,MarkupRatesEnum.FOOD,-1 },
			{Type.VALIDBASERATEVALUE, 90000000 ,MarkupRatesEnum.OTHER,0},
			{Type.VALIDBASERATEVALUE,.0099 ,MarkupRatesEnum.OTHER,0},
			{Type.ZEROBASERATEVALUE,0,MarkupRatesEnum.OTHER,0},
			{Type.NEGATIVEBASERATEVALUE,-1000000.01,MarkupRatesEnum.OTHER,-1 },
			{Type.VALIDBASERATEVALUE,90000000 ,MarkupRatesEnum.PHARM,6750000},
		    {Type.VALIDBASERATEVALUE,.0099 ,MarkupRatesEnum.PHARM,0.0007425},
			{Type.ZEROBASERATEVALUE,0,MarkupRatesEnum.PHARM,0},
			{Type.NEGATIVEBASERATEVALUE,-1000000.01,MarkupRatesEnum.PHARM,-1 }};	
			return Arrays.asList(dataList);				
		}
	
	
	@Test
	public void testCategoryChargesWithValidFlatRateValues() {
		Assume.assumeTrue(type==Type.VALIDBASERATEVALUE);
		assertEquals("Expected flat rate charge does not match actual flat rate chage",expectedCategoryCharges,JobCostEstimator.calculateCategoryCharges(basePlusFlatPrice, inputCategory),0.0001);	
	}
	
	@Test
	public void testCategoryChargesWithNegativeFlatRateCharges() {
		Assume.assumeTrue(type==Type.NEGATIVEBASERATEVALUE);
		assertEquals("Expected flat rate charge does not match actual flat rate chage",expectedCategoryCharges,JobCostEstimator.calculateCategoryCharges(basePlusFlatPrice, inputCategory),0.0);
	}
	
	
	@Test
	public void testCategoryChargesWithZeroFlatRateCharges() {
		Assume.assumeTrue(type==Type.ZEROBASERATEVALUE);
		assertEquals("Expected flat rate charge does not match actual flat rate chage",expectedCategoryCharges,JobCostEstimator.calculateCategoryCharges(basePlusFlatPrice, inputCategory),0.0);	
	}
	

	
}




