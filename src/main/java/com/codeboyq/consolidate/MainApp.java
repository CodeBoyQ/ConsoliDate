package com.codeboyq.consolidate;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.io.File;

public class MainApp {

    private static final XLogger logger = XLoggerFactory.getXLogger(MainApp.class);

    public static void main(String[] args) throws Exception {
        logger.entry(args);

        System.out.println("Start ConsoliDate Application");

        if (args.length != 1) {
            throw new ConsoliDateException("Please give only one argument: folderPath");
        }

        File folder = new File(args[0]);

        if (!folder.exists()) {
            throw new ConsoliDateException("Folder " + folder.getPath() + " does not exist");
        }

        FolderProcessor.process(folder);

        logger.exit();
    }
}
