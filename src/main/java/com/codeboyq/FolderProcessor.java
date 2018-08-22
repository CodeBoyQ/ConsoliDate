package com.codeboyq;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FolderProcessor {

    private static int OFFSET_HOURS = 7; //Footage taken [OFFSET_HOURS] after midnight will still be part of the same day

    private FolderProcessor() {

    }

    public static void process(File destDir) throws Exception {

        List<Path> filesInFolder = Files.list(Paths.get(destDir.getPath()))
                .filter(Files::isRegularFile)
                .collect(toList());

        List<String> skippedFiles = new ArrayList();

        for (Path filePath : filesInFolder) {
            File currentFile = filePath.toFile();

            LocalDateTime currentFileExifDate;

            try {
                currentFileExifDate = ExifReader.getOriginalDateFromFile(currentFile);
            } catch (Exception e) {
                //This file can't be processed, go to the next file
                skippedFiles.add(currentFile.getName());
                System.out.println("Skipped file: " + currentFile.getName() + " because it could not be processed");
                continue;
            }

            System.out.println(currentFile.getName() + "  Exif date: " + ExifReader.getOriginalDateFromFile(currentFile));

            File eventFolder = new File (currentFile.getParent() + File.separator + getDestinationFolderName(currentFileExifDate));
            if (!eventFolder.exists()) {
                eventFolder.mkdir();
            }

            Path src = Paths.get(currentFile.getPath());
            Path dest = Paths.get(eventFolder.getPath(), currentFile.getName());
            Path temp = Files.move (src, dest);

            if(temp != null)
            {
                System.out.println("File " + currentFile.getName() + " renamed and moved successfully");
            } else {
                System.out.println("Failed to move the file");
            }

        }

        System.out.println("Skipped: " + skippedFiles.size() + " / " + skippedFiles.toString());
    }


    private static String getDestinationFolderName(LocalDateTime date) {

        LocalDateTime startOfDay = date.with(LocalTime.MIN);

        if (Duration.between(startOfDay, date).toHours() < OFFSET_HOURS) {
            return date.minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }

        return date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }


}
