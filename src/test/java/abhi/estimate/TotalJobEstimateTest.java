package abhi.estimate;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

public class TotalJobEstimateTest {



	@Test
	public void totalEstimateTest() {
		//1364.9895 +49.139622 + 177.448635 
		assertEquals("The total estimate doesn't match the expected total estimate", JobCostEstimator.getTotalEstimate(1299.99, 64.9995, 49.139622, 177.448635),1591.577757,0.0 );
		
	}

}
