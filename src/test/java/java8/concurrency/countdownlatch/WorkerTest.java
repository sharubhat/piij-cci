package java8.concurrency.countdownlatch;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

public class WorkerTest {
  @Test
  public void whenParallelProcessing_thenMainThreadWillBlockUntilCompletion()
    throws InterruptedException {
    List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
    CountDownLatch countDownLatch = new CountDownLatch(5);
    List<Thread> workers = Stream
        .generate(() -> new Thread(new Worker(outputScraper, countDownLatch)))
        .limit(5)
        .collect(toList());

    workers.forEach(Thread::start);
    countDownLatch.await();
    outputScraper.add("Latch released");

    assertThat(outputScraper)
        .containsExactly(
            "Counted down",
            "Counted down",
            "Counted down",
            "Counted down",
            "Counted down",
            "Latch released"
        );
  }

}