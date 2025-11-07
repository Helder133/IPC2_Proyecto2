/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package ipc2_proyecto2.backend_proyecto2.rest.api.app.resources.cine;

import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.cine.CineRequest;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.cine.CineResponse;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.dtos.cine.CineUpdate;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.EntityAlreadyExistsException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.exceptions.UserDataInvalidException;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.models.cine.Cine;
import ipc2_proyecto2.backend_proyecto2.rest.api.app.services.cine.CineService;
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
@Path("cines")
public class CineResource {

    @Context
    UriInfo context;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createCine(CineRequest cineRequest) {
        CineService cineService = new CineService();
        try {
            Cine cine = cineService.createCine(cineRequest);

            return Response.ok(new CineResponse(cine)).build();
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
        } catch (EntityAlreadyExistsException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCine() {
        CineService cineService = new CineService();
        try {
            List<CineResponse> cines = cineService.getAllCine()
                    .stream()
                    .map(CineResponse::new)
                    .toList();
            return Response.ok(cines).build();
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
    public Response getCineByCode(@PathParam("code") String code) {
        CineService cineService = new CineService();
        try {
            int code1 = Integer.parseInt(code);
            Cine cine = cineService.getCineByInt(code1);
            return Response.ok(new CineResponse(cine)).build();
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
            CineUpdate cineUpdate) {
        CineService cineService = new CineService();
        try {
            Cine updateCine = cineService.updateCine(code, cineUpdate);
            return Response.ok(new CineResponse(updateCine)).build();
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
        } catch (EntityAlreadyExistsException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }

    @DELETE
    @Path("{code}")
    public Response deleteCine(@PathParam("code") int code) {
        CineService cineService = new CineService();
        try {
            cineService.deleteCineByCode(code);
            return Response.ok().build();
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
        List<CineResponse> cines;
        CineService cineService = new CineService();
        try {
            cines = cineService.getCineByString(code)
                    .stream()
                    .map(CineResponse::new)
                    .toList();
            return Response.ok(cines).build();
        } catch (SQLException ex) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\": \"" + ex.getMessage() + "\"}")
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }
    }
}
