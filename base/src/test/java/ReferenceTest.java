import org.junit.jupiter.api.Test;


/**
 * 此test表面赋值也为值传递，对象传递相应的引用地址,
 * user----- \
 *            实际对象地址（但user与user2没有任何引用关系） 即 user2---->user1--->实际对象地址这种理解是错误的
 * user2---- /
 */
public class ReferenceTest {

    private String first = "first";

    private String second;

    //String类是一个特例，每次赋值为重写生成一个实例
    @Test
    public void referenceTest1() throws InterruptedException {
        second = first;
        System.out.println(second);
        first = "second";
        System.out.println(second);
    }


    public static class User {
        public String name;

        public User(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    private User user1 = new User("user1");

    //user2保持对原user的引用，但user指向新user，至此各user引用不同的对象了
    @Test
    public void referenceTest2() throws InterruptedException {
        User user2 = user1;
        System.out.println(user2);
        user1 = new User("user2");
        System.out.println(user2);
    }

    //引用一致没有更改，所以可以同步修改
    @Test
    public void referenceTest3() throws InterruptedException {
        User user2 = user1;
        System.out.println(user2);
        user1.name = "user2";
        System.out.println(user2);
    }

}
