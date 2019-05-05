package com.kodilla.ecommercee.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @Column(name = "ID", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "STATUS")
    private boolean status;

    @Column(name = "USER_KEY")
    private int userKey;

    public User(String userName){
        this.userName = userName;
        status = true;
        userKey = keyGenerator();
    }

    public int keyGenerator(){
        int keyValue = 0;
        int[] keyNumbers = IntStream.range(0,5).toArray();
        keyNumbers = Arrays.stream(keyNumbers)
                .map(e -> new Random().nextInt(10))
                .toArray();

        for (int i = 0 ; i < keyNumbers.length ; i++) {
            if (keyNumbers[4] == 0){
                keyNumbers[4] = 1;
            }
            keyValue += Math.pow(10,i) * keyNumbers[i];
        }
        return keyValue;
    }
}
