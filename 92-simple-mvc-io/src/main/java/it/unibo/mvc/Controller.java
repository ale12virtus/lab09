package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private static final String HOME = System.getProperty("user.home");
    private static final String SEP = System.getProperty("file.separator");
    private static final String DEFAULT_FILE_NAME = "output.txt";

    private File file = new File(HOME + SEP + DEFAULT_FILE_NAME);

    /**
     * Using the default constructor.
     */
    public Controller() {
        //default constructor
    }

    /**
     * The file becomes the current.
     * 
     * @param file Becomes the current.
     */
    public void setFile(final File file) {
        this.file = file;
    }

    /**
     * Returns the current file.
     * 
     * @return The current file.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Returns the path of the current file.
     * 
     * @return The path of the current file.
     */
    public String getPath() {
        return this.file.getPath();
    }

    /**
     * Writes an input on the current file.
     * 
     * @param input What is going to be written.
     * @throws IOException when the file isn't opened correctly.
     */
    public void writeFile(final String input) throws IOException {
        try (PrintStream ps = new PrintStream(this.file, StandardCharsets.UTF_8)) {
            ps.print(input);
        }
    }
}
