package threads;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.*;

/**
 * It is a concurrent implementation with newly found directories getting scanned by the thread pool
 * in the Executor Framework.It uses concurrent collections for Queue and List to hold the indexed files.
 * The indexer picks lock the files from the queue and does something with them
 */

// FileFilter implementation for image files
class ImageFileFilter implements FileFilter {
    private final String[] okFileExtensions =
            new String[]{"jpg", "png", "gif"};

    public boolean accept(File file) {
        for (String extension : okFileExtensions)
            if (file.getName().toLowerCase().endsWith(extension)) {
                return true;
            }
        return false;
    }
}

public class FileCrawler implements Runnable {
    private final BlockingQueue<File> fileQueue;
    private ConcurrentSkipListSet indexedFiles = new ConcurrentSkipListSet();
    private final FileFilter fileFilter;
    private final File root;
    private final ExecutorService exec = Executors.newCachedThreadPool();

    public FileCrawler(BlockingQueue<File> fileQueue,
                       final FileFilter fileFilter,
                       File root) {
        this.fileQueue = fileQueue;
        this.root = root;
        this.fileFilter = new FileFilter() {
            public boolean accept(File f) {
                return f.isDirectory() || fileFilter.accept(f);
            }
        };
    }

    public void run() {

        submitCrawlTask(root);

    }

    private void submitCrawlTask(File f) {
        CrawlTask crawlTask = new CrawlTask(f);
        exec.execute(crawlTask);
    }

    private class CrawlTask implements Runnable {
        private final File file;

        CrawlTask(File file) {

            this.file = file;
        }

        public void run() {
            if (Thread.currentThread().isInterrupted())
                return;

            File[] entries = file.listFiles(fileFilter);

            if (entries != null) {
                for (File entry : entries)
                    if (entry.isDirectory())
                        submitCrawlTask(entry);
                    else if (entry != null && !indexedFiles.contains(entry)) {
                        indexedFiles.add(entry);
                        try {
                            fileQueue.put(entry);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
            }
        }
    }
}

// Here is the file indexer thread
 class FileIndexer implements Runnable {
    private final BlockingQueue<File> queue;

    public FileIndexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                indexFile(queue.take());
            }
        } catch (InterruptedException e) {
            System.out.println("Indexer Interrupted");
            Thread.currentThread().interrupt();
        }
    }

    public void indexFile(File file) {
        // do something with the file...
        System.out.println("Indexing File : " + file.getAbsolutePath() + " " + file.getName());
    }

    ;
}

class FileCrawlerTest {
    public static void main(String[] args) {
        File dir = new File("D:\\dev\\css-templates\\cms-admin");
        BlockingQueue<File> blockingQueue = new ArrayBlockingQueue<>(5);
        FileCrawler fileCrawler = new FileCrawler(blockingQueue,
                new ImageFileFilter(), dir);
        new Thread(fileCrawler).start();

        FileIndexer indexer = new FileIndexer(blockingQueue);
        new Thread(indexer).start();
    }
}


