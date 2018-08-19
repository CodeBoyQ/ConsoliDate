package com.codeboyq;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ConsoliDateApp {

    public static void main(String[] args) throws Exception {

        File srcDir = new File("/Users/astronauta/Documents/java_i_workspaces/ConsoliDate/src/main/resources/TestPictures");
        File destDir = new File("/Users/astronauta/Documents/java_i_workspaces/ConsoliDate/src/main/resources/TestPicturesTemp");

        try {
            FileUtils.copyDirectory(srcDir, destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Path> filesInFolder = Files.list(Paths.get(destDir.getPath()))
                .filter(Files::isRegularFile)
                .collect(toList());

        for (Path filePath : filesInFolder) {
            File currentFile = filePath.toFile();
            System.out.println(currentFile.getName() + " - " + ExifReader.getOriginalDateFromFile(currentFile));

            LocalDateTime fileDateTime = ExifReader.getOriginalDateFromFile(currentFile);

            String eventFolderName = fileDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

            File eventFolder = new File (currentFile.getParent() + File.separator + eventFolderName);
            if (!eventFolder.exists()) {
                eventFolder.mkdir();
            }

            Path temp = Files.move (Paths.get(currentFile.getPath()), Paths.get(eventFolder.getPath(), currentFile.getName()));

            if(temp != null)
            {
                System.out.println("File " + currentFile.getName() + "renamed and moved successfully");
            }
            else
            {
                System.out.println("Failed to move the file");
            }

        }





    }
}
