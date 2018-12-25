package keyboard;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class FakeClock extends Clock {
    private final ZoneId DEFAULT_TZONE = ZoneId.systemDefault();
    private Instant currentTime;

    public FakeClock() {
        currentTime = Instant.parse("2018-12-03T00:00:00.00Z");
    }

    @Override
    public ZoneId getZone() {
        return DEFAULT_TZONE;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return Clock.fixed(currentTime, zone);
    }

    @Override
    public Instant instant() {
        return currentTime;
    }

    void advanceTime() {
        currentTime = currentTime.plusMillis(1000);
    }
}