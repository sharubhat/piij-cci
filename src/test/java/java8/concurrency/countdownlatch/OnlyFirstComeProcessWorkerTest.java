package java8.concurrency.countdownlatch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class OnlyFirstComeProcessWorkerTest {
  @Test
  public void whenDoingLotsOfThreadsInParallel_thenLetOnlyOneDoTheBackgroundRefresh() throws InterruptedException {
    List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
    CountDownLatch callingThreadBlocker = new CountDownLatch(1);
    CountDownLatch completedThreadCounter = new CountDownLatch(10);
    AtomicBoolean processing = new AtomicBoolean(false);

    List<Thread> workers = Stream
        .generate(() -> new Thread(new OnlyFirstComeProcessWorker(
            outputScraper, callingThreadBlocker, completedThreadCounter, processing)))
        .limit(10)
        .collect(toList());

    workers.forEach(e -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e1) {
        e1.printStackTrace();
      }
      e.start();
    });
    completedThreadCounter.await();
    outputScraper.add("Workers complete");

    assertThat(outputScraper)
        .containsExactly(
            "Someone else is working",
            "Someone else is working",
            "Someone else is working",
            "Someone else is working",
            "Work completed",
            "Counted down",
            "Someone else finished working",
            "Someone else finished working",
            "Someone else finished working",
            "Someone else finished working",
            "Workers complete"
        );
  }

}