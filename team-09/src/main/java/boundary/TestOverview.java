package boundary;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TestOverview {
    String hello = "hello, world";

    public String getHello() {
        return "hello";
    }

    public void setHello(String hello) {
        this.hello = hello;
    }
}
