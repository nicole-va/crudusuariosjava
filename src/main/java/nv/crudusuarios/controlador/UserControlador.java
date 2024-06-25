package nv.crudusuarios.controlador;

import nv.crudusuarios.excepcion.RecursoNoEncontradoExcepcion;
import nv.crudusuarios.modelo.User;
import nv.crudusuarios.servicio.UserServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//controlador en rama nicole
@RestController
//http://localhost:8080/api
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:4200")
public class UserControlador {

    private static final Logger logger= LoggerFactory.getLogger(UserControlador.class);

    @Autowired
    private UserServicio userServicio;

    //http://localhost:8080/api/users
    @GetMapping("/users")
    public List<User> obtenerUsuarios(){
        List<User> usuarios = this.userServicio.listarUsuarios();
        logger.info("Usuarios Obtenidos");
        usuarios.forEach((usuario -> logger.info(usuarios.toString())));
        return usuarios;

    }

    @PostMapping("/users")
    public User agregarUsuario(@RequestBody User usuarios){
        logger.info("Usuario a agregar: " + usuarios);
        return this.userServicio.guardarUsuario(usuarios);
    }

    //primero controlador
    @GetMapping("/users/{id}")
    public ResponseEntity<User> obtenerUsuarioPorId(
            @PathVariable Long id
    ){
        User usuario = this.userServicio.buscarUsuariosPorId(Math.toIntExact(id));
        if(usuario != null) {
            return ResponseEntity.ok(usuario);
        }else{
            throw new RecursoNoEncontradoExcepcion("No se encontro el id "+ id);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody User usuarioRecibido
    ){
        User usuario = this.userServicio.buscarUsuariosPorId(Math.toIntExact(id));
        if (usuario == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        usuario.setUsername(usuarioRecibido.getUsername());
        usuario.setEmail(usuarioRecibido.getEmail());
        usuario.setFirstName(usuarioRecibido.getFirstName());
        usuario.setLastName(usuarioRecibido.getLastName());
        this.userServicio.guardarUsuario(usuario);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable Long id){
        User usuario = userServicio.buscarUsuariosPorId(Math.toIntExact(id));
        if (usuario == null)
            throw new RecursoNoEncontradoExcepcion("No se encontro el id: " + id);
        this.userServicio.eliminarUsuarioPorId(usuario.getId());
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
