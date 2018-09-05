package com.codeboyq.consolidate;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

public class FolderProcessorTest {


    @Test
    public void testProcessor() throws Exception {

        File testFolder = new File("src/test/resources/TestPictures");
        File testFolderTemp = new File(testFolder.getPath() + "Temp");

        if (testFolderTemp.exists()) {
            FileUtils.deleteDirectory(testFolderTemp);
        }

        FileUtils.copyDirectory(testFolder, testFolderTemp);

        FolderProcessor.process(testFolderTemp);

        FileUtils.deleteDirectory(testFolderTemp);

    }


}
