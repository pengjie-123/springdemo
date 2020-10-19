package com.mt.template;

public class TemplateMain {

    public static void main(String[] args) {
        SomeTemplate template = new SomeTemplate();
        template.execute(new MyCallback() {
            @Override public void doSomethingDetail(String s) {
                System.out.println("doing something detail: " + s);
            }
        });
    }
}
