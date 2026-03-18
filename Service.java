package Software;

public class Service{

    private String name;
    private String type;
    private Schedule schedule ;
    public Service(String name, String Type, Schedule schedule) {
        this.name = name;
        this.Type=Type;
        this.schedule=schedule;
    }
    public void setSchedule(Schedule schedule) {
    	 this.schedule=schedule;
    }
    public Schedule getSchedule() {
        return schedule;
    }
    public void addTimeSlot(TimeSlot slot)
    {
    	schedule.addTimeSlot(slot);
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
}