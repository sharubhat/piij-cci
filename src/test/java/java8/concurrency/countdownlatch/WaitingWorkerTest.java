package java8.concurrency.countdownlatch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class WaitingWorkerTest {
  @Test
  public void whenDoingLotsOfThreadsInParallel_thenStartThemAtTheSameTime()
      throws InterruptedException {

    List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
    CountDownLatch readyThreadCounter = new CountDownLatch(5);
    CountDownLatch callingThreadBlocker = new CountDownLatch(1);
    CountDownLatch completedThreadCounter = new CountDownLatch(5);
    List<Thread> workers = Stream
        .generate(() -> new Thread(new WaitingWorker(
            outputScraper, readyThreadCounter, callingThreadBlocker, completedThreadCounter)))
        .limit(5)
        .collect(toList());

    workers.forEach(Thread::start);
    readyThreadCounter.await();
    outputScraper.add("Workers ready");
    callingThreadBlocker.countDown();
    completedThreadCounter.await();
    outputScraper.add("Workers complete");

    assertThat(outputScraper)
        .containsExactly(
            "Workers ready",
            "Counted down",
            "Counted down",
            "Counted down",
            "Counted down",
            "Counted down",
            "Workers complete"
        );
  }
}