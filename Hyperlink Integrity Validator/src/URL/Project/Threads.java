/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package URL.Project;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Mohamed
 */
public class Threads {
    public static ExecutorService es;
    public static int threadCount;
    public static int depth;
    public static String link;
    public static boolean checking = false;

    public static void checkLinks() {
        checking = true;

        System.out.println("Thread(s): " + threadCount + "     Depth: " + (depth - 1) + "\n");

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            //
        }

        es = Executors.newFixedThreadPool(threadCount);

        ValidationOfLink.startTime = System.currentTimeMillis();

        if (threadCount > 1) {
            es.execute(new ValidationOfLink(link, depth));
        } else {
            ValidationOfLink valLink = new ValidationOfLink(link, depth);
            valLink.run();
        }

        if (threadCount > 1) {
            while (true) {
                if (es.isTerminated()) {
                    break;
                }
            }
        }

        ValidationOfLink.endTime = System.currentTimeMillis();
        checking = false;

        ValidationOfLink.timeElapsed = ValidationOfLink.endTime - ValidationOfLink.startTime;

        System.out.println("Execution time: " + ValidationOfLink.timeElapsed / 1000 + " seconds");
    }
}