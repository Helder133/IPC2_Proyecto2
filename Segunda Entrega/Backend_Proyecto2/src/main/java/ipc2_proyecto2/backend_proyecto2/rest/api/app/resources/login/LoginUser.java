/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.resources.login;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.Usuario.UsuarioResponse;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.login.UserLogin;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.Usuario;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.services.login.LoginService;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * REST Web Service
 *
 * @author helder
 */
@Path("login")
public class LoginUser {

    @Context
    UriInfo uriInfo;
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userLogin (UserLogin login){
        try {
            System.out.println("Comunicacion con el frontend exitodo :3");
            LoginService loginService = new LoginService();
            
            Usuario usuariLogin= loginService.loginUser(login);
            
            return Response.ok(new UsuarioResponse(usuariLogin)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (UserDataInvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
        
    }
}
