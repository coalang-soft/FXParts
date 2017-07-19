package io.github.coalangsoft.intern.fxparts;

import javafx.beans.property.Property;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Output stream that writes to a string property.
 * Created by Matthias on 31.05.2017.
 */
public class StringPropertyOutputStream extends OutputStream{

    private final Property<String> property;

    /**
     * Creates a String Property Output stream.
     * @param property the property to write to.
     */
    public StringPropertyOutputStream(Property<String> property){
        this.property = property;
    }

    @Override
    public void write(int b) throws IOException {
        property.setValue(property.getValue() + (char) b);
    }

    /**
     * Copies the content from an input stream to this stream.
     * @param from the stream to copy from.
     */
    public void asyncWriteAll(InputStream from){
        new StreamCopyService(from, this).start();
    }

    /**
     * Copies the content from an input stream to this stream. Only updates the property at new lines
     * @param from the stream to copy from.
     */
    public void asyncWriteLines(InputStream from){
        new StreamLineCopyService(from, this).start();
    }

}
