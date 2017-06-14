package io.github.coalangsoft.intern.fxparts;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Matthias on 09.06.2017.
 */
public class StreamCopyService extends ScheduledService<Integer> {

    private final InputStream from;
    private final OutputStream to;

    public StreamCopyService(InputStream from, OutputStream to){
        this.from = from;
        this.to = to;
    }

    @Override
    protected Task<Integer> createTask() {
        return new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                return from.read();
            }
        };
    }

    {
        setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                if(getValue() == -1){
                    cancel();
                }else{
                    try {
                        to.write(getValue());
                    } catch (IOException e) {
                        //TODO Handle write exception!
                        cancel();
                    }
                }
            }
        });
    }

}
