package soft;

public class DurationRule implements BookingRuleStrategy {

	private int maxDuration ;
	
	public DurationRule(int max)
	{
		this.maxDuration=max;
	}
	@Override
	public boolean isValid(Appointment  app) {
		// TODO Auto-generated method stub
		
		return app.getDuration()<=maxDuration;
	}
      
	
}
