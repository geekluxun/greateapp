package com.geekluxun.greateapp.jdk.java8.lambda;

/**
 * Created by luxun on 2018/5/7.
 */
public class Human {

    private String name;

    private int age;

    public Human() {
        super();
    }

    public Human(final String name, final int age) {
        super();
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    /**
     * 默认的对象相等是指同一个对象，这里覆写
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return (this.getName().equals(((Human) obj).getName()) &&
                this.getAge() == ((Human) (obj)).getAge());
    }


    public static int compareByNameThenAge(Human lhs, Human rhs) {
        if (lhs.name.equals(rhs.name)) {
            return lhs.age - rhs.age;
        } else {
            return lhs.name.compareTo(rhs.name);
        }
    }
}
