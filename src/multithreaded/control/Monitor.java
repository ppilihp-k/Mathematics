package multithreaded.control;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by PhilippKroll on 08.11.2016.
 */
public class Monitor {

    public static Monitor instance;
    private static ExecutorService pool;
    private AtomicBoolean lock;

    /**
     * the Monitor represents provides access to a thread pool, which is capable of
     * working through all kinds of classes, which implement the Callable<E> interface!
     * so all tasks, one want to be performed, should be submitted to this instance.
     * @return the only instance of this class
     */
    public static Monitor getInstance(){
        if(instance == null){
            pool = Executors.newCachedThreadPool();
            instance = new Monitor();
        }
        return instance;
    }

    private Monitor(){
        lock = new AtomicBoolean(false);
    }

    /**
     * getter for a mutes
     * @return atomicboolean functioning as a lock
     */
    public AtomicBoolean getLock(){
        return lock;
    }

    /**
     * terminates all threads from the managed pool
     */
    public void terminate(){
        pool.shutdown();
    }

    /**
     * submits a task to the threadpool, where the implemented loadbalancer submits the task to a specific thread
     * @param task the task, implementing the callable interface
     * @return a future object, after its computation is finished, the content of if contains the result of the tasks call() method
     */
    public Future<Object> addTask(Callable<Object> task){
        return pool.submit(task);
    }
}
