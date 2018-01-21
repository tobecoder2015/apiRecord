package com.meituan.hb.apirecord.core.record;

public class QueueHandler implements Runnable {

    private Object obj;

    public QueueHandler(Object obj){
        this.obj = obj;
    }

    @Override
    public void run() {
        doBusiness();
    }

    /**
     * 业务处理时限
     */
    public void doBusiness(){
        Message message=(Message) obj;

        RecordMap.set(message);
    }
}
