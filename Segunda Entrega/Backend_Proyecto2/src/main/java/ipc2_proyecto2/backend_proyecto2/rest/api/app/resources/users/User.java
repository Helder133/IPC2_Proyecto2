/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.resources.users;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.Usuario.NewUserRequest;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.Usuario.UpdateUserRequest;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.Usuario.UsuarioResponse;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.EntityAlreadyExistsException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.Usuario;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.services.users.UsuarioService;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

/**
 * REST Web Service
 *
 * @author helder
 */
@Path("user")
public class User {

    @Context
    UriInfo context;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(NewUserRequest newUserRequest) {
        UsuarioService usuarioService = new UsuarioService();
        try {
            Usuario usuario = usuarioService.createUsuario(newUserRequest);

            return Response.ok(new UsuarioResponse(usuario)).build();
        } catch (UserDataInvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (EntityAlreadyExistsException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUser() {
        UsuarioService usuarioService = new UsuarioService();
        try {
            List<UsuarioResponse> users = usuarioService.getAllUsers()
                    .stream()
                    .map(UsuarioResponse::new)
                    .toList();

            return Response.ok(users).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

    }

    @GET
    @Path("{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByInt(@PathParam("code") String code) {
        UsuarioService usuarioService = new UsuarioService();
        try {
            int code1 = Integer.parseInt(code);

            Usuario existingUser = usuarioService.getUsuarioByInt(code1);
            return Response.ok(new UsuarioResponse(existingUser)).build();
        } catch (NumberFormatException e) {

            return getUserString(code);

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

    @PUT
    @Path("{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("code") int code,
            UpdateUserRequest usuarioRequest) {
        UsuarioService usuarioService = new UsuarioService();
        
        try {
            Usuario userUpdate = usuarioService.updateUsuario(code, usuarioRequest);
            return Response.ok(new UsuarioResponse(userUpdate)).build();
        } catch (UserDataInvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (EntityAlreadyExistsException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
    
    @DELETE
    @Path("{code}")
    public Response deleteUser(@PathParam("code") int code){
        UsuarioService usuarioService = new UsuarioService();
        try {
            usuarioService.deleteUserByCode(code);
            
            return Response.ok().build();
        } catch (UserDataInvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (EntityAlreadyExistsException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
    
    private Response getUserString(String code) {
        List<UsuarioResponse> users;
        UsuarioService usuarioService = new UsuarioService();
        try {
            users = usuarioService.getUserByString(code)
                    .stream()
                    .map(UsuarioResponse::new)
                    .toList();
            return Response.ok(users).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + ex.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (UserDataInvalidException ex) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + ex.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
