package algo.hashmap;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;

import java.math.BigInteger;

import java.nio.file.Path;

import java.security.DigestInputStream;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;

import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by ndreddy on 11/08/17.
 */
public class FindDupFiles {

    public static List<FilePaths> findDuplicateFiles(Path startingDirectory) {
        Map<String, FileInfo> filesSeenAlready = new HashMap<String, FileInfo>();
        Stack<Path> stack = new Stack<>();
        stack.push(startingDirectory);

        List<FilePaths> duplicates = new ArrayList<>();

        while (!stack.empty()) {

            Path currentPath = stack.pop();
            File currentFile = new File(currentPath.toString());

            // if it's a directory,
            // put the contents in our stack
            if (currentFile.isDirectory()) {
                for (File file : currentFile.listFiles()) {
                    stack.add(file.toPath());
                }

                // if it's a file
            } else {

                // get its hash
                String fileHash;
                try {
                    fileHash = sampleHashFile(currentPath);
                } catch (IOException e) {

                    // show error and skip this file
                    e.printStackTrace();
                    continue;
                } catch (NoSuchAlgorithmException e) {

                    // show error and skip this file
                    e.printStackTrace();
                    continue;
                }

                // get its last edited time
                long currentLastEditedTime = currentFile.lastModified();

                // if we've seen it before
                if (filesSeenAlready.containsKey(fileHash)) {

                    FileInfo fileInfo = filesSeenAlready.get(fileHash);
                    long existingLastEditedTime = fileInfo.timeLastEdited;
                    Path existingPath = fileInfo.path;

                    if (currentLastEditedTime > existingLastEditedTime) {

                        // current file is the dupe!
                        duplicates.add(new FilePaths(currentPath, existingPath));

                    } else {

                        // old file is the dupe!
                        duplicates.add(new FilePaths(existingPath, currentPath));

                        // but also update filesSeenAlready to have the new file's info
                        filesSeenAlready.put(fileHash, new FileInfo(currentLastEditedTime, currentPath));
                    }

                    // if it's a new file, throw it in filesSeenAlready
                    // and record its path and last edited time,
                    // so we can tell later if it's a dupe
                } else {
                    filesSeenAlready.put(fileHash, new FileInfo(currentLastEditedTime, currentPath));
                }
            }
        }
        return duplicates;
    }

    private static final int SAMPLE_SIZE = 4000;

    private static String sampleHashFile(Path path) throws IOException, NoSuchAlgorithmException {

        final long totalBytes = new File(path.toString()).length();

        try (FileInputStream inputStream = new FileInputStream(path.toString())) {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            DigestInputStream digestInputStream = new DigestInputStream(inputStream, digest);

            // if the file is too short to take 3 samples, hash the entire file
            if (totalBytes < SAMPLE_SIZE * 3) {
                byte[] bytes = new byte[(int) totalBytes];
                digestInputStream.read(bytes);
            } else {
                byte[] bytes = new byte[SAMPLE_SIZE * 3];
                long numBytesBetweenSamples = (totalBytes - SAMPLE_SIZE * 3) / 2;

                // read first, middle and last bytes
                for (int n = 0; n < 3; n++) {
                    digestInputStream.read(bytes, n * SAMPLE_SIZE, SAMPLE_SIZE);
                    digestInputStream.skip(numBytesBetweenSamples);
                }
            }
            return new BigInteger(1, digest.digest()).toString(16);
        }
    }


    public static class FilePaths {

        private Path duplicatePath;
        private Path originalPath;

        public FilePaths(Path duplicatePath, Path originalPath) {
            this.duplicatePath = duplicatePath;
            this.originalPath = originalPath;
        }

        public Path getDuplicatePath() {
            return duplicatePath;
        }

        public Path getOriginalPath() {
            return originalPath;
        }

        @Override
        public String toString() {
            return String.format("(duplicate: %s, original: %s)", duplicatePath, originalPath);
        }
    }


    private static class FileInfo {

        long timeLastEdited;
        Path path;

        FileInfo(long timeLastEdited, Path path) {
            this.timeLastEdited = timeLastEdited;
            this.path = path;
        }
    }


}