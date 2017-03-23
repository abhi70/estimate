package abhi.estimate;

public class JobCostEstimator {

    //method to calculate the flat markup price.		
		public static double calculateFlatCharges(double base_price)
		{
			double basePlusFlatCharge;
			if(base_price==0)
				return 0;
			else if (base_price<0)
				return -1;
			else{
			try{
				basePlusFlatCharge=base_price+(base_price*MarkupRatesEnum.FLAT.value);
			}
			catch (NumberFormatException e)
			{
				base_price=0.0;
				e.printStackTrace();
				return 0;
			};
			}

			return basePlusFlatCharge;
			
		}
		
	//method to calculate the markup based on number of people involved in the job	
		public static double calculateEmployeeCharges(double basePlusFlatCharge, int employeeCount)
		{
		
			double employeeCharge; 
			if(basePlusFlatCharge==0|| employeeCount==0)
				return 0;
			if (basePlusFlatCharge<0|| employeeCount<0)
				return -1;
			try{
				employeeCharge = employeeCount * MarkupRatesEnum.PERSON.value* basePlusFlatCharge;
					}
			catch(NumberFormatException e)
			{
				basePlusFlatCharge=0;
				employeeCount=0;
				e.printStackTrace();
				return 0;
			}
			
			  
			return employeeCharge;
		}
		
		/*** method to calculate the markup based on category.
	    //assumption is that the base price is provided as input and flat rate is calculated using calFlatMarkup
	    //and the value is added together and provided as input for the first parameter ***/
		public static double calculateCategoryCharges(double basePlusFlatPrice, MarkupRatesEnum rate){
			
			double categoryCharge;
			if((basePlusFlatPrice == 0)  )
			{
				return 0;
			}
			if(basePlusFlatPrice<0)
			{
				return -1;
			}
	 
			try{
		   categoryCharge = basePlusFlatPrice * rate.value; 
			}
			
			catch(NumberFormatException e)
			{
				basePlusFlatPrice=0;
				e.printStackTrace();
				return 0;
			}
		
			return categoryCharge;
		}
			
		
		//Assumptions are that the required rates are already calculated above in the application
		//to get a final estimate
		public static double getTotalEstimate(double baseRate, double flatRateCharge, double personRateCharge, double categoryRateCharge)
		{
		 	return baseRate+= flatRateCharge+personRateCharge+categoryRateCharge;
		}
}
