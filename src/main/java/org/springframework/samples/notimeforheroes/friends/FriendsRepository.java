package org.springframework.samples.notimeforheroes.friends;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.notimeforheroes.user.User;



public interface FriendsRepository extends  CrudRepository<Friends, Integer>{

    @Query("Select f FROM Friends f where (f.user1 = :friendA OR f.user2 = :friendA) AND f.friendState = 1")
      public Collection<Friends> getByFriendA(@Param("friendA") User friendA);
      //Busca en Friends las rows donde el user se encunetra registrado y aceptado
    
    @Query("Select f FROM Friends f where f.user1 = :friendSA AND f.friendState = 1")
      public Collection<Friends> getByFriendSA(@Param("friendSA") User friendSA);
    //Busca en Friends las rows donde el user se encuentra registrado y aceptado, donde fue sender

    @Query("Select f FROM Friends f where f.user2 = :friendRA AND f.friendState = 1")
      public Collection<Friends> getByFriendRA(@Param("friendRA") User friendRA);
    //Busca en Friends las rows donde el user se encuentra registrado y aceptado, donde fue receiver
    
    @Query("Select f FROM Friends f where f.user1 = :friendSP AND f.friendState = 0")
      public Collection<Friends> getByFriendSP(@Param("friendSP") User friendSP);
    //Busca en Friends las rows donde el user se encuentra registrado y pendiente, donde fue sender
    
    @Query("Select f FROM Friends f where f.user2 = :friendRP AND f.friendState = 0")
      public Collection<Friends> getByFriendRP(@Param("friendRP") User friendRP);
    //Busca en Friends las rows donde el user se encuentra registrado y pendiente, donde fue receiver
	
}