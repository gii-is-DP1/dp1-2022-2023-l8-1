package org.springframework.samples.notimeforheroes.friends;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.samples.notimeforheroes.model.BaseEntity;
import org.springframework.samples.notimeforheroes.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "friends")
public class Friends extends BaseEntity{
	
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user1_id", referencedColumnName = "id")
	private User user1;

	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "user2_id", referencedColumnName = "id")
	private User user2;

    private FriendState friendState;

	@ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "receiver", referencedColumnName = "id")
    private User receiver;
    
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "sender", referencedColumnName = "id")
    private User sender;	

	@Override
        public String toString() {
            return "Friend{" +
                "id=" + id +
                ", User1='" + user1 + '\'' +
                ", User2='" + user2 + '\'' +
                ", FriendState='" + friendState + '\'' +
                '}';
        }
	
}
