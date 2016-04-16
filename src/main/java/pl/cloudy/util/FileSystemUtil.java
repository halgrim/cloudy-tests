package pl.cloudy.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by i323728 on 11/2/15.
 */
public class FileSystemUtil
{

    private String RESOURCES_JS = "resources" + File.separator + "js";
    private String IMAGES_FOLDER = "savedImages";
    private String LOAD_LIBRARIES = RESOURCES_JS + File.separator + "loadLibs.js";
    private String SAVE_ELEMENT_TO_FILE = RESOURCES_JS + File.separator + "saveElementToFile.js";

    public String readLoadLibrariesJS() throws IOException
    {
        return readFile(LOAD_LIBRARIES);
    }

    public String readSaveWebElementToFileJS() throws IOException{
        return readFile(SAVE_ELEMENT_TO_FILE);
    }

    public String generateUUID(){
        final UUID idOne = UUID.randomUUID();
        return idOne.toString();
    }

    public String buildImagesDestinationPath(){
        final String workingDir = System.getProperty("user.dir");
        return workingDir+ File.separator+ IMAGES_FOLDER;
    }

    public boolean checkIfDownloadImageExist(final String file)
    {
        final File newImage = new File(IMAGES_FOLDER + File.separator + file + ".png");
        if (newImage.length() > 0)
        {
            return true;
        } else {
            return false;
        }

    }

    private String readFile(final String file) throws IOException
    {
        final Charset cs = Charset.forName("UTF-8");
        final FileInputStream stream = new FileInputStream(file);
        try {
            final Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            final StringBuilder builder = new StringBuilder();
            final char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        }
        finally {
            stream.close();
        }
    }

    public void dumpAllImageFiles() throws IOException {

        final File directory = new File(IMAGES_FOLDER);
        if (!directory.exists()) {
            final String message = directory + " does not exist";
            throw new IllegalArgumentException(message);
        }

        if (!directory.isDirectory()) {
            final String message = directory + " is not a directory";
            throw new IllegalArgumentException(message);
        }

        File[] files = directory.listFiles();
        if (files == null) {  // null if security restricted
            throw new IOException("Failed to list contents of " + directory);
        }

        files = ArrayUtils.removeElement(files, new File(IMAGES_FOLDER + File.separator+ ".gitignore"));

        IOException exception = null;
        for (final File file : files) {
            try {
                FileUtils.forceDelete(file);
            } catch (final IOException ioe) {
                exception = ioe;
            }
        }

        if (null != exception) {
            throw exception;
        }

    }

}
