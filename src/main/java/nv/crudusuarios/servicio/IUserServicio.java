package nv.crudusuarios.servicio;

import java.util.List;
import nv.crudusuarios.modelo.User;


public interface IUserServicio {

    public List<User> listarUsuarios();
    public User buscarUsuariosPorId(Integer user);
    public User guardarUsuario(User user);
    public void eliminarUsuarioPorId(Long id);
}
