package Software;


/**
 * قاعدة للتحقق من الحد الأقصى للمشاركين في الموعد الواحد.
 * تنفذ واجهة {@link BookingRuleStrategy} كجزء من نمط التصميم Strategy.
 * * @author  Nadeen
 * @version 1.0
 */
public class ParticipantLimitRule implements BookingRuleStrategy {

    /** الحد الأقصى المسموح به لعدد المشاركين */
    private int maxParticipants;

    /**
     * منشئ القاعدة لتعريف الحد الأقصى للمشاركين.
     * * @param maxParticipants العدد الأقصى المسموح به (مثلاً 10 أشخاص).
     */
    public ParticipantLimitRule(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    /**
     * يتحقق مما إذا كان عدد المشاركين في الموعد يقع ضمن النطاق المسموح.
     * * @param app كائن الموعد المراد فحصه.
     * @return true إذا كان عدد المشاركين أقل من أو يساوي الحد الأقصى، و false خلاف ذلك.
     */
    @Override
    public boolean isValid(Appointment app) {
        // التحقق من أن عدد المشاركين لا يتجاوز السعة القصوى
        return app.getParticipants() <= maxParticipants;
    }
}
