package abhi.estimate;
 public enum MarkupRatesEnum {
		FLAT(0.05), PERSON(0.012), PHARM(0.075),FOOD(0.13), ELECTRONICS(0.02), OTHER(0.0);
		public double value;
		private MarkupRatesEnum(double val) { this.value = val; }


	
}
