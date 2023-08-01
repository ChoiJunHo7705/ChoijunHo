package scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PointIncreaseScheduler {
    private static final int POINT_INCREASE_INTERVAL_SECONDS = 20;

    public static void main(String[] args) {
        // 스케줄러 생성
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // ① "스케줄러가 시작되었음"을 출력
        System.out.println("스케줄러가 시작되었음");

        // 스케줄러 작업 생성 및 등록
        Runnable pointIncreaseJob = new Runnable() {
            @Override
            public void run() {
                // ② "Job이 실행되어, 몇 명에게 1포인트가 부여되었는지"를 출력
                // 여기에 포인트를 1씩 증가시키는 로직을 구현해야 함 (DB와 연동 필요)
                // 예시로 임시로 증가된 포인트 수를 출력
                int increasedPointCount = 1;
                System.out.println("Job이 실행되어, " + increasedPointCount + "명에게 1포인트가 부여되었음");
            }
        };

        // 스케줄러에 작업 등록 (초기 지연 0초, 20초마다 반복 실행)
        scheduler.scheduleAtFixedRate(pointIncreaseJob, 0, POINT_INCREASE_INTERVAL_SECONDS, TimeUnit.SECONDS);
    }
}
