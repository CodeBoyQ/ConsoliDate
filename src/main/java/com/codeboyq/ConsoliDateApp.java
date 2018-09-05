package com.codeboyq;

import org.slf4j.ext.XLogger;
import org.slf4j.ext.XLoggerFactory;

import java.io.File;

public class ConsoliDateApp {

    private static final XLogger logger = XLoggerFactory.getXLogger(ConsoliDateApp.class);

    public static void main(String[] args) throws Exception {
        logger.entry(args[0]);

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
