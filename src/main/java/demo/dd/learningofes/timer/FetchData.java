
/*
 * ------------------------------------------------------------------
 * Copyright @ 2022 Hangzhou DtDream Technology Co.,Ltd. All rights reserved.
 * ------------------------------------------------------------------
 *       Product: DataRiver
 *   Module Name:
 *  Date Created: 2022/12/28
 *   Description:
 * ------------------------------------------------------------------
 * Modification History
 * DATE            Name           Description
 * ------------------------------------------------------------------
 * 2022/12/28       底迪 z2358
 * ------------------------------------------------------------------
 */
package demo.dd.learningofes.timer;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author DiDi
 * @description 定时器
 * @date 2022/12/28 9:50
 */

@EnableScheduling
@Component
public class FetchData {

    @Scheduled(cron = "*/5 * * * * ?")
    public void fetData() {
        System.out.println(System.currentTimeMillis());
    }
}
