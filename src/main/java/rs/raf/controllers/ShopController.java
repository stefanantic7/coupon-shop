package rs.raf.controllers;

import rs.raf.annotations.Authenticated;
import rs.raf.annotations.AuthenticatedAsAdmin;
import rs.raf.dtos.ShopDto;
import rs.raf.models.Coupon;
import rs.raf.models.Shop;
import rs.raf.requests.CreateShopRequest;
import rs.raf.responses.PaginationResponse;
import rs.raf.services.ShopService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path(value="/shops")
public class ShopController {

    private ShopService shopService;

    @Inject
    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GET
    @Path("/all")
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    public List<ShopDto> all() {
        return this.shopService.all();
    }

    @GET
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    public PaginationResponse<ShopDto> get(@QueryParam("page")@DefaultValue("1") int page,
                                           @QueryParam("limit")@DefaultValue("10") int limit) {
        return this.shopService.paginate(limit, page);
    }

    @GET
    @Path("/{shopId}")
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    public ShopDto find(@PathParam("shopId") int shopId) {
        return this.shopService.find(shopId);
    }

    @DELETE
    @Path("/{shopId}")
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("shopId") int shopId) {
        this.shopService.delete(shopId);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public ShopDto create(@Valid CreateShopRequest createShopRequest) {
        return this.shopService.create(createShopRequest.getName());
    }

}
