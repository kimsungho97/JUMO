package JUMO.project.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
@Table(name="user")
public class User implements UserDetails {
    @Id
    @Column(name="uid",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uid;

    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String password;

    private Long balance;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Holding> holdings = new ArrayList<>();


    public static User createUser(String id, String password){
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setBalance(0L);
        return user;
    }

    public void subjectBalance(Long totalPrice){
        this.balance -= totalPrice;
    }

    public void addBalance(Long totalPrice){
        this.balance += totalPrice;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        return roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return id;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
