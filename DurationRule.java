package soft;

public class DurationRule implements BookingRuleStrategy {

	private int maxDuration ;
	
	DurationRule(int max)
	{
		this.maxDuration=max;
	}
	@Override
	public boolean isValid(Appointement app) {
		// TODO Auto-generated method stub
		
		return app.getDuration()<=maxDuration;
	}
      
	
}
