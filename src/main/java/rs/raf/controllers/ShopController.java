package rs.raf.controllers;

import rs.raf.annotations.Authenticated;
import rs.raf.annotations.AuthenticatedAsAdmin;
import rs.raf.dtos.ShopDto;
import rs.raf.models.Coupon;
import rs.raf.models.Shop;
import rs.raf.repositories.CouponRepository;
import rs.raf.repositories.ShopRepository;
import rs.raf.services.ShopService;

import javax.inject.Inject;
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
    @AuthenticatedAsAdmin
    @Produces(MediaType.APPLICATION_JSON)
    public List<ShopDto> get() {
        return this.shopService.paginate(10, 2);
    }


}
