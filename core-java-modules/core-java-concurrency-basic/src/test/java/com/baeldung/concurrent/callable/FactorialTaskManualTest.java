package com.baeldung.concurrent.callable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static junit.framework.Assert.assertEquals;

public class FactorialTaskManualTest {

    private ExecutorService executorService;

    @Before
    public void setup(){
        executorService = Executors.newSingleThreadExecutor();
    }

    @Test
    public void whenTaskSubmitted_ThenFutureResultObtained() throws ExecutionException, InterruptedException {
        FactorialTask task = new FactorialTask(5);
        Future<Integer> future= executorService.submit(task);
        assertEquals(120,future.get().intValue());
    }

    @Test(expected = ExecutionException.class)
    public void whenException_ThenCallableThrowsIt() throws ExecutionException, InterruptedException {
    	FactorialTask task = new FactorialTask(-5);
    	Future<Integer> future= executorService.submit(task);
        Integer result=future.get().intValue();
    }

    @Test
    public void whenException_ThenCallableDoesntThrowsItIfGetIsNotCalled(){
        FactorialTask task = new FactorialTask(-5);
        Future<Integer> future= executorService.submit(task);
        assertEquals(false,future.isDone());
    }

    @Test
    public void Test() throws InterruptedException, ExecutionException{
        FactorialTask task = new FactorialTask(-5);
        //task executing time is 4000ms
        Future<Integer> future= executorService.submit(task);
        assertEquals(false,future.isDone());
        
        Thread.sleep(5000);
        assertEquals(true,future.isDone());
        
        try {
        	//future.get() => If task is still running, this line waits till it's done. 
	        //Completion may be due to normal termination, an exception, or cancellation.
            System.out.print(future.get());
        }catch(Exception e) {
        	//java.util.concurrent.ExecutionException
        	System.out.println(e);
        	
        	//e.getCause() => get the original checked exception
        	//com.baeldung.concurrent.callable.FactorialTask$InvalidParamaterException
        	System.out.println(e.getCause());
        	assertEquals(true,future.isDone());
        }
    }
    
    @After
    public void cleanup(){
        executorService.shutdown();
    }
}
