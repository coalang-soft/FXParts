package io.github.coalangsoft.intern.fxparts;

import javafx.beans.property.Property;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Matthias on 31.05.2017.
 */
public class StringPropertyOutputStream extends OutputStream{

    private final Property<String> property;

    public StringPropertyOutputStream(Property<String> property){
        this.property = property;
    }

    @Override
    public void write(int b) throws IOException {
        property.setValue(property.getValue() + (char) b);
    }

}
