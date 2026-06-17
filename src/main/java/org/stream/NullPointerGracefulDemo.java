package org.stream;

import java.util.Optional;

/**
 * 优雅避免 NullPointerException 示例
 */
public class NullPointerGracefulDemo {

    // 模拟业务实体
    static class User {
        private String name;
        private Address address;

        // getter / setter
        public String getName() { return name; }
        public void setName(String name){ this.name = name;}
        public Address getAddress(){ return  address;}
        public void setAddress(Address address) { this.address = address;}
    }

    static class Address {
        private String city;
        private Street street;

        public String getCity(){ return  city;}
        public void setCity(String city){ this.city = city;}
        public Street getStreet(){ return street;}
        public  void setStreet(Street street) {this.street = street;}
    }

    static class Street {
        private String streetName;
        public String getStreetName(){ return streetName;}
        public void setStreetName(String streetName){ this.streetName = streetName;}
    }

    public static void main(String[] args){
        // 1. 正常值的情况
        User normalUser = buildNormalUser();
        String normalStreet = getStreetNameGracefully(normalUser);
        System.out.println("正常用户街道：" + normalStreet);

        // 2. 某一层为null 的情况
        User nullAddressUser = buildUserWithNullAddress();
        String nullStreet = getStreetNameGracefully(nullAddressUser);
        System.out.println("地址为空时街道：" + nullStreet);

        // 3. 完全 null 的用户
        String nullUserStreet = getStreetNameGracefully(null);
        System.out.println("用户为null时街道：" + nullUserStreet);
    }

    /**
     * 优雅获取 streetName，全程不抛 NPE
     * @param user
     * @return
     */
    private static String getStreetNameGracefully(User user) {
        return Optional.ofNullable(user)
                .map(User::getAddress)
                .map(Address::getStreet)
                .map(Street::getStreetName)
                .orElse("未知街道");
    }

    // 构造正常用户
    private static User buildNormalUser(){
        Street street = new Street();
        street.setStreetName("张江高科技园区");

        Address address = new Address();
        address.setCity("上海");
        address.setStreet(street);

        User user = new User();
        user.setName("张飒");
        user.setAddress(address);
        return user;
    }

    // 构造 address 为 null 的用户
    private static User buildUserWithNullAddress(){
        User user = new User();
        user.setName("李四");
        user.setAddress(null); // 地址为空
        return user;
    }
}
