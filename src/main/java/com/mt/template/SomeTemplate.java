package com.mt.template;

public class SomeTemplate {

    /**
     * this is a template method, before and after are fixed, the only need implementation is
     * abstract method doSomethingDetail'
     * @param callback
     */
    public void execute(MyCallback callback) {
        System.out.println("some fix before method execute");
        callback.doSomethingDetail("some-param");
        System.out.println("some fix after method execute");
    }
}
