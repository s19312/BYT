package com.company;

import java.util.ArrayList;
import java.util.List;

public class MediatorPattern {

    public static void main(String[] args) {
        ChatMediator mediator = new ConcreteChatMediator();
        User user1 = new ConcreteUser(mediator, "Vlad");
        User user2 = new ConcreteUser(mediator, "Vova");
        User user3 = new ConcreteUser(mediator, "Roman");
        User user4 = new ConcreteUser(mediator, "Den");
        mediator.addUser(user1);
        mediator.addUser(user2);
        mediator.addUser(user3);
        mediator.addUser(user4);

        user1.send("Hi All");
        user2.send("Hi!");
        user3.send("How are u?");
    }
}
class ConcreteChatMediator implements ChatMediator {

    private List<User> users;
    public ConcreteChatMediator(){
        this.users=new ArrayList<>();
    }

    @Override
    public void addUser(User user){
        this.users.add(user);
    }

    @Override
    public void sendMessage(String msg, User user) {
        for(User u : this.users){
            if(u != user){
                u.receive(msg);
            }
        }
    }

}
interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator med, String name){
        this.mediator=med;
        this.name=name;
    }

    public abstract void send(String msg);
    public abstract void receive(String msg);
}

class ConcreteUser extends User {
    public ConcreteUser(ChatMediator med, String name) {
        super(med, name);
    }
    @Override
    public void send(String msg){
        System.out.println(this.name+": Sending Message="+msg);
        mediator.sendMessage(msg, this);
    }
    @Override
    public void receive(String msg) {
        System.out.println(this.name+": Received Message:"+msg);
    }
}