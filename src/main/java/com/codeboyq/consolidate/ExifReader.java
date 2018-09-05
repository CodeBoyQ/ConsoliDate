package com.codeboyq.consolidate;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import com.joestelmach.natty.DateGroup;
import com.joestelmach.natty.Parser;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ExifReader {

    public static LocalDateTime getOriginalDateFromFile(final File file) throws Exception {
        Date date = null;

        //Extract the date from the exif data from the file
        if (file.isFile()) {
            final Metadata metadata = ImageMetadataReader.readMetadata(file);
            final Directory directory = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
            if (directory != null) {
                date = directory.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
            }
        }

        if (date == null) {
            //If there is no exif metadata available, extract the date from the filename using natural language date parser
            date = getOriginalDateFromFileName(file);
        }
        return convertToLocalDate(date);
    }

    public static void dumpMetadata (File inputFile) throws ImageProcessingException, IOException {
        Metadata metadata = ImageMetadataReader.readMetadata(inputFile);

        for (Directory directory : metadata.getDirectories()) {
            for (Tag tag : directory.getTags()) {
                System.out.format("[%s] - %s = %s",
                        directory.getName(), tag.getTagName(), tag.getDescription());
                System.out.println();
            }
            if (directory.hasErrors()) {
                for (String error : directory.getErrors()) {
                    System.err.format("ERROR: %s", error);
                }
            }
        }
    }

    private static Date getOriginalDateFromFileName(File file) throws Exception {
        Parser parser = new Parser();
        List<DateGroup> groups = parser.parse(file.getName());
        for (DateGroup group : groups) {
            List<Date> dates = group.getDates();
            return dates.get(0);
        }

        throw new Exception("Couldn't find a natural date");
    }

    private static LocalDateTime convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}