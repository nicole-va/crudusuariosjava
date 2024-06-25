package nv.crudusuarios.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import nv.crudusuarios.modelo.User;

public interface UserRepositorio extends JpaRepository<User, Integer> {

}
