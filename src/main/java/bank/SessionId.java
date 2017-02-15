package bank;

import java.util.UUID;

/**
 * Created by AMCBR on 14/02/2017.
 */
public class SessionId {

    private UUID id;
    private Long timeStamp;

    public SessionId(UUID uuid){

        id = uuid;
        timeStamp = System.currentTimeMillis();
    }

    public UUID getId() {
        return id;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }
}
