/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.resources.clasificacion;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.clasificacion.ClasificacionRequest;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.clasificacion.ClasificacionResponse;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.clasificacion.ClasificacionUpdate;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.EntityAlreadyExistsException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.clasificacion.Clasificacion;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.services.clasificacion.ClasificacionService;
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
@Path("clasificaciones")
public class ClasificacionesResource {

    @Context
    UriInfo context;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createClasificacion(ClasificacionRequest clasificacionRequest) {
        System.out.println(clasificacionRequest.toString());
        ClasificacionService clasificacionService = new ClasificacionService();
        try {
            clasificacionService.createClasificacion(clasificacionRequest);
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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getClasificacion() {
        ClasificacionService clasificacionService = new ClasificacionService();
        try {
            List<ClasificacionResponse> clasificaciones = clasificacionService.getAllClasificacion()
                    .stream().
                    map(ClasificacionResponse::new)
                    .toList();
            return Response.ok(clasificaciones).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @Path("{code}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByIntOrString(@PathParam("code") String code) {
        String regex = "^\\+\\d+$";
        
        ClasificacionService clasificacionService = new ClasificacionService();
        try {
            if (code.matches(regex)) {
                int x = Integer.parseInt("a");
            }
            int id = Integer.parseInt(code);
            Clasificacion clasificacion = clasificacionService.getByInt(id);
            return Response.ok(new ClasificacionResponse(clasificacion)).build();
        } catch (NumberFormatException e) {
            return getUserString(code);
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

    @PUT
    @Path("{code}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateClasificacion(@PathParam("code") int code,
            ClasificacionUpdate clasificacionUpdate) {
        ClasificacionService clasificacionService = new ClasificacionService();
        try {
            clasificacionService.updateClasificacion(code, clasificacionUpdate);
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

    @DELETE
    @Path("{code}")
    public Response deleteClasificacion(@PathParam("code") int code) {
        ClasificacionService clasificacionService = new ClasificacionService();
        try {
            clasificacionService.deleteClasificacion(code);
            return Response.ok().build();
        } catch (UserDataInvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST)
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
        List<ClasificacionResponse> clsificaciones;
        ClasificacionService clasificaion = new ClasificacionService();
        try {
            clsificaciones = clasificaion.gatBYString(code)
                    .stream()
                    .map(ClasificacionResponse::new)
                    .toList();
            return Response.ok(clsificaciones).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + ex.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
