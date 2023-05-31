package URL.Project;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ThreadPoolExecutor;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * ValidationOfLink class implements the Runnable interface.
 */
public class ValidationOfLink implements Runnable {
    public String link;
    public static int depth = 0;
    public static int Val = 0;
    public static int inVal = 0;
    public static long startTime;
    public static long endTime;
    public static long timeElapsed;
    public static int links = -1;

    public ValidationOfLink(String link, int depth) {
        this.link = link;
        ValidationOfLink.depth = depth;
    }

    @Override
    public void run() {
        if (depth > 0) {
            links++;
            String q = "";
            boolean recur = false;

            try {
                Document doc = Jsoup.connect(link).get();
                Elements z = doc.select("a[href]");

                for (int i = 0; i < z.size(); i++) {
                    System.out.println("Valid: " + Val + "   Invalid: " + inVal);
                    recur = false;
                    String tempq = link;

                    q = z.get(i).attr("href");

                    if (q.startsWith("https://")) {
                        if (validateLink(q)) {
                            recur = true;
                            Val++;
                            System.out.println("Link is Valid: " + q);
                        } else if (!validateLink(q)) {
                            inVal++;
                            System.out.println("Link is Invalid: " + q);
                        }

                        if (recur) {
                            if (Threads.threadCount > 1) {
                                Threads.es.execute(new ValidationOfLink(q, depth - 1));
                            } else {
                                ValidationOfLink recurValLink = new ValidationOfLink(q, depth - 1);
                                recurValLink.run();
                            }
                        }
                    } else {
                        URL url = new URL(link);
                        String website = url.getProtocol() + "://" + url.getHost();
                        tempq = website + q;

                        if (validateLink(tempq)) {
                            Val++;
                            System.out.println("Link is Valid: " + tempq);
                            recur = true;
                        } else if (!validateLink(tempq)) {
                            inVal++;
                            System.out.println("Link is Invalid: " + tempq);
                        }

                        if (recur) {
                            if (Threads.threadCount > 1) {
                                Threads.es.execute(new ValidationOfLink(tempq, depth - 1));
                            } else {
                                ValidationOfLink recurValLink = new ValidationOfLink(tempq, depth - 1);
                                recurValLink.run();
                            }
                        }
                    }
                }

                if (Threads.threadCount > 1 && ((ThreadPoolExecutor) Threads.es).getActiveCount() == 1) {
                    Threads.es.shutdown();
                }
            } catch (Exception ex) {
                // Catches any exceptions thrown by the analysis block
            }
        }
    }

    private boolean validateLink(String link) throws IOException, URISyntaxException {
        boolean valid = false;
        try {
            new URL(link).toURI();
            valid = true;
        } catch (IOException ex) {
            valid = false;
        }

        try {
            Document doc = Jsoup.connect(link).get();
            valid = true;
        } catch (HttpStatusException ex) {
            valid = false;
        } catch (IOException ex) {
            valid = false;
        }
        if (link.contains("linkedin") || link.contains("github") || link.contains("google")) {
            valid = true;
        }
        return valid;
    }
}