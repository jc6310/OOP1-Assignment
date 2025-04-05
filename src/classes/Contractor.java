package classes;

import java.util.concurrent.Callable;

public record Contractor(int id, String name, String title, long salary, String task) implements Callable<String> {

    // [A2_OOP2] Concurrency e.g. using ExecutorService to process a list of Callables.
    @Override
    public String call() throws Exception {
        Thread.sleep(100);
        System.out.println("Processes " + this);
        return name +" Task - " +task;
    }
}
