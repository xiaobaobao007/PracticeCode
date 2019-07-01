package Date;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaobaobao
 * @date 2019/6/28 13:57
 */
public class InatantTest {
    public static void main(String[] args) {
        Instant now = Instant.now().plusMillis(TimeUnit.HOURS.toMillis(8));
        System.out.println("秒数:"+now.getEpochSecond());
        System.out.println("毫秒数:"+now.toEpochMilli());

        System.out.println(Instant.now());
        System.out.println(Instant.now().atZone(ZoneId.systemDefault()));

        OffsetDateTime time=now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(time);
    }
}
