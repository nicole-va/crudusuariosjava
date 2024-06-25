package nv.crudusuarios.servicio;

import nv.crudusuarios.modelo.User;
import nv.crudusuarios.repositorio.UserRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicio implements IUserServicio{
    @Autowired
    private UserRepositorio userRepositorio;

    @Override
    public List<User> listarUsuarios() {
        return this.userRepositorio.findAll();
    }

    @Override
    public User buscarUsuariosPorId(Integer id) {
        User usuario = this.userRepositorio.findById(id).orElse(null);
        return usuario;
    }
    //si es igual a null se hace un insert caso contrario se hace un update
    @Override
    public User guardarUsuario(User usuario) {
        return this.userRepositorio.save(usuario);
    }

    @Override
    public void eliminarUsuarioPorId(Long id) {
        this.userRepositorio.deleteById(Math.toIntExact(id));
    }
}
