package io.github.coalangsoft.intern.fxparts;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Matthias on 09.06.2017.
 */
public class StreamLineCopyService extends ScheduledService<String> {

    private final InputStream from;
    private final OutputStream to;

    private Scanner scanner;
    private PrintWriter writer;

    public StreamLineCopyService(InputStream from, OutputStream to){
        this.from = from;
        this.to = to;
    }

    @Override
    protected Task<String> createTask() {
        if(scanner == null){
            scanner = new Scanner(from);
            writer = new PrintWriter(to);
        }

        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                try{
                    return scanner.nextLine();
                }catch(Exception e){
                    return null;
                }
            }
        };
    }

    {
        setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                if(getValue() == null){
                    cancel();
                }else{
                    writer.println(getValue());
                }
            }
        });
    }

}
