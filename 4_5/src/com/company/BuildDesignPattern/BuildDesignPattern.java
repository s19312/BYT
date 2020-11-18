package com.company.BuildDesignPattern;

public class BuildDesignPattern {
    public static void main(String[] args) {
        Person person1 = new Person.PersonBuilder("Vova", "Huzhak").age(30).build();
        System.out.println(person1);
        Person person2 = new Person.PersonBuilder("Den", "Vovich").age(40).build();
        System.out.println(person2);

        PersonAccount personAccount1 = new PersonAccount.PersonAccountBuilder(person1).portfolio("Information...").build();
        System.out.println(personAccount1);
        PersonAccount personAccount2 = new PersonAccount.PersonAccountBuilder(person1).portfolio("Experience").build();
        System.out.println(personAccount2);
    }
}
class PersonAccount{
    private final Person person;
    private String porfolip;

    public PersonAccount(PersonAccountBuilder builder){
        this.person = builder.person;
        this.porfolip = builder.portfolio;
    }

    @Override
    public String toString() {
        return "PersonAccount: "+this.person.toString()+", "+this.porfolip;
    }

    public static class PersonAccountBuilder{
        private final Person person;
        private String portfolio;

        public PersonAccountBuilder(Person person){
            this.person = person;
        }
        public PersonAccountBuilder portfolio(String info){
            this.portfolio = info;
            return this;
        }
        public PersonAccount build(){
            return new PersonAccount(this);
        }

    }

}

class Person
{
    private final String firstName;
    private final String lastName;
    private final int age;

    private Person(PersonBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
    }

    @Override
    public String toString() {
        return "Person: "+this.firstName+", "+this.lastName+", "+this.age;
    }

    public static class PersonBuilder
    {
        private final String firstName;
        private final String lastName;
        private int age;

        public PersonBuilder(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }

        public Person build() {
            Person person =  new Person(this);
            return person;
        }
    }
}