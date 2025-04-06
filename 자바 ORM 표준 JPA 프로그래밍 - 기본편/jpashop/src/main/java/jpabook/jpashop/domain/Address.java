package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Embeddable
public class Address {
    @Column(length = 10) // 10자 제한 -> Member, Delivery 동시 적용 가능
    private String city;

    @Column(length = 20)
    private String street;

    @Column(length = 5)
    private String zipcode;

    public Address(){}

    public Address(String city, String street, String number) {
        this.city = city;
        this.street = street;
        this.zipcode = number;
    }

    private String fullAddress() { // 이런걸 직접 만들어서 쓸 수 있다
        return getCity() + " " + getStreet() + " " + getZipcode();
    }

    public boolean isValid() {
        return false;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCity(), address.getCity()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getZipcode(), address.getZipcode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getStreet(), getZipcode());
    }
}
